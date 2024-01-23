package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;

@Service
public class GenerateAccountIdService implements GenerateAccountIdServiceable {
    @Override
    public Long generateAccountId(String accountNum) {
        return Math.abs( (long)accountNum.hashCode());
    }
}
