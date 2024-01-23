package ru.vtb.course.lesson5.services;

import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.dto.ProductResponse;

public interface ProductServiceable {
    public ProductResponse makeProduct(ProductRequest request);
}
