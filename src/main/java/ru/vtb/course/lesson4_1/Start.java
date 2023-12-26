package ru.vtb.course.lesson4_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.vtb.course.lesson4_1.datareader.DataReader;
import ru.vtb.course.lesson4_1.datatransformer.AppTransformer;
import ru.vtb.course.lesson4_1.datatransformer.DateTransformer;
import ru.vtb.course.lesson4_1.datatransformer.FioTransformer;
import ru.vtb.course.lesson4_1.datawriter.DataWriter;
import ru.vtb.course.lesson4_1.model.Model;
import ru.vtb.course.lesson4_1.repositories.LoginsRepo;
import ru.vtb.course.lesson4_1.repositories.UsersRepo;

import java.text.ParseException;

@SpringBootApplication(scanBasePackages = "ru.vtb.course.lesson4_1")
public class Start{

	public static void main(String[] args) throws ParseException {
		ApplicationContext context = SpringApplication.run(Start.class, args);

		context.getBean(OperationMaker.class).make();

	}

}
