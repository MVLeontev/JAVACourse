package ru.vtb.course.lesson5.services.common;

import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.repositories.TppRefProductRegisterType;

public interface FindRegisterTypeRecServiceable {
    public TppRefProductRegisterType[] findRegisterTypeRec(ProductRequest pr);
}
