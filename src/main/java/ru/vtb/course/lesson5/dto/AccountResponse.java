package ru.vtb.course.lesson5.dto;

public class AccountResponse {
    private AccountData data;
    public AccountResponse(String id) {
        this.data = new AccountData(id);
    }

    public AccountData getData() {
        return data;
    }

    public void setData(AccountData data) {
        this.data = data;
    }

    public static class AccountData{
        private String accountId;

        public AccountData(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }
    }
}
