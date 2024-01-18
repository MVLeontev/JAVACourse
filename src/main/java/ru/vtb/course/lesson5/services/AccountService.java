package ru.vtb.course.lesson5.services;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.AccountResponse;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.TppProductRegister;
import ru.vtb.course.lesson5.repositories.TppProductRegisterRepo;
import ru.vtb.course.lesson5.repositories.TppProductRepo;

import java.util.ArrayList;

@Service
public class AccountService {

    private TppProductRegisterRepo accountRepo;
    private TppProductRepo productRepo;
    private CommonService commonService;


    public AccountService(TppProductRegisterRepo accountRepo, TppProductRepo productRepo, CommonService commonService) {
        this.accountRepo = accountRepo;
        this.productRepo = productRepo;
        this.commonService = commonService;
    }

    public AccountResponse makeAccount(AccountRequest accountRequest) {
        TppProductRegister[] accArr = accountRepo.findByProductIdAndType(
                commonService.findProduct(Long.valueOf(accountRequest.getInstanceId())),
                accountRequest.getRegistryTypeCode()
        );
        if (accArr.length > 0) {
            throw new DuplicateException(
                    "Параметр registryTypeCode тип регистра <" + accountRequest.getRegistryTypeCode() +
                    "> уже существует для ЭП с ИД  <" + accountRequest.getInstanceId() + ">");
        }

        ArrayList<String> regTypeArrVal = productRepo.getProductRegisterTypeCode(Long.valueOf(accountRequest.getInstanceId()));
        if (regTypeArrVal.isEmpty() || !regTypeArrVal.contains(accountRequest.getRegistryTypeCode()) ) {
            throw new NotFoundException("Код продукта <"+accountRequest.getRegistryTypeCode()+"> не найден в каталоге продуктов для данного типа регистра");
        }

        String accNum = commonService.findAccountNumber(accountRequest.getBranchCode(),
                                                        accountRequest.getCurrencyCode(),
                                                        accountRequest.getMdmCode(),
                                                        accountRequest.getPriorityCode(),
                                                        accountRequest.getRegistryTypeCode());

        TppProductRegister ac = new TppProductRegister();
        ac.setProductId( productRepo.findById(Long.valueOf(accountRequest.getInstanceId()) ).orElseGet(null) );
        ac.setType( accountRequest.getRegistryTypeCode()) ;
        ac.setAccountId(commonService.generateAccountId(accNum));
        ac.setCurrencyCode(accountRequest.getCurrencyCode());
        ac.setState("Created");
        ac.setAccountNumber(accNum);
        System.out.println(ac.toString());
        //return ac;
        return new AccountResponse( String.valueOf(accountRepo.save(ac).getId()) );
    }
}
