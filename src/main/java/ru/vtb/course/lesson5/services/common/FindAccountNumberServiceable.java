package ru.vtb.course.lesson5.services.common;

public interface FindAccountNumberServiceable {
    public String findAccountNumber(String branchCode,
                                    String currencyCode,
                                    String mdmCode,
                                    String priorityCode,
                                    String registryTypeCode);
}
