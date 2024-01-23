package ru.vtb.course.lesson5.services;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.ProductInstanceArrangement;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.repositories.TppProduct;

import ru.vtb.course.lesson5.repositories.TppRefProductClassRepo;
import ru.vtb.course.lesson5.services.common.GenerateClientIdByMdmServiceable;



@Service
public class PrepareProductService implements PrepareProductServiceable{

    private final TppRefProductClassRepo refProductClassRepo;
    private final GenerateClientIdByMdmServiceable generateClientIdByMdmServisable;



    public PrepareProductService(TppRefProductClassRepo refProductClassRepo, GenerateClientIdByMdmServiceable generateClientIdByMdmServisable) {
        this.refProductClassRepo = refProductClassRepo;
        this.generateClientIdByMdmServisable = generateClientIdByMdmServisable;
    }
    @Override
    public TppProduct prepareProduct(ProductRequest pr) {
        TppProduct mainProd = new TppProduct();
        //product_code_id
        mainProd.setProductCodeId( refProductClassRepo.findByValue(pr.getProductCode()) );
        mainProd.setClientId(generateClientIdByMdmServisable.generateClientIdByMdm(pr.getMdmCode()));
        mainProd.setType(pr.getProductType().name());
        mainProd.setNumber(pr.getContractNumber());
        mainProd.setPriority(pr.getPriority());
        mainProd.setDateOfConclusion(pr.getContractDate());
        mainProd.setPenaltyRate(pr.getInterestRatePenalty());
        mainProd.setNso(pr.getMinimalBalance());
        mainProd.setThresholdAmount(pr.getThresholdAmount());
        mainProd.setRequisiteType(pr.getAccountingDetails());
        mainProd.setInterestRateType(pr.getRateType());
        mainProd.setTaxRate(pr.getTaxPercentageRate());

        return mainProd;
    }
@Override
    public TppProduct prepareProductArrangement(ProductInstanceArrangement pr, Long agreementId){
        TppProduct arrProd = new TppProduct();
        arrProd.setAgreementId(agreementId);
        arrProd.setType(pr.getArrangementType().name());
        arrProd.setNumber(pr.getNumber());
        arrProd.setStartDateTime(pr.getOpeningDate());
        arrProd.setEndDateTime(pr.getClosingDate());
        arrProd.setDays(pr.getValidityDuration());
        arrProd.setReasonClose(pr.getCancellationReason());
        arrProd.setState(pr.getStatus());

        return arrProd;
    }

}
