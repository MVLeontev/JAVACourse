package ru.vtb.course.lesson5.dto;

public class ProductResponse {

    private ProductData data;

    public ProductResponse(ProductData data) {
        this.data = data;
    }

    public static class ProductData {
        private String instanceId;
        private String[] registerId;
        private String[] supplementaryAgreementId;
    }
}
