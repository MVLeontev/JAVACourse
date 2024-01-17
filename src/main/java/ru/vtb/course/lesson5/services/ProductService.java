package ru.vtb.course.lesson5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.dto.ProductResponse;
import ru.vtb.course.lesson5.repositories.TppProduct;
import ru.vtb.course.lesson5.repositories.TppProductRepo;

@Service
public class ProductService {
    private TppProductRepo tppProductRepo;

    @Autowired
    public ProductService(TppProductRepo tppProductRepo) {
        this.tppProductRepo = tppProductRepo;
    }


    public ProductResponse makeProduct(ProductRequest productRequest){


        return new ProductResponse("1111", new String[]{"1", "2"}, new String[]{"1", "2"});
    }
}
