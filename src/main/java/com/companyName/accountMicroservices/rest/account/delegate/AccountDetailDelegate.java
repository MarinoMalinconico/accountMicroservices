package com.companyName.accountMicroservices.rest.account.delegate;


import com.companyName.accountMicroservices.rest.account.model.response.AccountDetailResponse;

import java.security.InvalidParameterException;
import java.util.List;

public interface AccountDetailDelegate {

    List<AccountDetailResponse> getAccountDetail(String userCode) throws InvalidParameterException ;
    List<AccountDetailResponse> getAccountDetailJPA(String userCode) throws InvalidParameterException ;
    List<AccountDetailResponse> getAllJPA() throws InvalidParameterException ;
}
