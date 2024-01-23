package ru.vtb.course.lesson5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ProductInstanceArrangement {
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private EnumArrangementType arrangementType;
    private Integer shedulerJobId;
    @NotNull(message = "Имя обязательного параметра <instanceArrangement/number> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <instanceArrangement/number> не заполнено")
    private String number;
    @NotNull(message = "Имя обязательного параметра <instanceArrangement/openingDate> не заполнено")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate openingDate;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate closingDate;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    private String status;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate interestCalculationDate;
    private BigDecimal interestRate;
    private BigDecimal coefficient;
    private EnumCoefficientAction coefficientAction;
    private BigDecimal minimumInterestRate;
    private BigDecimal minimumInterestRateCoefficient;
    private EnumCoefficientAction minimumInterestRateCoefficientAction;
    private BigDecimal maximalnterestRate;
    private BigDecimal maximalnterestRateCoefficient;
    private EnumCoefficientAction maximalnterestRateCoefficientAction;

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

    public EnumArrangementType getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(EnumArrangementType arrangementType) {
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

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public LocalDate getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDate cancelDate) {
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

    public LocalDate getInterestCalculationDate() {
        return interestCalculationDate;
    }

    public void setInterestCalculationDate(LocalDate interestCalculationDate) {
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

    public EnumCoefficientAction getCoefficientAction() {
        return coefficientAction;
    }

    public void setCoefficientAction(EnumCoefficientAction coefficientAction) {
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

    public EnumCoefficientAction getMinimumInterestRateCoefficientAction() {
        return minimumInterestRateCoefficientAction;
    }

    public void setMinimumInterestRateCoefficientAction(EnumCoefficientAction minimumInterestRateCoefficientAction) {
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

    public EnumCoefficientAction getMaximalnterestRateCoefficientAction() {
        return maximalnterestRateCoefficientAction;
    }

    public void setMaximalnterestRateCoefficientAction(EnumCoefficientAction maximalnterestRateCoefficientAction) {
        this.maximalnterestRateCoefficientAction = maximalnterestRateCoefficientAction;
    }

    @Override
    public String toString() {
        return "ProductInstanceArrangement{" +
                "generalAgreementId='" + generalAgreementId + '\'' +
                ", supplementaryAgreementId='" + supplementaryAgreementId + '\'' +
                ", arrangementType=" + arrangementType +
                ", shedulerJobId=" + shedulerJobId +
                ", number='" + number + '\'' +
                ", openingDate=" + openingDate +
                ", closingDate=" + closingDate +
                ", cancelDate=" + cancelDate +
                ", validityDuration=" + validityDuration +
                ", cancellationReason='" + cancellationReason + '\'' +
                ", status='" + status + '\'' +
                ", interestCalculationDate=" + interestCalculationDate +
                ", interestRate=" + interestRate +
                ", coefficient=" + coefficient +
                ", coefficientAction=" + coefficientAction +
                ", minimumInterestRate=" + minimumInterestRate +
                ", minimumInterestRateCoefficient=" + minimumInterestRateCoefficient +
                ", minimumInterestRateCoefficientAction=" + minimumInterestRateCoefficientAction +
                ", maximalnterestRate=" + maximalnterestRate +
                ", maximalnterestRateCoefficient=" + maximalnterestRateCoefficient +
                ", maximalnterestRateCoefficientAction=" + maximalnterestRateCoefficientAction +
                '}';
    }
}
