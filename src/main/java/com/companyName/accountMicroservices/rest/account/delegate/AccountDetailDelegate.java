package com.companyName.accountMicroservices.rest.account.delegate;


import com.companyName.accountMicroservices.repository.entity.Account;
import com.companyName.accountMicroservices.rest.account.model.request.AccountDetailRequest;
import com.companyName.accountMicroservices.rest.account.model.request.AddAccountDetailRequest;
import com.companyName.accountMicroservices.rest.account.model.response.AccountDetailResponse;

import java.security.InvalidParameterException;
import java.util.List;

public interface AccountDetailDelegate {

    List<AccountDetailResponse> getAccountDetail(String FkUser) throws InvalidParameterException ;
    List<AccountDetailResponse> getAccountDetailJPA(String FkUser) throws InvalidParameterException ;
    List<AccountDetailResponse> getAllJPA() throws InvalidParameterException ;
    List<AccountDetailResponse> addAccountDetail(AddAccountDetailRequest account) throws InvalidParameterException ;
    List<AccountDetailResponse> updateAccountDetail(Account account) throws InvalidParameterException ;
    boolean deleteAccountDetail(Account account) throws InvalidParameterException ;
}
