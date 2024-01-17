package ru.vtb.course.lesson5.dto;

public class ProductResponse {

    private ProductData data;

    public ProductResponse(String instanceId, String[] registerId, String[] supplementaryAgreementId) {
        this.data = new ProductData(instanceId, registerId, supplementaryAgreementId);
    }

    public static class ProductData {
        private String instanceId;
        private String[] registerId;
        private String[] supplementaryAgreementId;

        public ProductData(String instanceId, String[] registerId, String[] supplementaryAgreementId) {
            this.instanceId = instanceId;
            this.registerId = registerId;
            this.supplementaryAgreementId = supplementaryAgreementId;
        }
    }
}
