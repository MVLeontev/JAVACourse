package ru.vtb.course.lesson4_1.datatransformer;

import jakarta.annotation.Priority;
import org.springframework.stereotype.Component;
import ru.vtb.course.lesson4_1.AppConfig;
import ru.vtb.course.lesson4_1.LogTransformation;
import ru.vtb.course.lesson4_1.model.Model;

import java.util.List;
import java.util.ListIterator;

// проверяет 5 элемент в строке на web или mobile. Если иначе - трансформирует в other + значение
@Component
@Priority(1)
public class AppTransformer implements DataTransformerable<Model> {
    @Override
    @LogTransformation(value = "log_transform.txt")
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
                if (i == 5) {
                    if ( !(parts[i].equalsIgnoreCase("web") || parts[i].equalsIgnoreCase("mobile") )) {
                        newString += "other:" + parts[i];
                    } else newString += parts[i];
                } else newString += parts[i];
                if (i < parts.length - 1) newString += AppConfig.separator;
            }
            iterator.set(newString);
        }
        model.setDataArr(tmpStrArr);
        //System.out.println("====== AppTransformer ==========");
        return model;
    }
}
