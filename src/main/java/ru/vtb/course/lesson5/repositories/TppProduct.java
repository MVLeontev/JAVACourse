package ru.vtb.course.lesson5.repositories;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tpp_product")
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "agreement_id")
    private Long agreementId;
    @Column(name = "product_code_id")
    private Long productCodeId;  // здесь нужна ссылка на tpp_ref_product_class
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "type")
    private String type;
    @Column(name = "number")
    private String number;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "date_of_conclusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfConclusion;
    @Column(name = "start_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Column(name = "end_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Column(name = "days")
    private Integer days;
    @Column(name = "penalty_rate")
    private BigDecimal penaltyRate;
    @Column(name = "nso")
    private BigDecimal nso;
    @Column(name = "threshold_amount")
    private BigDecimal thresholdAmount;
    @Column(name = "requisite_type")
    private String requisiteType;
    @Column(name = "interest_rate_type")
    private String interestRateType;
    @Column(name = "tax_rate")
    private BigDecimal taxRate;
    @Column(name = "reason_close")
    private String reasonClose;
    @Column(name = "state")
    private String state;

    public TppProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Long agreementId) {
        this.agreementId = agreementId;
    }

    public Long getProductCodeId() {
        return productCodeId;
    }

    public void setProductCodeId(Long productCodeId) {
        this.productCodeId = productCodeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDateOfConclusion() {
        return dateOfConclusion;
    }

    public void setDateOfConclusion(Date dateOfConclusion) {
        this.dateOfConclusion = dateOfConclusion;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getPenaltyRate() {
        return penaltyRate;
    }

    public void setPenaltyRate(BigDecimal penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    public BigDecimal getNso() {
        return nso;
    }

    public void setNso(BigDecimal nso) {
        this.nso = nso;
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public String getRequisiteType() {
        return requisiteType;
    }

    public void setRequisiteType(String requisiteType) {
        this.requisiteType = requisiteType;
    }

    public String getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(String interestRateType) {
        this.interestRateType = interestRateType;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getReasonClose() {
        return reasonClose;
    }

    public void setReasonClose(String reasonClose) {
        this.reasonClose = reasonClose;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
