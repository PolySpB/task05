import java.util.Objects;

public class MyEntry {
    private final int hash;
    private Object key;
    private Object value;
    private int next;

    public MyEntry(int hash, Object key, Object value, int next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object setValue(Object newValue) {
        Object oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }

    public int getHash() {
        return hash;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (key == null ? 0 : key.hashCode());
        // result = prime * result + (value == null ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyEntry myEntry = (MyEntry) obj;
        return Objects.equals(getKey(), myEntry.getKey()) &&
                Objects.equals(getValue(), myEntry.getValue());
    }

    @Override
    public String toString() {
        return "{K=" + key +
                ", V=" + value +
                '}';
    }
}
