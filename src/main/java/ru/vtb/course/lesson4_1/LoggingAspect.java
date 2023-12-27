package ru.vtb.course.lesson4_1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
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
        Method meth = ((MethodSignature)point.getSignature()).getMethod();
        String logFile = meth.getAnnotation(LogTransformation.class).value();
        Date currDate = new Date();
        Object returnedValue;
        if (logFile.isBlank()) {
            logger.info("=============== LogTransformation begin =====================");
            logger.info(currDate.toString());
            logger.info(methodSign);
            logger.info("INPUT:");
            logger.info(Arrays.toString(args));
            returnedValue = point.proceed();
            logger.info("OUTPUT:");
            logger.info(returnedValue.toString());
            logger.info("=============== LogTransformation end  =====================");
        } else {
            //создадим свой файл, переданный в параметре аннотации
            File folder = new File("./","log");
            if (!folder.exists()) folder.mkdir();
            FileWriter fw = new FileWriter("./log/"+logFile, true);
            fw.write(currDate.toString() + " " + methodSign + '\n');
            fw.write("Input: " + Arrays.toString(args) + '\n');
            returnedValue = point.proceed();
            fw.write("Output: " + returnedValue.toString() + '\n');
            fw.close();
        }
        return returnedValue;
    }
}
