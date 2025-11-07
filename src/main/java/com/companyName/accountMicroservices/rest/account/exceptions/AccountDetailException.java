package com.companyName.accountMicroservices.rest.account.exceptions;

public class AccountDetailException extends Exception {
    public AccountDetailException(String message) {
        super(message);
    }
    public AccountDetailException(String message, Throwable cause) {
        super(message, cause);
    }
}
