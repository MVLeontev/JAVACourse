package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.ProductInstanceArrangement;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.repositories.TppProduct;
import ru.vtb.course.lesson5.repositories.TppProductRepo;

@Service
public class CheckProductArrangementByNumService implements CheckProductArrangementByNumServiceable {
    private final TppProductRepo productRepo;

    public CheckProductArrangementByNumService(TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void checkProductArrangementByNum(ProductRequest pr) {
        for (ProductInstanceArrangement pa : pr.getInstanceArrangement()) {
            String num = pa.getNumber();
            TppProduct[] p = productRepo.findByNumberAndAgreementIdIsNotNull(num);
            if (p.length > 0) throw new DuplicateException("Параметр № Дополнительного соглашения (сделки) Number  <"+num+"> уже существует для ЭП с ИД <"+p[0].getAgreementId()+">.");
        }
    }
}
