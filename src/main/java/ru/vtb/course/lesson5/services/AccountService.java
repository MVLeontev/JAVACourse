package ru.vtb.course.lesson5.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.AccountResponse;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.repositories.TppProductRegister;



@Service
public class AccountService implements AccountServiceable{

    private final PrepareAccountServiceable prepareAccountServiceable;
    private final ProductServiceable productServiceable;


    public AccountService(PrepareAccountServiceable prepareAccountServiceable, ProductServiceable productServiceable) {
        this.prepareAccountServiceable = prepareAccountServiceable;
        this.productServiceable = productServiceable;
    }

    @Override
    @Transactional
    public AccountResponse makeAccount(AccountRequest accountRequest) {
        checkAccountByProductAndType(accountRequest);
        productServiceable.checkAccountByProductRegisterTypeCode(accountRequest);
        TppProductRegister tppProductRegister = prepareAccountServiceable.prepareAccount(accountRequest);
        tppProductRegister.setProductId(productServiceable.findProduct(accountRequest.getInstanceId()));
        return new AccountResponse( String.valueOf(prepareAccountServiceable.saveAccount(tppProductRegister)) );
    }

    @Override
    public void checkAccountByProductAndType(AccountRequest accountRequest) {
        TppProductRegister[] accArr = prepareAccountServiceable.findByProdIdAndType(
                productServiceable.findProduct(accountRequest.getInstanceId()),
                accountRequest.getRegistryTypeCode()
        );
        if (accArr.length > 0) {
            throw new DuplicateException(
                    "Параметр registryTypeCode тип регистра <" + accountRequest.getRegistryTypeCode() +
                            "> уже существует для ЭП с ИД  <" + accountRequest.getInstanceId() + ">");
        }
    }

}
