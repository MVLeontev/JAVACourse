package ru.vtb.course.lesson4_1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
@ComponentScan(   value = "ru.vtb.course.lesson4_1")
@EnableAspectJAutoProxy
public class AppConfig {
    public static final String separator = ";";
    public static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
}
