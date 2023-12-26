package ru.vtb.course.lesson4_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vtb.course.lesson4_1.datareader.DataReadable;
import ru.vtb.course.lesson4_1.datareader.DataReader;
import ru.vtb.course.lesson4_1.datatransformer.AppTransformer;
import ru.vtb.course.lesson4_1.datatransformer.DataTransformerable;
import ru.vtb.course.lesson4_1.datatransformer.DateTransformer;
import ru.vtb.course.lesson4_1.datatransformer.FioTransformer;
import ru.vtb.course.lesson4_1.datawriter.DataWritable;
import ru.vtb.course.lesson4_1.datawriter.DataWriter;
import ru.vtb.course.lesson4_1.model.Model;

@SpringBootTest
class Lesson41ApplicationTests {

//	@Autowired
//	DataTransformerable<Model>[] arrTransformer;
//	@Autowired
//	DataReadable<Model> dataReader;
//	@Autowired
//	DataWritable<Model> dataWriter;
	@Autowired
	OperationMaker op;

	@Test
	@DisplayName("Проверка корректности и порядка привязки бинов DataTransformerable в массив в соответствии с указанными Priority")
	public void checkPriorityOfTransformers() {
		Assertions.assertTrue( op.arrTransformer[0] instanceof AppTransformer, "Ошибка порядка следования для класса AppTransformer");
		Assertions.assertTrue( op.arrTransformer[1] instanceof DateTransformer, "Ошибка порядка следования для класса DateTransformer");
		Assertions.assertTrue( op.arrTransformer[2] instanceof FioTransformer, "Ошибка порядка следования для класса FioTransformer");
	}

	@Test
	@DisplayName("Проверка корректности привязки бина DataReader")
	public void checkBeanDataReader() {
		Assertions.assertTrue( op.dataReader != null && op.dataReader instanceof DataReader, "Ошибка привязки бина DataReader" );
	}

	@Test
	@DisplayName("Проверка корректности привязки бина DataWriter")
	public void checkBeanDataWriter() {
		Assertions.assertTrue( op.dataWriter != null && op.dataWriter instanceof DataWriter, "Ошибка привязки бина DataWriter" );
	}


}
