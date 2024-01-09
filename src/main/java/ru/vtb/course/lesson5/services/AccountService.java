package ru.vtb.course.lesson5.services;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.controllers.AccountRequest;
import ru.vtb.course.lesson5.repositories.Account;

@Service
public class AccountService {

    public Account makeAccount(AccountRequest accountRequest) {
        Account ac = new Account();
        ac.setProduct_id(accountRequest.getInstanceId());
        ac.setType(accountRequest.getAccountType());
        ac.setAccount_id(1110L);
        ac.setCurrency_code(accountRequest.getCurrencyCode());
        ac.setState("Created");
        ac.setId(22222L);
        System.out.println(ac.toString());
        return ac;
    }
}
