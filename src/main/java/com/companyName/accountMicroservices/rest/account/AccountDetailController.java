package com.companyName.accountMicroservices.rest.account;

import com.companyName.accountMicroservices.common.model.BasicResponse;
import com.companyName.accountMicroservices.rest.account.delegate.AccountDetailDelegate;
import com.companyName.accountMicroservices.rest.account.exceptions.AccountDetailException;
import com.companyName.accountMicroservices.rest.account.model.request.AccountDetailRequest;
import com.companyName.accountMicroservices.rest.account.model.request.AddAccountDetailRequest;
import com.companyName.accountMicroservices.rest.account.model.response.AccountDetailResponse;
import com.companyName.coreMicroservices.repository.entity.Account;
import com.companyName.coreMicroservices.repository.entity.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

@Slf4j
@Controller
public class AccountDetailController {

    @Autowired
    AccountDetailDelegate delegate;

    @RequestMapping(value = "/accountDetailBasicResponse",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> accountDetailBasicResponse(@RequestBody AccountDetailRequest account) throws InvalidParameterException, AccountDetailException {

        log.info("Entering in accountDetail service - PathVariable: [{}]", account.getCf());

        List<AccountDetailResponse> delegateResult =  null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAccountDetail(account.getCf());
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
                throw new AccountDetailException("No data found for request param: "+account.getCf());
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //con path parameter
    @RequestMapping(value = "/accountDetailBasicResponseParam",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> accountDetailBasicResponse(@RequestParam String FkUser) throws InvalidParameterException {

        log.info("Entering in accountDetail service(param) - PathVariable: [{}]", FkUser);

        List<AccountDetailResponse> delegateResult =  null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAccountDetail(FkUser);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //get con read e jpa
    @RequestMapping(value = "/accountDetailBasicResponseParam",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> accountDetailBasicResponseGET(@RequestParam String FkUser) throws InvalidParameterException {

        log.info("Entering in accountDetail service(param)(JPA) - PathVariable: [{}]", FkUser);

        List<AccountDetailResponse> delegateResult =  null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAccountDetailJPA(FkUser);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //get all jpa
    @RequestMapping(value = "/accountDetailBasicResponseParamAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> accountDetailBasicResponseGET() throws InvalidParameterException {

        log.info("Entering in accountDetail service(param)(JPA)(all)");

        List<AccountDetailResponse> delegateResult =  null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAllJPA();
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/AddAccount",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> addAccount(@RequestBody AddAccountDetailRequest account) throws InvalidParameterException {

        log.info("Entering in add account of [{}]", account.getFkUser());

        List<AccountDetailResponse> delegateResult =  null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.addAccountDetail(account);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/AddInvoice",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> updateAccount(@RequestBody Invoice invoice, @RequestParam Long accountId) throws InvalidParameterException {

        log.info("Entering in AddInvoice [{}]",accountId);

        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegate.addInvoiceToAccount(accountId,invoice);

            log.debug("response [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/UpdateAccount",
    method = RequestMethod.PUT,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> updateAccount(@RequestBody Account account) throws InvalidParameterException {

        log.info("Entering in account update of [{}]",account.getFkUser());

        List<AccountDetailResponse> delegateResult = null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult = delegate.updateAccountDetail(account);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/DeleteAccount",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> deleteAccount(@RequestBody Account account) throws InvalidParameterException {

        log.info("Entering in account delete of [{}]",account.getFkUser());

        boolean deleted=false;

        List<AccountDetailResponse> delegateResult = null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAccountDetailJPA(account.getFkUser());
            deleted=delegate.deleteAccountDetail(account);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }
        log.info(deleted ? "eseguita delete di {} - {}" : "errore nella delete di {}", account.getFkUser(),account.getId());

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/DeleteAccountByCf",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> deleteAccountByCf(@RequestBody Account account) throws InvalidParameterException {

        log.info("Entering in account delete of [{}]",account.getFkUser());

        int deleted=-1;

        List<AccountDetailResponse> delegateResult = null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAccountDetailJPA(account.getFkUser());
            deleted=delegate.deleteAccountDetailByCf(account);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }
        if (deleted > 0) log.info("eseguita delete di {} elementi di {}", deleted, account.getFkUser());
        else
            log.info(deleted == 0 ? "nessun elemento di {}":"errore nella delete di {}", account.getFkUser());

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}