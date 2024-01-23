package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.repositories.TppProductRegister;
import ru.vtb.course.lesson5.repositories.TppProductRegisterRepo;

@Service
public class CheckAccountByProductAndTypeService implements CheckAccountByProductAndTypeServiceable {
    private TppProductRegisterRepo accountRepo;
    private FindProductServiceable findProductServisable;

    public CheckAccountByProductAndTypeService(TppProductRegisterRepo accountRepo, FindProductServiceable findProductServisable) {
        this.accountRepo = accountRepo;
        this.findProductServisable = findProductServisable;
    }

    @Override
    public void checkAccountByProductAndType(AccountRequest accountRequest) {
        TppProductRegister[] accArr = accountRepo.findByProductIdAndType(
                findProductServisable.findProduct(accountRequest.getInstanceId()),
                accountRequest.getRegistryTypeCode()
        );
        if (accArr.length > 0) {
            throw new DuplicateException(
                    "Параметр registryTypeCode тип регистра <" + accountRequest.getRegistryTypeCode() +
                            "> уже существует для ЭП с ИД  <" + accountRequest.getInstanceId() + ">");
        }
    }
}
