package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.TppProductRepo;

import java.util.ArrayList;

@Service
public class CheckAccountByProductRegisterTypeCodeService implements CheckAccountByProductRegisterTypeCodeServiceable {
    private final TppProductRepo productRepo;

    public CheckAccountByProductRegisterTypeCodeService(TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void checkAccountByProductRegisterTypeCode(AccountRequest accountRequest) {
        ArrayList<String> regTypeArrVal = productRepo.getProductRegisterTypeCode(accountRequest.getInstanceId());
        if (regTypeArrVal.isEmpty() || !regTypeArrVal.contains(accountRequest.getRegistryTypeCode()) ) {
            throw new NotFoundException("Код продукта <"+accountRequest.getRegistryTypeCode()+"> не найден в каталоге продуктов для данного типа регистра");
        }
    }
}
