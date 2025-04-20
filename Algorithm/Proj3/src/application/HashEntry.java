package application;

class HashEntry {
    private String key;
    private Country value;
    private byte status; // 0 = empty, 1 = active, 2 = deleted

    public HashEntry(String key, Country value, byte status) {
        this.key = key;
        this.value = value;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public Country getValue() {
        return value;
    }

    public byte getStatus() {
        return status;
    }

    public void setDeleteStatus() {
        status = 2; // Mark as deleted
    }
}
