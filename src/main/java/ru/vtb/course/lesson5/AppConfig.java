package ru.vtb.course.lesson5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ru.vtb.course.lesson5")
@EnableJpaRepositories
public class AppConfig {
}
