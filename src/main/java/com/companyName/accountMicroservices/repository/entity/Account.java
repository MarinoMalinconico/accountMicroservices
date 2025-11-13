package com.companyName.accountMicroservices.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    @Getter
    @Setter
    private Long id;

    @Column(name="NAME")
    @Getter @Setter
    private String name;

    @Column(name="SURNAME")
    @Getter @Setter
    private String surname;

    @Column(name="EMAIL")
    @Getter @Setter
    private String email;

    @Column(name="FK_USER")
    @Getter @Setter
    private String fkUser;

    @Column(name="BALANCE")
    @Getter @Setter
    private BigDecimal balance;

    public Account(Account account) { //serve a fare new Account(account), altrimenti devi fare new Account(account.getId,account.getName,....)
        this.id = account.getId();
        this.name = account.getName();
        this.surname = account.getSurname();
        this.email = account.getEmail();
        this.fkUser = account.getFkUser();
        this.balance = account.getBalance();
    }
}