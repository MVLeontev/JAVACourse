package ru.vtb.course.lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountPoolRepo extends JpaRepository<AccountPool, Long> {
    public AccountPool[] findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
            String branchCode,
            String currencyCode,
            String mdmCode,
            String priorityCode,
            String registryTypeCode
            );

}
