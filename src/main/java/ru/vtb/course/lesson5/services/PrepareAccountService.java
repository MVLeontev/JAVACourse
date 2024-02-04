package ru.vtb.course.lesson5.services;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.repositories.TppProduct;
import ru.vtb.course.lesson5.repositories.TppProductRegister;
import ru.vtb.course.lesson5.repositories.TppProductRegisterRepo;
import ru.vtb.course.lesson5.services.common.FindAccountNumberServiceable;

import ru.vtb.course.lesson5.services.common.GenerateAccountIdServiceable;

@Service
public class PrepareAccountService implements PrepareAccountServiceable{

    private final FindAccountNumberServiceable findAccountNumberServiceable;
    private final GenerateAccountIdServiceable generateAccountIdServiceable;
    private final TppProductRegisterRepo accountRepo;

    public PrepareAccountService(FindAccountNumberServiceable findAccountNumberServiceable, GenerateAccountIdServiceable generateAccountIdServiceable, TppProductRegisterRepo accountRepo) {
        this.findAccountNumberServiceable = findAccountNumberServiceable;
        this.generateAccountIdServiceable = generateAccountIdServiceable;
        this.accountRepo = accountRepo;
    }

    @Override
    public TppProductRegister prepareAccount(AccountRequest accountRequest) {
        String accNum = findAccountNumberServiceable.findAccountNumber(accountRequest.getBranchCode(),
                accountRequest.getCurrencyCode(),
                accountRequest.getMdmCode(),
                accountRequest.getPriorityCode(),
                accountRequest.getRegistryTypeCode());

        TppProductRegister tppProductRegister = new TppProductRegister();
        tppProductRegister.setType( accountRequest.getRegistryTypeCode()) ;
        tppProductRegister.setAccountId(generateAccountIdServiceable.generateAccountId(accNum));
        tppProductRegister.setCurrencyCode(accountRequest.getCurrencyCode());
        tppProductRegister.setState("Created");
        tppProductRegister.setAccountNumber(accNum);
        return tppProductRegister;
    }

    @Override
    public TppProductRegister[] findByProdIdAndType(TppProduct tppProduct, String type) {
        return accountRepo.findByProductIdAndType(tppProduct, type);
    }

    public TppProductRegister[] findByProdId(TppProduct tppProduct) {
        return accountRepo.findByProductId(tppProduct);
    }
    @Override
    public Long saveAccount(TppProductRegister productRegister) {
        return accountRepo.save(productRegister).getId();
    }

}
