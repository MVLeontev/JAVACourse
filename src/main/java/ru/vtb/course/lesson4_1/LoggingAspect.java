package ru.vtb.course.lesson4_1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("@annotation(LogTransformation)")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        String methodSign = point.getSignature().toString();
        Object [] args = point.getArgs();
        Date currDate = new Date();
        Object returnedValue = point.proceed();
        logger.info("=============== LogTransformation begin =====================");
        logger.info(currDate.toString());
        logger.info(methodSign);
        logger.info("INPUT:");
        logger.info(args[0].toString());
        logger.info("OUTPUT:");
        logger.info(returnedValue.toString());
        logger.info("=============== LogTransformation end  =====================");
        return returnedValue;
    }
}
