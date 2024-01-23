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
    private final TppProductRepo tppProductRepo;
    private final TppProductRegisterRepo accountRepo;
    private final PrepareProductServiceable prepareProductServiceable;
    private final PrepareAccountServiceable prepareAccountServiceable;
    private final FindProductServiceable findProductServiceable;
    private final CheckProductByNumServiceable checkProductByNumServiceable;
    private final CheckProductArrangementByNumServiceable checkProductArrangementByNumServiceable;
    private final FindRegisterTypeRecServiceable findRegisterTypeRecServiceable;


    public ProductService(TppProductRepo tppProductRepo, TppProductRegisterRepo accountRepo, PrepareProductServiceable prepareProductServiceable, PrepareAccountServiceable prepareAccountServiceable, FindProductServiceable findProductServiceable, CheckProductByNumServiceable checkProductByNumServiceable, CheckProductArrangementByNumServiceable checkProductArrangementByNumServiceable, FindRegisterTypeRecServiceable findRegisterTypeRecServiceable) {
        this.tppProductRepo = tppProductRepo;
        this.accountRepo = accountRepo;
        this.prepareProductServiceable = prepareProductServiceable;
        this.prepareAccountServiceable = prepareAccountServiceable;
        this.findProductServiceable = findProductServiceable;
        this.checkProductByNumServiceable = checkProductByNumServiceable;
        this.checkProductArrangementByNumServiceable = checkProductArrangementByNumServiceable;
        this.findRegisterTypeRecServiceable = findRegisterTypeRecServiceable;
    }

    @Override
    @Transactional
    public ProductResponse makeProduct(ProductRequest request) {
        Long idProd;
        TppProduct tppProduct;
        ArrayList<String> arrRegisterId = new ArrayList<>();
        ArrayList<String> arrAgreementId = new ArrayList<>();

        //Проверка таблицы ДС на дубли (шаг 3)
        //вынес вверх потому как этот шаг должен выполняться для всех случаев (instanceId is null и instanceId is not null)
        checkProductArrangementByNumServiceable.checkProductArrangementByNum(request);

        if (request.getInstanceId() == null) {
            //проверим на дубли по номеру ЭП (шаг 2)
            checkProductByNumServiceable.checkProductByNum(request.getContractNumber());
            //шаг 4
            TppRefProductRegisterType[] registryArr = findRegisterTypeRecServiceable.findRegisterTypeRec(request);
            // добавим запись ЭП (шаг 5)
            tppProduct = prepareProductServiceable.prepareProduct(request);
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
                TppProductRegister tpr = prepareAccountServiceable.prepareAccount(ar);
                arrRegisterId.add(String.valueOf( accountRepo.save(tpr).getId() ) );
            }

        } else {
            idProd = request.getInstanceId();
            tppProduct = findProductServiceable.findProduct(idProd);
            TppProductRegister[] arrPr = accountRepo.findByProductId(tppProduct);
            for (int i = 0; i < arrPr.length; i++) {
                arrRegisterId.add(String.valueOf(arrPr[i].getId()));
            }
            TppProduct[] arrProdAgr = tppProductRepo.findByAgreementId(idProd);
            for (int i = 0; i < arrProdAgr.length; i++) {
                arrAgreementId.add(String.valueOf(arrProdAgr[i].getId()));
            }
        }
        //обработаем доп.соглашения
        for (ProductInstanceArrangement pia : request.getInstanceArrangement()) {
            TppProduct productArrangement = prepareProductServiceable.prepareProductArrangement(pia, idProd);
            arrAgreementId.add(String.valueOf(tppProductRepo.save(productArrangement).getId()));
        }

        return new ProductResponse(String.valueOf(idProd), arrRegisterId, arrAgreementId);
    }
}
