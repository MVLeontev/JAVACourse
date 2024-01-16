package ru.vtb.course.lesson5.dto;

import jakarta.validation.constraints.*;

public class AccountRequest {

    @NotNull(message = "Имя обязательного параметра <instanceId> не заполнено")
    @NotBlank(message = "Имя обязательного параметра <instanceId> не заполнено")
    @Min(value = 1, message = "Обязательный параметр <instanceId> должен быть больше 0")
    @Pattern(regexp = "^\\d+$", message = "Обязательный параметр <instanceId> должен содержать только цифры")
    private String instanceId;
    private String registryTypeCode;
    private String accountType;
    private String currencyCode;
    private String branchCode;
    private String priorityCode;
    private String mdmCode;
    private String clientCode;
    private String trainRegion;
    private String counter;
    private String salesCode;

    public AccountRequest() {
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String  instanceId) {
        this.instanceId = instanceId;
    }

    public String getRegistryTypeCode() {
        return registryTypeCode;
    }

    public void setRegistryTypeCode(String registryTypeCode) {
        this.registryTypeCode = registryTypeCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public String getMdmCode() {
        return mdmCode;
    }

    public void setMdmCode(String mdmCode) {
        this.mdmCode = mdmCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getTrainRegion() {
        return trainRegion;
    }

    public void setTrainRegion(String trainRegion) {
        this.trainRegion = trainRegion;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }
}
