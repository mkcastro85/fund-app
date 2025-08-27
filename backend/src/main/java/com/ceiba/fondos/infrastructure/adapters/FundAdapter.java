package com.ceiba.fondos.infrastructure.adapters;

import com.ceiba.fondos.domain.exceptions.FundException;
import com.ceiba.fondos.domain.models.Fund;
import com.ceiba.fondos.domain.models.Transaction;
import com.ceiba.fondos.domain.models.enums.TransactionTypeEnum;
import com.ceiba.fondos.domain.services.FundService;
import com.ceiba.fondos.infrastructure.mappers.FundMapper;
import com.ceiba.fondos.infrastructure.mappers.TransactionMapper;
import com.ceiba.fondos.infrastructure.repositories.ClientRepository;
import com.ceiba.fondos.infrastructure.repositories.FundRepository;
import com.ceiba.fondos.infrastructure.repositories.TransactionRepository;
import com.ceiba.fondos.infrastructure.repositories.entities.ClientEntity;
import com.ceiba.fondos.infrastructure.repositories.entities.FundEntity;
import com.ceiba.fondos.infrastructure.repositories.entities.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FundAdapter implements FundService {

    private final FundRepository fundRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final FundMapper fundMapper;

    public FundAdapter(FundRepository fundRepository, ClientRepository clientRepository, TransactionRepository transactionRepository, TransactionMapper transactionMapper, FundMapper fundMapper) {
        this.fundRepository = fundRepository;
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.fundMapper = fundMapper;
    }


    @Override
    public Transaction subscribe(String client, String fund, String notification) {
        ClientEntity clientEntity = clientRepository.findById(client).orElseThrow();
        FundEntity fundEntity = fundRepository.findById(fund).orElseThrow();

        if (clientEntity.getBalance() < fundEntity.getMinAmount()) {
            throw new FundException("No tiene saldo disponible para vincularse al fondo " + fundEntity.getName());
        }
        clientEntity.setBalance(clientEntity.getBalance() - fundEntity.getMinAmount());
        if (clientEntity.getFunds() == null || clientEntity.getFunds().isEmpty()) {
            clientEntity.setFunds(new ArrayList<>());
        }
        clientEntity.getFunds().add(fundEntity);
        clientRepository.save(clientEntity);
        TransactionEntity tx = new TransactionEntity();
        tx.setFund(fundEntity);
        tx.setType(TransactionTypeEnum.OPEN);
        tx.setAmount(fundEntity.getMinAmount());
        return transactionMapper.toDomain(transactionRepository.save(tx));
    }

    @Override
    public Transaction cancel(String client,String fundId) {
        ClientEntity clientEntity = clientRepository.findById(client).orElseThrow();
        FundEntity fundEntity = fundRepository.findById(fundId).orElseThrow();


        validateClient(fundId, clientEntity);

        clientEntity.setBalance(clientEntity.getBalance() + fundEntity.getMinAmount());
        if (clientEntity.getFunds() != null) {
            clientEntity.getFunds().remove(fundEntity);
        }

        clientRepository.save(clientEntity);

        TransactionEntity tx = new TransactionEntity();
        tx.setFund(fundEntity);
        tx.setType(TransactionTypeEnum.CLOSE);
        tx.setAmount(fundEntity.getMinAmount());
        return transactionMapper.toDomain(transactionRepository.save(tx));

    }

    private static void validateClient(String fundId, ClientEntity clientEntity) {
        boolean notSubscribed = clientEntity.getFunds()
                .stream()
                .noneMatch(data -> data.getId().equals(fundId));

        if (notSubscribed) {
            throw new FundException("El cliente no está suscrito a este fondo.");
        }
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(data->transactionMapper.toDomain(data))
                .toList();
    }

    @Override
    public List<Fund> getFunds() {
        return fundRepository.findAll().stream()
                .map(data->fundMapper.toDomain(data))
                .toList();
    }
}
