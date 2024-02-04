package ru.vtb.course.lesson5.services;

import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.dto.ProductResponse;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.TppProduct;
import ru.vtb.course.lesson5.repositories.TppRefProductRegisterType;

public interface ProductServiceable {
    public ProductResponse makeProduct(ProductRequest request);

    public TppProduct findProduct(Long id);

    public void checkProductArrangementByNum(ProductRequest pr);
    public void checkProductByNum(String num) ;

    public TppRefProductRegisterType[] findRegisterTypeRec(ProductRequest pr);

    public void checkAccountByProductRegisterTypeCode(AccountRequest accountRequest);

}
