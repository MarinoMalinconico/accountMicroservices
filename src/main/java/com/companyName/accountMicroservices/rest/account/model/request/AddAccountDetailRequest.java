package com.companyName.accountMicroservices.rest.account.model.request;

import com.companyName.coreMicroservices.repository.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AddAccountDetailRequest {

    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String fkUser;

    @Getter @Setter
    private BigDecimal balance;

    @Getter @Setter
    private List<Invoice> invoices = new ArrayList<>();

}
