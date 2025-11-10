package com.companyName.accountMicroservices.rest.account.delegate.impl;

import com.companyName.accountMicroservices.repository.entity.Account;
import com.companyName.accountMicroservices.repository.AccountRepository;
import com.companyName.accountMicroservices.rest.account.delegate.AccountDetailDelegate;
import com.companyName.accountMicroservices.rest.account.model.request.AddAccountDetailRequest;
import com.companyName.accountMicroservices.rest.account.model.response.AccountDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountDetailDelegateImpl implements AccountDetailDelegate {

    @Autowired
    AccountRepository repository;

    @Override
    public List<AccountDetailResponse> getAccountDetail(String FkUser) {
        log.debug("Into getAccountDetail delegate with PathParameter [{}]", FkUser);

        List<Account> dbResult = repository.getAllAccountPerUser(FkUser);
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    private List<AccountDetailResponse> dbResultToDto(List<Account> dtos) {
        List<AccountDetailResponse> formattedDTOs = new ArrayList<>();
        for (Account dto : dtos) {
            AccountDetailResponse fileDto = new AccountDetailResponse();
            fileDto.setId(dto.getId());
            fileDto.setName(dto.getName());
            fileDto.setSurname(dto.getSurname());
            fileDto.setEmail(dto.getEmail());
            fileDto.setFkUser(dto.getFkUser());
            fileDto.setBalance(dto.getBalance().setScale(2,BigDecimal.ROUND_HALF_DOWN));
            formattedDTOs.add(fileDto);
        }
        return formattedDTOs;
    }

    @Override
    public List<AccountDetailResponse> getAccountDetailJPA(String FkUser) {
        log.debug("Into getAccountDetail delegate with PathParameter [{}]", FkUser);

        List<Account> dbResult = repository.findByFkUser(FkUser);
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public List<AccountDetailResponse> getAllJPA() {
        log.debug("Into getAccountDetail delegate with all");

        List<Account> dbResult = repository.findAll();
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public List<AccountDetailResponse> addAccountDetail(AddAccountDetailRequest account) {
        log.debug("Into addAccountDetail");

        repository.save(new Account(account.getId(),account.getName(), account.getSurname(), account.getEmail(), account.getFkUser(), account.getBalance()));

        List<Account> dbResult = repository.findByFkUser(account.getFkUser());
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public List<AccountDetailResponse> updateAccountDetail(Account account) {
        log.debug("Into updateAccountDetail");

        repository.save(new Account(account));

        List<Account> dbResult = repository.findByFkUser(account.getFkUser());
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public boolean deleteAccountDetail(Account account) {
        log.debug("Into deleteAccountDetail for [{} - {}]",account.getFkUser(),account.getId());

        repository.delete(new Account(account));

        return true;
    }
}
