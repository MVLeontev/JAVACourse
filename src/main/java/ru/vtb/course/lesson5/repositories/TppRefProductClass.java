package ru.vtb.course.lesson5.repositories;

import jakarta.persistence.*;

@Entity
@Table(name = "tpp_ref_product_class")
public class TppRefProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;
    @Column(name = "valuess")
    private String value;
    @Column(name = "gbl_code")
    private String gblCode;
    @Column(name = "gbl_name")
    private String gblName;
    @Column(name = "product_row_code")
    private String productRowCode;
    @Column(name = "product_row_name")
    private String productRowName;
    @Column(name = "subclass_code")
    private String subclassCode;
    @Column(name = "subclass_name")
    private String subclassName;

    public TppRefProductClass() {
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

    public String getGblCode() {
        return gblCode;
    }

    public void setGblCode(String gblCode) {
        this.gblCode = gblCode;
    }

    public String getGblName() {
        return gblName;
    }

    public void setGblName(String gblName) {
        this.gblName = gblName;
    }

    public String getProductRowCode() {
        return productRowCode;
    }

    public void setProductRowCode(String productRowCode) {
        this.productRowCode = productRowCode;
    }

    public String getProductRowName() {
        return productRowName;
    }

    public void setProductRowName(String productRowName) {
        this.productRowName = productRowName;
    }

    public String getSubclassCode() {
        return subclassCode;
    }

    public void setSubclassCode(String subclassCode) {
        this.subclassCode = subclassCode;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }
}
