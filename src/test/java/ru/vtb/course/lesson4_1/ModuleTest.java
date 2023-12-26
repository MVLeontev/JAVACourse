package ru.vtb.course.lesson4_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vtb.course.lesson4_1.datareader.DataReader;
import ru.vtb.course.lesson4_1.datatransformer.AppTransformer;
import ru.vtb.course.lesson4_1.datatransformer.DateTransformer;
import ru.vtb.course.lesson4_1.datatransformer.FioTransformer;
import ru.vtb.course.lesson4_1.model.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ModuleTest {

    @Test
    @DisplayName("Тестирование компоненты чтения данных из файла в Модель")
    public void readDataTest() throws IOException {
        String sp = AppConfig.separator;
        File fold = new File("./", "test");
        if (!fold.exists()) fold.mkdir();
        FileWriter fw = new FileWriter("./test/test.txt");
        String testLine1 = "iv"+sp+"ivanov"+sp+"ivan"+sp+"ivanovich"+sp+"01.01.2001"+sp+"mobile";
        fw.write(testLine1);
        fw.close();
        Model model;
        DataReader dataReader = new DataReader();
        model = dataReader.read("./test");
        Assertions.assertTrue(model.getDataArr().get(0).equals(testLine1+sp+"test.txt"), "Ошибка чтения данных из файла"  );
    }

    @Test
    @DisplayName("Тестирование преобразования ФИО в верхний регистр")
    public void checkFioTest() {
        String sp = AppConfig.separator;
        String testLine1 = "iv"+sp+"ivanov"+sp+"ivan"+sp+"ivanovich"+sp+"01.01.2001"+sp+"mobile";
        Model model = new Model();
        model.setDataArr(List.of(testLine1));
        FioTransformer ft = new FioTransformer();
        model = ft.transform(model);
        Assertions.assertTrue(
                model.getDataArr().get(0).split(sp)[1].equals("Ivanov") &&
                        model.getDataArr().get(0).split(sp)[2].equals("Ivan") &&
                        model.getDataArr().get(0).split(sp)[3].equals("Ivanovich")
                        , "Ошибка возведения ФИО в верхний регистр"
                        );
    }

    @Test
    @DisplayName("Тестирование проверки наличия даты в импортированной строке")
    public void checkDateTest() {
        //проверяется наличие даты в позиции 4 строки. Если даты нет, строка выбрасывается из модели
        String sp = AppConfig.separator;
        String testLine1 = "iv"+sp+"ivanov"+sp+"ivan"+sp+"ivanovich"+sp+"01.01.2001"+sp+"mobile";
        String testLine2 = "iv"+sp+"ivanov"+sp+"ivan"+sp+"ivanovich"+sp+""+sp+"mobile";
        Model model = new Model();
        model.setDataArr(List.of(testLine1, testLine2));
        DateTransformer dt = new DateTransformer();
        model = dt.transform(model);
        Assertions.assertTrue(model.getDataArr().size() == 1, "Ошибка удаления строки с пустой датой");
    }

    @Test
    @DisplayName("Тестирование замены наименования приложения в позиции 6")
    public void checkAppTest() {
        //в позиции 6 ожидается web или mobile. Если иное, то добавляется префикс other;
        String sp = AppConfig.separator;
        String testLine1 = "iv"+sp+"ivanov"+sp+"ivan"+sp+"ivanovich"+sp+"01.01.2001"+sp+"mobile";
        String testLine2 = "iv"+sp+"ivanov"+sp+"ivan"+sp+"ivanovich"+sp+"02.02.2001"+sp+"web";
        String testLine3 = "iv"+sp+"ivanov"+sp+"ivan"+sp+"ivanovich"+sp+"02.02.2001"+sp+"email";
        Model model = new Model();
        model.setDataArr(List.of(testLine1, testLine2, testLine3));
        AppTransformer at = new AppTransformer();
        model = at.transform(model);
        Assertions.assertEquals("mobile", model.getDataArr().get(0).split(sp)[5], "Ошибка проверки 1 строки: в позиции 6 ожидалось mobile");
        Assertions.assertEquals("web", model.getDataArr().get(1).split(sp)[5], "Ошибка проверки 2 строки: в позиции 6 ожидалось web");
        Assertions.assertEquals("other:email", model.getDataArr().get(2).split(sp)[5], "Ошибка проверки 3 строки: в позиции 6 ожидалось other:email");
    }

}
