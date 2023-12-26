package ru.vtb.course.lesson4_1.datatransformer;

import jakarta.annotation.Priority;

import org.springframework.stereotype.Component;
import ru.vtb.course.lesson4_1.AppConfig;
import ru.vtb.course.lesson4_1.LogTransformation;
import ru.vtb.course.lesson4_1.model.Model;

import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

@Component
@Priority(2)
public class DateTransformer implements DataTransformerable<Model> {
    static final Logger logger = Logger.getLogger(DateTransformer.class.getName());
    @Override
    @LogTransformation
    public Model transform(Model model) {
        List<String> tmpStrArr = model.getDataArr();
        ListIterator<String> iterator = tmpStrArr.listIterator();
        String tmp = "";
        while (iterator.hasNext()) {
            tmp = iterator.next();
            if (tmp.split(AppConfig.separator)[4].isBlank()) {
                iterator.remove();
                logger.warning("Empty Date, string removed: " + tmp);
            }
        }
        model.setDataArr(tmpStrArr);
        //System.out.println("====== DateTransformer ==========");
        return model;
    }
}
