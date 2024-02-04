package ru.vtb.course.lesson5.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.services.AccountService;
import ru.vtb.course.lesson5.services.AccountServiceable;

@RestController
@RequestMapping("corporate-settlement-account")
public class CorporateSettlementAccountController {

    private final AccountServiceable accountServiceable;

    public CorporateSettlementAccountController(AccountServiceable accountServiceable) {
        this.accountServiceable = accountServiceable;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountRequest accountRequest){  //@Valid
        //return null;
        return ResponseEntity.ok(  accountServiceable.makeAccount(accountRequest) )  ;
    }
}
