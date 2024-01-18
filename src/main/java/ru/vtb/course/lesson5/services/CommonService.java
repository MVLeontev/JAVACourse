package ru.vtb.course.lesson5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.*;

@Service
public class CommonService {
    private TppProductRegisterRepo accountRepo;
    private TppRefProductRegisterTypeRepo refProductRegisterTypeRepo;
    private TppRefProductClassRepo refProductClassRepo;
    private TppRefAccountTypeRepo refAccountTypeRepo;
    private TppProductRepo productRepo;
    private AccountPoolRepo accountPoolRepo;

    @Autowired
    public CommonService(TppProductRegisterRepo accountRepo, TppRefProductRegisterTypeRepo refProductRegisterTypeRepo, TppRefProductClassRepo refProductClassRepo, TppRefAccountTypeRepo refAccountTypeRepo, TppProductRepo productRepo, AccountPoolRepo accountPoolRepo) {
        this.accountRepo = accountRepo;
        this.refProductRegisterTypeRepo = refProductRegisterTypeRepo;
        this.refProductClassRepo = refProductClassRepo;
        this.refAccountTypeRepo = refAccountTypeRepo;
        this.productRepo = productRepo;
        this.accountPoolRepo = accountPoolRepo;
    }

    public TppRefProductRegisterType findRegType(String typeCode) {
        TppRefProductRegisterType[] regTypeArr = refProductRegisterTypeRepo.findByValue(typeCode);
        if (regTypeArr.length == 0){
            throw new NotFoundException("Не найдена запись в tpp_ref_product_register_type со значением <"+typeCode+">");
        }
        if (regTypeArr.length > 1){
            throw new DuplicateException("Найдено более 1-ой записи в tpp_ref_product_register_type со значением <"+typeCode+">");
        }
        return regTypeArr[0];
    }
    public String findAccountNumber(String branchCode,
                                    String currencyCode,
                                    String mdmCode,
                                    String priorityCode,
                                    String registryTypeCode) {
        AccountPool[] accArr = accountPoolRepo.findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                branchCode, currencyCode, mdmCode, priorityCode, registryTypeCode);
        if (accArr.length == 0) {
            throw new NotFoundException("Не найден счет в пуле счетов со следующими данными: <branchCode> "+branchCode+
                    ", <currencyCode> "+currencyCode+", <mdmCode> "+mdmCode+", <priorityCode> "+priorityCode+", <registryTypeCode> "+registryTypeCode);
        }
        return accArr[0].getAccounts().split(",")[0];
    }

    public Long generateAccountId(String accountNum) {
        return Math.abs( (long)accountNum.hashCode());
    }

    public Long generateClientIdByMdm(String mdmCode) {
        return Long.valueOf(mdmCode);
    }

    public TppProduct findProduct(Long id) {
        TppProduct prod = productRepo.findById(id).orElseThrow(() -> new NotFoundException("Не найден экземпляр продукта <InstanseId> = "+id));
        return prod;
    }

}
