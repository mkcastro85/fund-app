package com.ceiba.fondos.infrastructure.config;

import com.ceiba.fondos.infrastructure.repositories.ClientRepository;
import com.ceiba.fondos.infrastructure.repositories.FundRepository;
import com.ceiba.fondos.infrastructure.repositories.entities.ClientEntity;
import com.ceiba.fondos.infrastructure.repositories.entities.FundEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(FundRepository fundRepository, ClientRepository clienteRepository) {
        return args -> {
            seedFunds(fundRepository);
            seedClientes(clienteRepository);
        };
    }

    private void seedFunds(FundRepository fundRepository) {
        if (fundRepository.count() == 0) {
            List<FundEntity> funds = Arrays.asList(
                    createFund("1", "FPV_BTG_PACTUAL_RECAUDADORA", 75000D, "FPV"),
                    createFund("2", "FPV_BTG_PACTUAL_ECOPETROL", 125000D, "FPV"),
                    createFund("3", "DEUDAPRIVADA", 50000D, "FIC"),
                    createFund("4", "FDO-ACCIONES", 250000D, "FIC"),
                    createFund("5", "FPV_BTG_PACTUAL_DINAMICA", 100000D, "FPV")
            );
            fundRepository.saveAll(funds);
            log.info("Fondos iniciales insertados en Mongo");
        } else {
            log.info("La colección de fondos ya tiene datos, no se insertaron registros.");
        }
    }

    private void seedClientes(ClientRepository clienteRepository) {
        if (clienteRepository.count() == 0) {
            ClientEntity cliente = new ClientEntity();
            cliente.setId("meyling");
            cliente.setName("Meyling");
            cliente.setBalance(500000D);
            clienteRepository.save(cliente);
            log.info("Cliente inicial");
        } else {
            log.info("La colección de clientes ya tiene datos, no se insertaron registros.");
        }
    }

    private FundEntity createFund(String id, String name, Double minAmount, String category) {
        FundEntity fund = new FundEntity();
        fund.setId(id);
        fund.setName(name);
        fund.setMinAmount(minAmount);
        fund.setCategory(category);
        return fund;
    }
}
