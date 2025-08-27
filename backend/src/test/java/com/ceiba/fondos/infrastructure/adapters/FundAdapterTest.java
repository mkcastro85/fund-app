package com.ceiba.fondos.infrastructure.adapters;


import com.ceiba.fondos.domain.exceptions.FundException;
import com.ceiba.fondos.domain.models.Fund;
import com.ceiba.fondos.domain.models.Transaction;
import com.ceiba.fondos.domain.models.enums.TransactionTypeEnum;
import com.ceiba.fondos.infrastructure.mappers.TransactionMapper;
import com.ceiba.fondos.infrastructure.repositories.ClientRepository;
import com.ceiba.fondos.infrastructure.repositories.FundRepository;
import com.ceiba.fondos.infrastructure.repositories.TransactionRepository;
import com.ceiba.fondos.infrastructure.repositories.entities.ClientEntity;
import com.ceiba.fondos.infrastructure.repositories.entities.FundEntity;
import com.ceiba.fondos.infrastructure.repositories.entities.TransactionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FundAdapterTest {

    @Mock
    private FundRepository fundRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private FundAdapter fundAdapter;

    private ClientEntity client;
    private Fund fund;
    private FundEntity fundEntity;
    private TransactionEntity transactionEntity;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fundEntity = new FundEntity();
        fundEntity.setId("fund1");
        fundEntity.setName("FPV_BTG_PACTUAL");
        fundEntity.setMinAmount(100_000D);

        client = new ClientEntity();
        client.setId("client1");
        client.setBalance(10000000D);
        List<FundEntity> listas=new ArrayList<>();
        client.setFunds(listas);

        fund = new Fund();
        fund.setId("fund1");
        fund.setName("FPV_BTG_PACTUAL");
        fund.setMinAmount(100_000D);

        transactionEntity = new TransactionEntity();
        transactionEntity.setFund(fundEntity);
        transactionEntity.setType(TransactionTypeEnum.OPEN);
        transactionEntity.setAmount(100_000D);

        transaction = new Transaction();
        transaction.setFund(fund);
        transaction.setType(TransactionTypeEnum.OPEN);
        transaction.setAmount(100_000D);
    }

    @Test
    void subscribe_successful() {
        when(clientRepository.findById("client1")).thenReturn(Optional.of(client));
        when(fundRepository.findById("fund1")).thenReturn(Optional.of(fundEntity));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity);
        when(transactionMapper.toDomain(transactionEntity)).thenReturn(transaction);

        Transaction result = fundAdapter.subscribe("client1", "fund1", "email");

        assertNotNull(result);
        assertEquals(TransactionTypeEnum.OPEN, result.getType());
        assertEquals("FPV_BTG_PACTUAL", result.getFund().getName());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void subscribe_insufficientBalance_shouldThrowException() {
        client.setBalance(50_000D); // menos que el monto mínimo
        when(clientRepository.findById("client1")).thenReturn(Optional.of(client));
        when(fundRepository.findById("fund1")).thenReturn(Optional.of(fundEntity));

        FundException ex = assertThrows(FundException.class, () ->
                fundAdapter.subscribe("client1", "fund1", "sms")
        );

        assertTrue(ex.getMessage().contains("No tiene saldo disponible"));
        verify(clientRepository, never()).save(any());
        verify(transactionRepository, never()).save(any());
    }

    @Test
    void cancel_successful() {
        client.getFunds().add(fundEntity);
        when(clientRepository.findById("client1")).thenReturn(Optional.of(client));
        when(fundRepository.findById("fund1")).thenReturn(Optional.of(fundEntity));

        TransactionEntity closeTx = new TransactionEntity();
        closeTx.setFund(fundEntity);
        closeTx.setType(TransactionTypeEnum.CLOSE);
        closeTx.setAmount(fund.getMinAmount());

        Transaction closeTransaction = new Transaction();
        closeTransaction.setFund(fund);
        closeTransaction.setType(TransactionTypeEnum.CLOSE);
        closeTransaction.setAmount(fund.getMinAmount());

        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(closeTx);
        when(transactionMapper.toDomain(closeTx)).thenReturn(closeTransaction);

        Transaction result = fundAdapter.cancel("client1", "fund1");

        assertNotNull(result);
        assertEquals(TransactionTypeEnum.CLOSE, result.getType());
        assertEquals("FPV_BTG_PACTUAL", result.getFund().getName());
        verify(clientRepository, times(1)).save(client);
    }



    @Test
    void getTransactions_shouldReturnList() {
        when(transactionRepository.findAll()).thenReturn(List.of(transactionEntity));
        when(transactionMapper.toDomain(transactionEntity)).thenReturn(transaction);

        List<Transaction> result = fundAdapter.getTransactions();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("FPV_BTG_PACTUAL", result.get(0).getFund().getName());
        verify(transactionRepository, times(1)).findAll();
    }
}