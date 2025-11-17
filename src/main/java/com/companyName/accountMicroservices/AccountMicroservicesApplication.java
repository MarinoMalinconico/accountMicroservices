package com.companyName.accountMicroservices;

import com.companyName.coreMicroservices.repository.entity.Account;
import com.companyName.coreMicroservices.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
@AutoConfiguration
@EntityScan(basePackages = "com.companyName.coreMicroservices.repository.entity")
@EnableJpaRepositories(basePackages = "com.companyName.coreMicroservices.repository")
public class AccountMicroservicesApplication implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountMicroservicesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        log.info("Metodo run");
        //accountRepository.save(new Account(null,"pippo","gulp","pippo@topolinia.top", "RGNLSN87H13D761R", new BigDecimal("3000.01")));
        //accountRepository.save(new Account(null,"pluto","bau","pluto@topolinia.top", "RGNLSN87H13D761R", new BigDecimal("3000.02")));
        //accountRepository.save(new Account(null,"paperino","quack","paperino@paperopoli.top", "FRNFBA85M08D761M", new BigDecimal("3000.03")));
        //accountRepository.save(new Account(null,"topolino","squit","topolino@topolinia.top", "DSTLCU89R52D761R", new BigDecimal("3000.04")));
        //accountRepository.save(new Account(null,"sora","roxas","sora@gummiship.kh", "DSTLCU89R52D761R", new BigDecimal("3000.05")));
    }

}
