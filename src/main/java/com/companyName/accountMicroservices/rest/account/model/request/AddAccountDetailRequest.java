package com.companyName.accountMicroservices.rest.account.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class AddAccountDetailRequest {

    @Getter
    @Setter
    private String id;

    @Getter @Setter
    private String fkUser;

    @Getter @Setter
    private BigDecimal total;
}
