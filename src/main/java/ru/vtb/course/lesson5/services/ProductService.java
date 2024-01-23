package ru.vtb.course.lesson5.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.ProductInstanceArrangement;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.dto.ProductResponse;
import ru.vtb.course.lesson5.repositories.*;
import ru.vtb.course.lesson5.services.common.CheckProductArrangementByNumServiceable;
import ru.vtb.course.lesson5.services.common.CheckProductByNumServiceable;
import ru.vtb.course.lesson5.services.common.FindProductServiceable;
import ru.vtb.course.lesson5.services.common.FindRegisterTypeRecServiceable;

import java.util.ArrayList;

@Service
public class ProductService implements ProductServiceable {
    private TppProductRepo tppProductRepo;
    private TppProductRegisterRepo accountRepo;
    private PrepareProductService prepareProductService;
    private PrepareAccountService prepareAccountService;
    private FindProductServiceable findProductServisable;
    private CheckProductByNumServiceable checkProductByNumServisable;
    private CheckProductArrangementByNumServiceable checkProductArrangementbyNum;
    private FindRegisterTypeRecServiceable findRegisterTypeRecServisable;


    public ProductService(TppProductRepo tppProductRepo, TppProductRegisterRepo accountRepo, PrepareProductService prepareProductService, PrepareAccountService prepareAccountService, FindProductServiceable findProductServisable, CheckProductByNumServiceable checkProductByNumServisable, CheckProductArrangementByNumServiceable checkProductArrangementbyNum, FindRegisterTypeRecServiceable findRegisterTypeRecServisable) {
        this.tppProductRepo = tppProductRepo;
        this.accountRepo = accountRepo;
        this.prepareProductService = prepareProductService;
        this.prepareAccountService = prepareAccountService;
        this.findProductServisable = findProductServisable;
        this.checkProductByNumServisable = checkProductByNumServisable;
        this.checkProductArrangementbyNum = checkProductArrangementbyNum;
        this.findRegisterTypeRecServisable = findRegisterTypeRecServisable;
    }

    @Override
    @Transactional
    public ProductResponse makeProduct(ProductRequest request) {
        Long idProd;
        TppProduct tppProduct;
        ArrayList<String> arrRegisterId = new ArrayList<>();
        ArrayList<String> arrAgreementId = new ArrayList<>();

        //Проверка таблицы ДС на дубли (шаг 3)
        checkProductArrangementbyNum.checkProductArrangementByNum(request);

        if (request.getInstanceId() == null) {
            //проверим на дубли по номеру (шаг 2)
            checkProductByNumServisable.checkProductByNum(request.getContractNumber());
            //шаг 4
            TppRefProductRegisterType[] registryArr = findRegisterTypeRecServisable.findRegisterTypeRec(request);
            // добавим запись ЭП (шаг 5)
            tppProduct = prepareProductService.prepareProduct(request);
            idProd = tppProductRepo.save(tppProduct).getId(); // сохранили в БД и получили ID для последующего использования в AgreementId при создании ДС

            // создадим ПР
            for (int i = 0; i < registryArr.length; i++) {
                AccountRequest ar = new AccountRequest();
                ar.setInstanceId(idProd);
                ar.setBranchCode(request.getBranchCode());
                ar.setCurrencyCode(request.getIsoCurrencyCode());
                ar.setMdmCode(request.getMdmCode());
                ar.setPriorityCode(request.getUrgencyCode());
                ar.setRegistryTypeCode( registryArr[i].getValue() );
                TppProductRegister tpr = prepareAccountService.prepareAccount(ar);
                arrRegisterId.add(String.valueOf( accountRepo.save(tpr).getId() ) );
            }

        } else {
            idProd = request.getInstanceId();
            tppProduct = findProductServisable.findProduct(idProd);
        }
        //обработаем доп.соглашения
        for (ProductInstanceArrangement pia : request.getInstanceArrangement()) {
            TppProduct productArrangement = prepareProductService.prepareProductArrangement(pia, idProd);
            arrAgreementId.add(String.valueOf(tppProductRepo.save(productArrangement).getId()));
        }

        return new ProductResponse(String.valueOf(idProd), arrRegisterId, arrAgreementId);
    }
}
