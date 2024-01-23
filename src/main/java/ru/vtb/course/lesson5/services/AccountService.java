package ru.vtb.course.lesson5.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.AccountResponse;
import ru.vtb.course.lesson5.repositories.TppProductRegister;
import ru.vtb.course.lesson5.repositories.TppProductRegisterRepo;
import ru.vtb.course.lesson5.services.common.CheckAccountByProductAndTypeServiceable;
import ru.vtb.course.lesson5.services.common.CheckAccountByProductRegisterTypeCodeServiceable;

@Service
public class AccountService implements AccountServiceable{

    private final TppProductRegisterRepo accountRepo;
    private final PrepareAccountServiceable prepareAccountServiceable;
    private final CheckAccountByProductAndTypeServiceable checkAccountByProductAndTypeServiceable;
    private final CheckAccountByProductRegisterTypeCodeServiceable checkAccountByProductRegisterTypeCodeServiceable;


    public AccountService(TppProductRegisterRepo accountRepo, PrepareAccountServiceable prepareAccountServiceable, CheckAccountByProductAndTypeServiceable checkAccountByProductAndTypeServiceable, CheckAccountByProductRegisterTypeCodeServiceable checkAccountByProductRegisterTypeCodeServiceable) {
        this.accountRepo = accountRepo;
        this.prepareAccountServiceable = prepareAccountServiceable;
        this.checkAccountByProductAndTypeServiceable = checkAccountByProductAndTypeServiceable;
        this.checkAccountByProductRegisterTypeCodeServiceable = checkAccountByProductRegisterTypeCodeServiceable;
    }

    @Override
    @Transactional
    public AccountResponse makeAccount(AccountRequest accountRequest) {
        checkAccountByProductAndTypeServiceable.checkAccountByProductAndType(accountRequest);
        checkAccountByProductRegisterTypeCodeServiceable.checkAccountByProductRegisterTypeCode(accountRequest);
        TppProductRegister tppProductRegister = prepareAccountServiceable.prepareAccount(accountRequest);

        return new AccountResponse( String.valueOf(accountRepo.save(tppProductRegister).getId()) );
    }
}
