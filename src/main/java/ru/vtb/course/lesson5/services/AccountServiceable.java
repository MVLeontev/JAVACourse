package ru.vtb.course.lesson5.services;

import ru.vtb.course.lesson5.dto.AccountRequest;
import ru.vtb.course.lesson5.dto.AccountResponse;

public interface AccountServiceable {
    public AccountResponse makeAccount(AccountRequest accountRequest);
    public void checkAccountByProductAndType(AccountRequest accountRequest);
}
