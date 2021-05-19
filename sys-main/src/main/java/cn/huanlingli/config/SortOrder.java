package cn.huanlingli.config;

public enum SortOrder {
    ASCENDING("asc"), DESCENDING("desc");

    private String value;

    SortOrder() {
    }

    SortOrder(String value) {
        this.value = value;
    }

    @Deprecated
    @Override
    public String toString() {
        return value;
    }

    public String ToString() {
        return value;
    }
}
