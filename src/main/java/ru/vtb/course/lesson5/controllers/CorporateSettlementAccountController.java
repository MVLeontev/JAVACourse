package ru.vtb.course.lesson5.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.course.lesson5.services.AccountService;

@RestController
@RequestMapping("corporate-settlement-account")
public class CorporateSettlementAccountController {

    private AccountService accountService;
    public CorporateSettlementAccountController(AccountService ac) {
        this.accountService = ac;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createAccount(@RequestBody @Valid  AccountRequest accountRequest){  //@Valid
        //return null;
        return ResponseEntity.ok(accountService.makeAccount(accountRequest).getId().toString());
    }
}
