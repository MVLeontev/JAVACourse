package ru.vtb.course.lesson5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.AccountResponse;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.repositories.TppProductRegister;
import ru.vtb.course.lesson5.repositories.TppProductRegisterRepo;

@Service
public class AccountService {

    private TppProductRegisterRepo accountRepo;


    public AccountService(TppProductRegisterRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    public AccountResponse makeAccount(AccountRequest accountRequest) {
        TppProductRegister[] accArr = accountRepo.findByProductIdAndType(Long.valueOf(accountRequest.getInstanceId()), accountRequest.getAccountType());
        if (accArr.length > 0) {
            throw new DuplicateException(
                    "Параметр registryTypeCode тип регистра <" + accountRequest.getAccountType() +
                    "> уже существует для ЭП с ИД  <" + accountRequest.getInstanceId() + ">");
        }

        TppProductRegister ac = new TppProductRegister();
        ac.setProductId( Long.valueOf(accountRequest.getInstanceId()) );
        //ac.setType(commonService.findRegType( accountRequest.getRegistryTypeCode()) );
        ac.setAccountId(1110L);
        ac.setCurrencyCode(accountRequest.getCurrencyCode());
        ac.setState("Created");
        //ac.setId(22222L);
        System.out.println(ac.toString());
        //return ac;
        return new AccountResponse( String.valueOf(accountRepo.save(ac).getId()) );
    }
}
