package com.companyName.accountMicroservices.rest.account.delegate.impl;

import com.companyName.coreMicroservices.repository.entity.Account;
import com.companyName.coreMicroservices.repository.AccountRepository;
import com.companyName.accountMicroservices.rest.account.delegate.AccountDetailDelegate;
import com.companyName.accountMicroservices.rest.account.model.request.AddAccountDetailRequest;
import com.companyName.accountMicroservices.rest.account.model.response.AccountDetailResponse;
import com.companyName.coreMicroservices.repository.entity.Invoice;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            fileDto.setBalance(dto.getBalance() != null ? dto.getBalance().setScale(2,BigDecimal.ROUND_HALF_DOWN): null);
            fileDto.setInvoices(dto.getInvoices());
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

        repository.save(new Account(account.getId(),account.getName(), account.getSurname(), account.getEmail(), account.getFkUser(), account.getBalance(), account.getInvoices()));

        List<Account> dbResult = repository.findByFkUser(account.getFkUser());
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    public void addInvoiceToAccount(Long accountId, Invoice invoice){
        Optional<Account> account = repository.findById(accountId);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("Invoice not found " + accountId);
        }
        account.get().getInvoices().add(invoice);
        repository.save(account.get());
    }

    @Override
    @Transactional
    public List<AccountDetailResponse> updateAccountDetail(Account newAccount) {
        log.debug("Into updateAccountDetail");

        Optional<Account> currentAccount = repository.findById(newAccount.getId());
        currentAccount.ifPresent(account -> {
            account.setName(newAccount.getName() != null ? newAccount.getName() : currentAccount.get().getName());
            account.setSurname(newAccount.getSurname() != null ? newAccount.getSurname() : currentAccount.get().getSurname());
            account.setEmail(newAccount.getEmail() != null ? newAccount.getEmail() : currentAccount.get().getEmail());
            account.setFkUser(newAccount.getFkUser() != null ? newAccount.getFkUser() : currentAccount.get().getFkUser());
            account.setBalance(newAccount.getBalance() != null ? newAccount.getBalance() : currentAccount.get().getBalance());
        });
        //il transactional fa la save
        //repository.save(currentAccount.get());
        Optional<Account> dbResult = repository.findById(newAccount.getId());
        List<AccountDetailResponse> response = dbResultToDto(dbResult.stream().toList());

        return response;
    }

    @Override
    public boolean deleteAccountDetail(Account account) {
        log.debug("Into deleteAccountDetail for [{} - {}]",account.getFkUser(),account.getId());

        repository.delete(new Account(account));

        return true;
    }

    @Override
    public boolean deleteAccountDetailByCf(Account account) {
        log.debug("Into deleteAccountDetail for [{} - {}]",account.getFkUser(),account.getId());

        List<Account> dbResult = repository.findByFkUser(account.getFkUser());

        try {
            for(Account accToDelete:dbResult){
                repository.deleteById(accToDelete.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
