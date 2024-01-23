package ru.vtb.course.lesson5.services;

import ru.vtb.course.lesson5.dto.ProductInstanceArrangement;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.repositories.TppProduct;

public interface PrepareProductServiceable {
    public TppProduct prepareProduct(ProductRequest pr);
    public TppProduct prepareProductArrangement(ProductInstanceArrangement pr, Long agreementId);
}
