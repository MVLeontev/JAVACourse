package ru.vtb.course.lesson4_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vtb.course.lesson4_1.datareader.DataReadable;
import ru.vtb.course.lesson4_1.datatransformer.DataTransformerable;
import ru.vtb.course.lesson4_1.datawriter.DataWritable;
import ru.vtb.course.lesson4_1.model.Model;

import java.text.ParseException;

@Component
public class OperationMaker {
    @Autowired
    DataReadable<Model> dataReader;
    @Autowired
    DataTransformerable<Model>[] arrTransformer;
    @Autowired
    DataWritable<Model> dataWriter;

    Model model;

    public void make() throws ParseException {

        model = (Model) dataReader.read("./src/main/resources/files/");
        for (DataTransformerable<Model> m : arrTransformer ) {
            model = m.transform(model);
        }
        dataWriter.saveData(model);

    }

}
