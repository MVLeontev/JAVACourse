package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.TppProduct;
import ru.vtb.course.lesson5.repositories.TppProductRepo;

@Service
public class FindProductService implements FindProductServiceable {

    private TppProductRepo productRepo;

    public FindProductService(TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public TppProduct findProduct(Long id) {
        TppProduct prod = productRepo.findById(id).orElseThrow(() -> new NotFoundException("Не найден экземпляр продукта <InstanseId> = "+id));
        return prod;
    }
}
