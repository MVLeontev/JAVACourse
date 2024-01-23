package ru.vtb.course.lesson5.repositories;

import jakarta.persistence.*;

@Entity
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;
    @Column(name = "valuess")
    private String value;
    @Column(name = "register_type_name")
    private String registerTypeName;


    @Column(name = "product_class_code")
    private String productClassCode;

    @Column(name = "account_type")
    private String accountType;


    public TppRefProductRegisterType() {
    }

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegisterTypeName() {
        return registerTypeName;
    }

    public void setRegisterTypeName(String registerTypeName) {
        this.registerTypeName = registerTypeName;
    }

    public String  getProductClassCode() {
        return productClassCode;
    }

    public void setProductClassCode(String  productClassCode) {
        this.productClassCode = productClassCode;
    }

    public String  getAccountType() {
        return accountType;
    }

    public void setAccountType(String  accountType) {
        this.accountType = accountType;
    }
}
