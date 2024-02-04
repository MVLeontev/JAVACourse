package ru.vtb.course.lesson5.services;

import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.repositories.TppProduct;
import ru.vtb.course.lesson5.repositories.TppProductRegister;

public interface PrepareAccountServiceable {
    public TppProductRegister prepareAccount(AccountRequest accountRequest);

    public TppProductRegister[] findByProdIdAndType(TppProduct tppProduct, String type);

    public Long saveAccount(TppProductRegister productRegister) ;
    public TppProductRegister[] findByProdId(TppProduct tppProduct);


}
