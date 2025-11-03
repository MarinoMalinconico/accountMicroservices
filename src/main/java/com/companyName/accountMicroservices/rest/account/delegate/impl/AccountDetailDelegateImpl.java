package com.companyName.accountMicroservices.rest.account.delegate.impl;

import com.companyName.accountMicroservices.repository.entity.Account;
import com.companyName.accountMicroservices.repository.entity.AccountRepository;
import com.companyName.accountMicroservices.rest.account.delegate.AccountDetailDelegate;
import com.companyName.accountMicroservices.rest.account.model.response.AccountDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AccountDetailDelegateImpl implements AccountDetailDelegate {

    @Autowired
    AccountRepository repository;

    @Override
    public List<AccountDetailResponse> getAccountDetail(String userCode) {
        log.debug("Into getAccountDetail delegate with PathParameter [{}]", userCode);

        List<Account> dbResult = repository.getAllAccountPerUser(userCode);
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    private List<AccountDetailResponse> dbResultToDto(List<Account> dtos) {
        List<AccountDetailResponse> formattedDTOs = new ArrayList<>();
        for (Account dto : dtos) {
            AccountDetailResponse fileDto = new AccountDetailResponse();
            fileDto.setId(dto.getId());
            fileDto.setFkUser(dto.getFkUser());
            fileDto.setTotal(BigDecimal.valueOf(dto.getTotal()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
            formattedDTOs.add(fileDto);
        }
        return formattedDTOs;
    }

    @Override
    public List<AccountDetailResponse> getAccountDetailJPA(String userCode) {
        log.debug("Into getAccountDetail delegate with PathParameter [{}]", userCode);

        List<Account> dbResult = repository.findByFkUser(userCode);
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
}
