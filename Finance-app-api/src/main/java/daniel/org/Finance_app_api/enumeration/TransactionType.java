package daniel.org.Finance_app_api.enumeration;

public enum TransactionType {
    EXPENSE("expense"),
    INCOME("income");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}