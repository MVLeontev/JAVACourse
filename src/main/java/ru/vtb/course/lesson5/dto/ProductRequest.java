package ru.vtb.course.lesson5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductRequest {
    private Long instanceId;
    @NotNull(message = "Имя обязательного параметра <productType> не заполнено")
    private EnumProductType productType;
    @NotNull(message = "Имя обязательного параметра <productCode> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <productCode> не заполнено")
    private String productCode;
    @NotNull(message = "Имя обязательного параметра <registerType> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <registerType> не заполнено")
    private String registerType;
    @NotNull(message = "Имя обязательного параметра <mdmCode> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <mdmCode> не заполнено")
    private String mdmCode;
    @NotNull(message = "Имя обязательного параметра <contractNumber> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <contractNumber> не заполнено")
    private String contractNumber;
    @NotNull(message = "Имя обязательного параметра <contractDate> не заполнено")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate contractDate;
    @NotNull(message = "Имя обязательного параметра <priority> не заполнено")
    private Integer priority;
    private BigDecimal interestRatePenalty;
    private BigDecimal minimalBalance;
    private BigDecimal thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private BigDecimal taxPercentageRate;
    private BigDecimal technicalOverdraftLimitAmount;
    @NotNull(message = "Имя обязательного параметра <contractId> не заполнено")
    private Long contractId;
    @NotNull(message = "Имя обязательного параметра <branchCode> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <branchCode> не заполнено")
    private String branchCode;
    @NotNull(message = "Имя обязательного параметра <isoCurrencyCode> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <isoCurrencyCode> не заполнено")
    private String isoCurrencyCode;
    @NotNull(message = "Имя обязательного параметра <urgencyCode> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <urgencyCode> не заполнено")
    private String urgencyCode;
    private Long referenceCode;
    private ProductAdditionalPropertiesData additionalPropertiesVip;
    private @Valid ArrayList<ProductInstanceArrangement> instanceArrangement = new ArrayList<>();

    public ProductRequest() {
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public EnumProductType getProductType() {
        return productType;
    }

    public void setProductType(EnumProductType productType) {
        this.productType = productType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getMdmCode() {
        return mdmCode;
    }

    public void setMdmCode(String mdmCode) {
        this.mdmCode = mdmCode;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getInterestRatePenalty() {
        return interestRatePenalty;
    }

    public void setInterestRatePenalty(BigDecimal interestRatePenalty) {this.interestRatePenalty = interestRatePenalty; }

    public BigDecimal getMinimalBalance() {
        return minimalBalance;
    }

    public void setMinimalBalance(BigDecimal minimalBalance) {
        this.minimalBalance = minimalBalance;
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public String getAccountingDetails() {
        return accountingDetails;
    }

    public void setAccountingDetails(String accountingDetails) {
        this.accountingDetails = accountingDetails;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getTaxPercentageRate() {
        return taxPercentageRate;
    }

    public void setTaxPercentageRate(BigDecimal taxPercentageRate) {
        this.taxPercentageRate = taxPercentageRate;
    }

    public BigDecimal getTechnicalOverdraftLimitAmount() {
        return technicalOverdraftLimitAmount;
    }

    public void setTechnicalOverdraftLimitAmount(BigDecimal technicalOverdraftLimitAmount) {
        this.technicalOverdraftLimitAmount = technicalOverdraftLimitAmount;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getIsoCurrencyCode() {
        return isoCurrencyCode;
    }

    public void setIsoCurrencyCode(String isoCurrencyCode) {
        this.isoCurrencyCode = isoCurrencyCode;
    }

    public String getUrgencyCode() {
        return urgencyCode;
    }

    public void setUrgencyCode(String urgencyCode) {
        this.urgencyCode = urgencyCode;
    }

    public Long getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(Long referenceCode) {
        this.referenceCode = referenceCode;
    }

    public ProductAdditionalPropertiesData getAdditionalPropertiesVip() {
        return additionalPropertiesVip;
    }

    public void setAdditionalPropertiesVip(ProductAdditionalPropertiesData additionalPropertiesVip) {
        this.additionalPropertiesVip = additionalPropertiesVip;
    }

    public ArrayList<ProductInstanceArrangement> getInstanceArrangement() {
        return new ArrayList<>(instanceArrangement);
    }

    public void setInstanceArrangement(ArrayList<ProductInstanceArrangement> instanceArrangement) {
        this.instanceArrangement = instanceArrangement;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "instanceId='" + instanceId + '\'' +
                ", productType=" + productType +
                ", productCode='" + productCode + '\'' +
                ", registerType='" + registerType + '\'' +
                ", mdmCode='" + mdmCode + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractDate=" + contractDate +
                ", priority=" + priority +
                ", interestRatePenalty=" + interestRatePenalty +
                ", minimalBalance=" + minimalBalance +
                ", thresholdAmount=" + thresholdAmount +
                ", accountingDetails='" + accountingDetails + '\'' +
                ", rateType='" + rateType + '\'' +
                ", taxPercentageRate=" + taxPercentageRate +
                ", technicalOverdraftLimitAmount=" + technicalOverdraftLimitAmount +
                ", contractId=" + contractId +
                ", branchCode='" + branchCode + '\'' +
                ", isoCurrencyCode='" + isoCurrencyCode + '\'' +
                ", urgencyCode='" + urgencyCode + '\'' +
                ", referenceCode=" + referenceCode +
                ", additionalPropertiesVip=" + additionalPropertiesVip +
                ", instanceArrangement=" + instanceArrangement +
                '}';
    }
}
