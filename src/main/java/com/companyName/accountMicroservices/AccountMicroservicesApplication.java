package com.companyName.accountMicroservices;

import com.companyName.accountMicroservices.repository.entity.Account;
import com.companyName.accountMicroservices.repository.entity.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        accountRepository.save(new Account("cn4563df3", "RGNLSN87H13D761R", 3000.03));
        accountRepository.save(new Account("cn7256su9", "RGNLSN87H13D761R", 4000.10));
        accountRepository.save(new Account("cn6396dr7", "FRNFBA85M08D761M", 7000.00));
        accountRepository.save(new Account("cn2759ds4", "DSTLCU89R52D761R", 2000.00));
        accountRepository.save(new Account("cn2874da2", "DSTLCU89R52D761R", 8000.00));
    }

}
