/*import java.util.Objects;
*//**
 * MyEntry implementation.
 *//*

public class MyEntry<K, V> {
    private K key;
    private V value;
    private MyEntry<K, V> next;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public MyEntry<K, V> getNext() {
        return next;
    }

    public void setNext(MyEntry<K, V> next) {
        this.next = next;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V setValue(V newValue) {
        V oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }

    *//**
     * Returns entry hashcode.
     * @return int value - entry hashcode
     *//*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (value == null ? 0 : value.hashCode());
        return result;
    }

    *//**
     * Checks if MyEntry objects are equal.
     * @return true if objects are equal, false otherwise
     *//*

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyEntry<?, ?> myEntry = (MyEntry<?, ?>) obj;
        return Objects.equals(getKey(), myEntry.getKey()) &&
                Objects.equals(getValue(), myEntry.getValue());
    }

    @Override
    public String toString() {
        return "MyEntry{" +
                "K=" + key +
                ",V=" + value +
                ",N=" + next +
                "} ";
    }
}*/
