package ru.vtb.course.lesson5.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.ProductInstanceArrangement;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.dto.ProductResponse;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.*;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductService implements ProductServiceable {
    private final TppProductRepo tppProductRepo;
    private final TppRefProductClassRepo refProductClassRepo;
    private final TppRefProductRegisterTypeRepo refProductRegisterTypeRepo;
    private final PrepareProductServiceable prepareProductServiceable;
    private final PrepareAccountServiceable prepareAccountServiceable;

    public ProductService(TppProductRepo tppProductRepo, TppRefProductClassRepo refProductClassRepo, TppRefProductRegisterTypeRepo refProductRegisterTypeRepo, PrepareProductServiceable prepareProductServiceable, PrepareAccountServiceable prepareAccountServiceable) {
        this.tppProductRepo = tppProductRepo;
        this.refProductClassRepo = refProductClassRepo;
        this.refProductRegisterTypeRepo = refProductRegisterTypeRepo;
        this.prepareProductServiceable = prepareProductServiceable;
        this.prepareAccountServiceable = prepareAccountServiceable;
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
        checkProductArrangementByNum(request);

        if (request.getInstanceId() == null) {
            //проверим на дубли по номеру ЭП (шаг 2)
            checkProductByNum(request.getContractNumber());
            //шаг 4
            TppRefProductRegisterType[] registryArr = findRegisterTypeRec(request);
            // добавим запись ЭП (шаг 5)
            tppProduct = prepareProductServiceable.prepareProduct(request);
            tppProduct.setProductCodeId(refProductClassRepo.findByValue(request.getProductCode()));
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
                tpr.setProductId(findProduct(ar.getInstanceId()));
                arrRegisterId.add(String.valueOf( prepareAccountServiceable.saveAccount(tpr) ) );
            }

        } else {
            idProd = request.getInstanceId();
            tppProduct = findProduct(idProd);
            TppProductRegister[] arrPr = prepareAccountServiceable.findByProdId(tppProduct);
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

    @Override
    public void checkProductArrangementByNum(ProductRequest pr) {
        for (ProductInstanceArrangement pa : pr.getInstanceArrangement()) {
            String num = pa.getNumber();
            TppProduct[] p = tppProductRepo.findByNumberAndAgreementIdIsNotNull(num);
            if (p.length > 0) {
                throw new DuplicateException("Параметр № Дополнительного соглашения (сделки) Number  <"+num+"> уже существует для ЭП с ИД <"+p[0].getAgreementId()+">.");
            }
        }
    }

    @Override
    public TppProduct findProduct(Long id) {
        TppProduct prod = tppProductRepo.findById(id).orElseThrow(() -> new NotFoundException("Не найден экземпляр продукта <InstanseId> = "+id));
        return prod;
    }
    @Override
    public void checkProductByNum(String num) {
        TppProduct[] p = tppProductRepo.findByNumberAndAgreementIdIsNull(num);
        if (p.length > 0) {
            throw new DuplicateException("Параметр ContractNumber № договора <"+num+"> уже существует для ЭП с ИД <"+p[0].getId()+">.");
        }
    }

    @Override
    public TppRefProductRegisterType[] findRegisterTypeRec(ProductRequest pr) {
        TppRefProductClass pc = refProductClassRepo.findByValue(pr.getProductCode());

        if (pc == null) {
            throw new NotFoundException("КодПродукта <"+pr.getProductCode()+"> не найдено в Каталоге продуктов");
        }
        TppRefProductRegisterType[] rtArr = refProductRegisterTypeRepo.findByProductClassCode(pc.getValue());

        if ( rtArr.length == 0 || Arrays.stream(rtArr).filter(a -> a.getAccountType().equals("Клиентский")).findAny().isEmpty() ) {
            throw new NotFoundException("КодПродукта <"+pr.getProductCode()+"> не найдено в Каталоге продуктов для типа счета <Клиентский>");
        }
        return rtArr;
    }

    @Override
    public void checkAccountByProductRegisterTypeCode(AccountRequest accountRequest) {
        ArrayList<String> regTypeArrVal = tppProductRepo.getProductRegisterTypeCode(accountRequest.getInstanceId());
        if (regTypeArrVal.isEmpty() || !regTypeArrVal.contains(accountRequest.getRegistryTypeCode()) ) {
            throw new NotFoundException("Код продукта <"+accountRequest.getRegistryTypeCode()+"> не найден в каталоге продуктов для данного типа регистра");
        }
    }
}
