package ru.vtb.course.lesson5.services;

import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.repositories.TppProductRegister;

public interface PrepareAccountServiceable {
    public TppProductRegister prepareAccount(AccountRequest accountRequest);
}
