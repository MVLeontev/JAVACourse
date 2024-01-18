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


    @ManyToOne()
    @JoinColumn(name = "product_class_code")
    private TppRefProductClass productClassCode;


    @ManyToOne()
    @JoinColumn(name = "account_type")
    private TppRefAccountType accountType;


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

    public TppRefProductClass getProductClassCode() {
        return productClassCode;
    }

    public void setProductClassCode(TppRefProductClass productClassCode) {
        this.productClassCode = productClassCode;
    }

    public TppRefAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(TppRefAccountType accountType) {
        this.accountType = accountType;
    }
}
