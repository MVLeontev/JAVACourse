package ru.vtb.course.lesson4_1.datareader;

import org.springframework.stereotype.Component;
import ru.vtb.course.lesson4_1.AppConfig;
import ru.vtb.course.lesson4_1.model.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DataReader implements DataReadable<Model> {
    @Override
    public Model read(String pathFold) {
        Model model = new Model();
        List<String> tmpStrArr = new ArrayList<>();
        try {
            File f = new File(pathFold);
            for (File file : f.listFiles()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    tmpStrArr.add(scanner.nextLine() + AppConfig.separator + file.getName());
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        model.setDataArr(tmpStrArr);
        return model;
    }
}
