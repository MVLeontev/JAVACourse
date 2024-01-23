package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.TppRefProductClass;
import ru.vtb.course.lesson5.repositories.TppRefProductClassRepo;
import ru.vtb.course.lesson5.repositories.TppRefProductRegisterType;
import ru.vtb.course.lesson5.repositories.TppRefProductRegisterTypeRepo;

import java.util.Arrays;

@Service
public class FindRegisterTypeRecService implements FindRegisterTypeRecServiceable {
    private TppRefProductClassRepo refProductClassRepo;
    private TppRefProductRegisterTypeRepo refProductRegisterTypeRepo;

    public FindRegisterTypeRecService(TppRefProductClassRepo refProductClassRepo, TppRefProductRegisterTypeRepo refProductRegisterTypeRepo) {
        this.refProductClassRepo = refProductClassRepo;
        this.refProductRegisterTypeRepo = refProductRegisterTypeRepo;
    }

    @Override
    public TppRefProductRegisterType[] findRegisterTypeRec(ProductRequest pr) {
        TppRefProductClass pc = refProductClassRepo.findByValue(pr.getProductCode());

        if (pc == null) throw new NotFoundException("КодПродукта <"+pr.getProductCode()+"> не найдено в Каталоге продуктов");
        TppRefProductRegisterType[] rtArr = refProductRegisterTypeRepo.findByProductClassCode(pc.getValue());

        if ( rtArr.length == 0 ||
                Arrays.stream(rtArr).filter(a -> a.getAccountType().equals("Клиентский")).findAny().isEmpty()
        ) throw new NotFoundException("КодПродукта <"+pr.getProductCode()+"> не найдено в Каталоге продуктов для типа счета <Клиентский>");

        return rtArr;
    }
}
