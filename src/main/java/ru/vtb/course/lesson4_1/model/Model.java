package ru.vtb.course.lesson4_1.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> dataArr;

    public Model() {
        this.dataArr = new ArrayList<>();
    }

    public List<String> getDataArr() {
        return new ArrayList<>(dataArr);
    }

    public void setDataArr(List<String> dataArr) {
        this.dataArr = dataArr;
    }

    @Override
    public String toString() {
        return "Model{" +
                "dataArr=" + dataArr +
                '}';
    }
}
