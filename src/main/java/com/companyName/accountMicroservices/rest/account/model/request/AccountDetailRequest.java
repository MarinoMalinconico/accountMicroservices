package com.companyName.accountMicroservices.rest.account.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailRequest {

    @Getter
    @Setter
    private String cf;

}
