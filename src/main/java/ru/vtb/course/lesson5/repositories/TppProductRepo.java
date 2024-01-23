package ru.vtb.course.lesson5.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TppProductRepo extends JpaRepository<TppProduct, Long> {

    @Transactional
    @Query(
       value="select trprt.valuess from tpp_product tp join tpp_ref_product_class trpc on tp.product_code_id = trpc.internal_id join tpp_ref_product_register_type trprt on trpc.valuess = trprt.product_class_code where tp.id = :productId"
      ,nativeQuery = true)
    public ArrayList<String> getProductRegisterTypeCode(Long productId);

    public TppProduct[] findByNumber(String number);

    public TppProduct[] findByNumberAndAgreementIdIsNotNull(String number);
    public TppProduct[] findByNumberAndAgreementIdIsNull(String number);

}
