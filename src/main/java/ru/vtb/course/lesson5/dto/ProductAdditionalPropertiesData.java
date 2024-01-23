package ru.vtb.course.lesson5.dto;

import java.util.ArrayList;

public class ProductAdditionalPropertiesData {
    private ArrayList<ProductAdditionalProperties> data;

    public ProductAdditionalPropertiesData() {
    }

    public ProductAdditionalPropertiesData(ArrayList<ProductAdditionalProperties> data) {
        this.data = data;
    }

    public ArrayList<ProductAdditionalProperties> getData() {
        return data;
    }

    public void setData(ArrayList<ProductAdditionalProperties> data) {
        this.data = data;
    }
}
