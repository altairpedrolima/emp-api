package com.all.emplaca.bdstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BdConfig {

    @Autowired
    private StartOfBankService startOfBankService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String estrategiaDeBanco;

    @Bean
    public boolean estanciarBanco(){

        if (!"create".equals(estrategiaDeBanco)){
            return false;
        }

        startOfBankService.startarBanco();

        return true;

    }


}
