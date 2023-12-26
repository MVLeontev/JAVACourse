package ru.vtb.course.lesson4_1.datatransformer;

import jakarta.annotation.Priority;
import org.springframework.stereotype.Component;
import ru.vtb.course.lesson4_1.AppConfig;
import ru.vtb.course.lesson4_1.LogTransformation;
import ru.vtb.course.lesson4_1.model.Model;

import java.util.List;
import java.util.ListIterator;
// переводит первые буквы ФИО (1-2-3 элементы строки) в верхний регистр
@Component
@Priority(3)
public class FioTransformer implements DataTransformerable<Model> {
    @Override
    @LogTransformation
    public Model transform(Model model) {
        List<String> tmpStrArr = model.getDataArr();
        String tmp = "";
        String newString = "";
        String[] parts;
        ListIterator<String> iterator = tmpStrArr.listIterator();
        while (iterator.hasNext()) {
            tmp = iterator.next();
            parts = tmp.split(AppConfig.separator);
            newString = "";
            for (int i = 0; i < parts.length; i++) {
                if (i == 1 || i == 2 || i == 3) {
                    newString += parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
                } else newString += parts[i];
                if (i < parts.length - 1) newString += AppConfig.separator;
            }
            iterator.set(newString);
        }
        model.setDataArr(tmpStrArr);
        //System.out.println("====== FioTransformer ==========");
        return model;
    }
}
