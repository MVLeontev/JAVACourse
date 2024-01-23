package ru.vtb.course.lesson5.services;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.repositories.TppProductRegister;
import ru.vtb.course.lesson5.services.common.FindAccountNumberServiceable;
import ru.vtb.course.lesson5.services.common.FindProductServiceable;
import ru.vtb.course.lesson5.services.common.GenerateAccountIdServiceable;

@Service
public class PrepareAccountService implements PrepareAccountServiceable{

    private final FindAccountNumberServiceable findAccountNumberServiceable;
    private final FindProductServiceable findProductServiceable;
    private final GenerateAccountIdServiceable generateAccountIdServiceable;

    public PrepareAccountService(FindAccountNumberServiceable findAccountNumberServiceable, FindProductServiceable findProductServiceable, GenerateAccountIdServiceable generateAccountIdServiceable) {
        this.findAccountNumberServiceable = findAccountNumberServiceable;
        this.findProductServiceable = findProductServiceable;
        this.generateAccountIdServiceable = generateAccountIdServiceable;
    }

    @Override
    public TppProductRegister prepareAccount(AccountRequest accountRequest) {
        String accNum = findAccountNumberServiceable.findAccountNumber(accountRequest.getBranchCode(),
                accountRequest.getCurrencyCode(),
                accountRequest.getMdmCode(),
                accountRequest.getPriorityCode(),
                accountRequest.getRegistryTypeCode());

        TppProductRegister tppProductRegister = new TppProductRegister();
        tppProductRegister.setProductId(findProductServiceable.findProduct(accountRequest.getInstanceId()));
        tppProductRegister.setType( accountRequest.getRegistryTypeCode()) ;
        tppProductRegister.setAccountId(generateAccountIdServiceable.generateAccountId(accNum));
        tppProductRegister.setCurrencyCode(accountRequest.getCurrencyCode());
        tppProductRegister.setState("Created");
        tppProductRegister.setAccountNumber(accNum);
        return tppProductRegister;
    }


}
