package ru.vtb.course.lesson5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInstanceArrangement {
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    @NotNull(message = "Имя обязательного параметра <instanceArrangement/number> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <instanceArrangement/number> не заполнено")
    private String number;
    @NotNull(message = "Имя обязательного параметра <instanceArrangement/openingDate> не заполнено")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date openingDate;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date closingDate;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    private String status;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date interestCalculationDate;
    private BigDecimal interestRate;
    private BigDecimal coefficient;
    private String coefficientAction;
    private BigDecimal minimumInterestRate;
    private BigDecimal minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private BigDecimal maximalnterestRate;
    private BigDecimal maximalnterestRateCoefficient;
    private String maximalnterestRateCoefficientAction;

    public ProductInstanceArrangement() {
    }

    public String getGeneralAgreementId() {
        return generalAgreementId;
    }

    public void setGeneralAgreementId(String generalAgreementId) {
        this.generalAgreementId = generalAgreementId;
    }

    public String getSupplementaryAgreementId() {
        return supplementaryAgreementId;
    }

    public void setSupplementaryAgreementId(String supplementaryAgreementId) {
        this.supplementaryAgreementId = supplementaryAgreementId;
    }

    public String getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(String arrangementType) {
        this.arrangementType = arrangementType;
    }

    public Integer getShedulerJobId() {
        return shedulerJobId;
    }

    public void setShedulerJobId(Integer shedulerJobId) {
        this.shedulerJobId = shedulerJobId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Integer getValidityDuration() {
        return validityDuration;
    }

    public void setValidityDuration(Integer validityDuration) {
        this.validityDuration = validityDuration;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInterestCalculationDate() {
        return interestCalculationDate;
    }

    public void setInterestCalculationDate(Date interestCalculationDate) {
        this.interestCalculationDate = interestCalculationDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public String getCoefficientAction() {
        return coefficientAction;
    }

    public void setCoefficientAction(String coefficientAction) {
        this.coefficientAction = coefficientAction;
    }

    public BigDecimal getMinimumInterestRate() {
        return minimumInterestRate;
    }

    public void setMinimumInterestRate(BigDecimal minimumInterestRate) {
        this.minimumInterestRate = minimumInterestRate;
    }

    public BigDecimal getMinimumInterestRateCoefficient() {
        return minimumInterestRateCoefficient;
    }

    public void setMinimumInterestRateCoefficient(BigDecimal minimumInterestRateCoefficient) {
        this.minimumInterestRateCoefficient = minimumInterestRateCoefficient;
    }

    public String getMinimumInterestRateCoefficientAction() {
        return minimumInterestRateCoefficientAction;
    }

    public void setMinimumInterestRateCoefficientAction(String minimumInterestRateCoefficientAction) {
        this.minimumInterestRateCoefficientAction = minimumInterestRateCoefficientAction;
    }

    public BigDecimal getMaximalnterestRate() {
        return maximalnterestRate;
    }

    public void setMaximalnterestRate(BigDecimal maximalnterestRate) {
        this.maximalnterestRate = maximalnterestRate;
    }

    public BigDecimal getMaximalnterestRateCoefficient() {
        return maximalnterestRateCoefficient;
    }

    public void setMaximalnterestRateCoefficient(BigDecimal maximalnterestRateCoefficient) {
        this.maximalnterestRateCoefficient = maximalnterestRateCoefficient;
    }

    public String getMaximalnterestRateCoefficientAction() {
        return maximalnterestRateCoefficientAction;
    }

    public void setMaximalnterestRateCoefficientAction(String maximalnterestRateCoefficientAction) {
        this.maximalnterestRateCoefficientAction = maximalnterestRateCoefficientAction;
    }
}
