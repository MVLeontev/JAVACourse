package ru.vtb.course.lesson5.dto;

import java.util.ArrayList;

public class ProductResponse {

    private ProductData data;

    public ProductResponse(String instanceId, ArrayList<String> registerId, ArrayList<String> supplementaryAgreementId) {
        this.data = new ProductData(instanceId, registerId, supplementaryAgreementId);
    }

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }

    public static class ProductData {
        private String instanceId;
        private ArrayList<String> registerId;
        private ArrayList<String> supplementaryAgreementId;

        public ProductData(String instanceId, ArrayList<String> registerId, ArrayList<String> supplementaryAgreementId) {
            this.instanceId = instanceId;
            this.registerId = registerId;
            this.supplementaryAgreementId = supplementaryAgreementId;
        }

        public ProductData() {
        }

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public ArrayList<String> getRegisterId() {
            return registerId;
        }

        public void setRegisterId(ArrayList<String> registerId) {
            this.registerId = registerId;
        }

        public ArrayList<String> getSupplementaryAgreementId() {
            return supplementaryAgreementId;
        }

        public void setSupplementaryAgreementId(ArrayList<String> supplementaryAgreementId) {
            this.supplementaryAgreementId = supplementaryAgreementId;
        }
    }
}
