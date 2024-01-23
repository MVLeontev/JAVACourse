package ru.vtb.course.lesson5.services;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.repositories.TppProductRegister;
import ru.vtb.course.lesson5.services.common.FindAccountNumberServiceable;
import ru.vtb.course.lesson5.services.common.FindProductServiceable;
import ru.vtb.course.lesson5.services.common.GenerateAccountIdServiceable;

@Service
public class PrepareAccountService {

    private FindAccountNumberServiceable findAccountNumberServisable;
    private FindProductServiceable findProductServisable;
    private GenerateAccountIdServiceable generateAccountIdServisable;

    public PrepareAccountService(FindAccountNumberServiceable findAccountNumberServisable, FindProductServiceable findProductServisable, GenerateAccountIdServiceable generateAccountIdServisable) {
        this.findAccountNumberServisable = findAccountNumberServisable;
        this.findProductServisable = findProductServisable;
        this.generateAccountIdServisable = generateAccountIdServisable;
    }

    public TppProductRegister prepareAccount(AccountRequest accountRequest) {
        String accNum = findAccountNumberServisable.findAccountNumber(accountRequest.getBranchCode(),
                accountRequest.getCurrencyCode(),
                accountRequest.getMdmCode(),
                accountRequest.getPriorityCode(),
                accountRequest.getRegistryTypeCode());

        TppProductRegister tppProductRegister = new TppProductRegister();
        tppProductRegister.setProductId(findProductServisable.findProduct(accountRequest.getInstanceId()));
        tppProductRegister.setType( accountRequest.getRegistryTypeCode()) ;
        tppProductRegister.setAccountId(generateAccountIdServisable.generateAccountId(accNum));
        tppProductRegister.setCurrencyCode(accountRequest.getCurrencyCode());
        tppProductRegister.setState("Created");
        tppProductRegister.setAccountNumber(accNum);
        return tppProductRegister;
    }


}
