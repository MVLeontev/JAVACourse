package ru.vtb.course.lesson5.repositories;

import jakarta.persistence.*;

@Entity
@Table(name = "tpp_product_register")
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "type")
    private String type;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "state")
    private String state;
    @Column(name="account_number")
    private String accountNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", productId=" + productId +
                ", type='" + type + '\'' +
                ", accountId=" + accountId +
                ", currencyCode='" + currencyCode + '\'' +
                ", state='" + state + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
