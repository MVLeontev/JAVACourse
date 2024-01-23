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
public class AccountService {

    private TppProductRegisterRepo accountRepo;
    private PrepareAccountService prepareAccountService;
    private CheckAccountByProductAndTypeServiceable checkAccountByProductAndTypeServisable;
    private CheckAccountByProductRegisterTypeCodeServiceable checkAccountByProductRegisterTypeCodeServisable;


    public AccountService(TppProductRegisterRepo accountRepo, PrepareAccountService prepareAccountService, CheckAccountByProductAndTypeServiceable checkAccountByProductAndTypeServisable, CheckAccountByProductRegisterTypeCodeServiceable checkAccountByProductRegisterTypeCodeServisable) {
        this.accountRepo = accountRepo;
        this.prepareAccountService = prepareAccountService;
        this.checkAccountByProductAndTypeServisable = checkAccountByProductAndTypeServisable;
        this.checkAccountByProductRegisterTypeCodeServisable = checkAccountByProductRegisterTypeCodeServisable;
    }

    @Transactional
    public AccountResponse makeAccount(AccountRequest accountRequest) {
        checkAccountByProductAndTypeServisable.checkAccountByProductAndType(accountRequest);
        checkAccountByProductRegisterTypeCodeServisable.checkAccountByProductRegisterTypeCode(accountRequest);
        TppProductRegister tppProductRegister = prepareAccountService.prepareAccount(accountRequest);

        return new AccountResponse( String.valueOf(accountRepo.save(tppProductRegister).getId()) );
    }
}
