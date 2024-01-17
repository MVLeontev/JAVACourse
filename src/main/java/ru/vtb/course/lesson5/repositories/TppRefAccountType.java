package ru.vtb.course.lesson5.repositories;

import jakarta.persistence.*;

@Entity
@Table(name = "tpp_ref_account_type")
public class TppRefAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;
    @Column(name = "value")
    private String value;

    public TppRefAccountType() {
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
}
