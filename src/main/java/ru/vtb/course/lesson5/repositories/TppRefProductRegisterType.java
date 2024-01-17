package ru.vtb.course.lesson5.repositories;

import jakarta.persistence.*;

@Entity
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;
    @Column(name = "value")
    private String value;
    @Column(name = "register_type_name")
    private String registerTypeName;
    @Column(name = "product_class_code")
    private Long productClassCode;
    @Column(name = "account_type")
    private Long accountType;
}
