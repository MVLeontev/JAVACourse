package ru.vtb.course.lesson5.services;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.AccountResponse;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.repositories.Account;
import ru.vtb.course.lesson5.repositories.AccountRepo;

@Service
public class AccountService {

    private AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public AccountResponse makeAccount(AccountRequest accountRequest) {
        Account[] accArr = accountRepo.findByProductIdAndType(Long.valueOf(accountRequest.getInstanceId()), accountRequest.getAccountType());
        if (accArr.length > 0) {
            throw new DuplicateException(
                    "Параметр registryTypeCode тип регистра <" + accountRequest.getAccountType() +
                    "> уже существует для ЭП с ИД  <" + accountRequest.getInstanceId() + ">");
        }

        Account ac = new Account();
        ac.setProductId( Long.valueOf(accountRequest.getInstanceId()) );
        ac.setType(accountRequest.getAccountType());
        ac.setAccountId(1110L);
        ac.setCurrencyCode(accountRequest.getCurrencyCode());
        ac.setState("Created");
        //ac.setId(22222L);
        System.out.println(ac.toString());
        //return ac;
        return new AccountResponse( String.valueOf(accountRepo.save(ac).getId()) );
    }
}
