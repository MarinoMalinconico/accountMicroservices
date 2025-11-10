package com.companyName.accountMicroservices;

import com.companyName.accountMicroservices.repository.entity.Account;
import com.companyName.accountMicroservices.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class AccountMicroservicesApplication implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountMicroservicesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        log.info("Metodo run");
        accountRepository.save(new Account("cn4563df3","pippo","gulp","pippo@topolinia.top", "RGNLSN87H13D761R", new BigDecimal("3000.01")));
        accountRepository.save(new Account("cn7256su9","pluto","bau","pluto@topolinia.top", "RGNLSN87H13D761R", new BigDecimal("3000.02")));
        accountRepository.save(new Account("cn6396dr7","paperino","quack","paperino@paperopoli.top", "FRNFBA85M08D761M", new BigDecimal("3000.03")));
        accountRepository.save(new Account("cn2759ds4","topolino","squit","topolino@topolinia.top", "DSTLCU89R52D761R", new BigDecimal("3000.04")));
        accountRepository.save(new Account("cn2874da2","sora","roxas","sora@gummiship.kh", "DSTLCU89R52D761R", new BigDecimal("3000.05")));
    }

}
