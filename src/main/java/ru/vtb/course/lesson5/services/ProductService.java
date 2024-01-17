package ru.vtb.course.lesson5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.dto.ProductResponse;
import ru.vtb.course.lesson5.repositories.TppProduct;

@Service
public class ProductService {
    private TppProduct tppProduct;
    @Autowired
    public ProductService(TppProduct tppProduct) {
        this.tppProduct = tppProduct;
    }


    public ProductResponse makeProduct(ProductRequest productRequest){


        return new ProductResponse();
    }
}
