package ru.vtb.course.lesson5.services.common;

import org.springframework.stereotype.Service;

@Service
public class GenerateClientIdByMdmService implements GenerateClientIdByMdmServiceable {
    @Override
    public Long generateClientIdByMdm(String mdmCode) {
        return Long.valueOf(mdmCode);
    }
}
