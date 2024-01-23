package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.AccountPool;
import ru.vtb.course.lesson5.repositories.AccountPoolRepo;

@Service
public class FindAccountNumberService implements FindAccountNumberServiceable {
    private final AccountPoolRepo accountPoolRepo;

    public FindAccountNumberService(AccountPoolRepo accountPoolRepo) {
        this.accountPoolRepo = accountPoolRepo;
    }

    @Override
    public String findAccountNumber(String branchCode, String currencyCode, String mdmCode, String priorityCode, String registryTypeCode) {
        AccountPool[] accArr = accountPoolRepo.findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                branchCode, currencyCode, mdmCode, priorityCode, registryTypeCode);
        if (accArr.length == 0) {
            throw new NotFoundException("Не найден счет в пуле счетов со следующими данными: <branchCode> "+branchCode+
                    ", <currencyCode> "+currencyCode+", <mdmCode> "+mdmCode+", <priorityCode> "+priorityCode+", <registryTypeCode> "+registryTypeCode);
        }
        return accArr[0].getAccounts().split(",")[0];
    }
}
