package ru.vtb.course.lesson5.services.common;

import ru.vtb.course.lesson5.repositories.TppProduct;

public interface FindProductServiceable {
    public TppProduct findProduct(Long id);
}
