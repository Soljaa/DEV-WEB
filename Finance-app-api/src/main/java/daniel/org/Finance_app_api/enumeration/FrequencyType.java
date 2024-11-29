package daniel.org.Finance_app_api.enumeration;

public enum FrequencyType {
    REGULAR("regular"),
    OCCASIONAL("occasional");

    private final String value;

    FrequencyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
