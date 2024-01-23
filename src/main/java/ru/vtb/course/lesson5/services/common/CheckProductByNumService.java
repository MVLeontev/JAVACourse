package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.repositories.TppProduct;
import ru.vtb.course.lesson5.repositories.TppProductRepo;

@Service
public class CheckProductByNumService implements CheckProductByNumServiceable {
    private TppProductRepo productRepo;

    public CheckProductByNumService(TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void checkProductByNum(String num) {
        TppProduct[] p = productRepo.findByNumberAndAgreementIdIsNull(num);
        if (p.length > 0) throw new DuplicateException("Параметр ContractNumber № договора <"+num+"> уже существует для ЭП с ИД <"+p[0].getId()+">.");
    }
}
