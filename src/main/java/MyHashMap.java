import java.util.*;

/**
 * Hashmap implementation with the usage of arrays.
 */

public class MyHashMap<K, V> implements Map<K, V> {
    private final int DEFAULT_CAPACITY = 16;
    private MyEntry<K, V>[] table = new MyEntry[DEFAULT_CAPACITY];
    private int threshold = (int) (table.length * 0.75);
    private int size;
    private MyEntrySet<Entry<K, V>> myEntrySet = new MyEntrySet<>();

    /**
     * Returns size of map.
     * @return int value; size of map
     */
    @Override
    public int size() {
        return size;
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private MyEntry<K, V> nodeThrough(MyEntry<K, V> myEntry, K key) {
        while (myEntry != null) {
            if (myEntry.getKey() != null) {
                if (myEntry.getKey().equals(key) || myEntry.getKey() == key) {
                    return myEntry;
                }
            } else {
                if (key == null)
                    return myEntry;
            }
            myEntry = myEntry.getNext();
        }
        return null;
    }

    private MyEntry<K, V> addEntry(K key, V value) {
        return new MyEntry<>(key, value);
    }

    private void putForNullKey(V value) {
        if (table[0] != null) {
            MyEntry<K, V> myEntry = nodeThrough(table[0], null);
            if (myEntry != null) {
                myEntrySet.remove(myEntry);
                myEntry.setValue(value);
                myEntrySet.add(myEntry);
            } else {
                MyEntry<K, V> newMyEntry = addEntry(null, value);
                myEntrySet.add(newMyEntry);
                newMyEntry.setNext(table[0]);
                table[0] = newMyEntry;
                size++;
            }
        }
        else {
            table[0] = addEntry(null, value);
            myEntrySet.add(table[0]);
            size++;
        }
    }

    /**
     * Returns true if map is empty, false otherwise
     * @return true if map is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if hashmap has an entry with the specified key, false otherwise.
     * @param key unique entry identifier
     * @return true if hashmap has an entry with the specified key, false otherwise
     */
    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * Returns true if hashmap has an entry with the specified value, false otherwise.
     * @param value entry value matching a specific key
     * @return true if hashmap has an entry with the specified value, false otherwise
     */
    @Override
    public boolean containsValue(Object value) {
        MyHashMap.MyEntry<K, V>[] tab;
        if ((tab = table) != null && size > 0) {

            for(int i = 0; i < tab.length; ++i) {
                for(MyHashMap.MyEntry<K, V> entry = tab[i]; entry != null; entry = entry.next) {
                    Object v;
                    if ((v = entry.value) == value || value != null && value.equals(v)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Adds an element(entry) into a hashmap.
     * If hashmap already contains an element with the specified key, an element will be replaced with the new one.
     * @param key unique entry identifier
     * @param value value matching a specific key
     * @return value of an entry that was inserted
     */
    public V put(K key, V value) {
        resize();
        if (key != null) {
            int hash = hash(key.hashCode());
            int index = indexFor(hash, table.length - 1);

            if (table[index] != null) {
                MyEntry<K, V> myEntry = nodeThrough(table[index], key);
                if (myEntry != null) {
                    myEntrySet.remove(myEntry);
                    myEntry.setValue(value);
                    myEntrySet.add(myEntry);
                } else {
                    MyEntry<K, V> newMyEntry = addEntry(key, value);
                    myEntrySet.add(newMyEntry);
                    newMyEntry.setNext(table[index]);
                    table[index] = newMyEntry;
                    size++;
                }
            } else {
                table[index] = addEntry(key, value);
                myEntrySet.add(table[index]);
                size++;
            }
        } else {
            putForNullKey(value);
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToBeInserted) {
        for (Entry<? extends K, ? extends V> entry : mapToBeInserted.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Clears hashmap: if hashmap contains any entries, they will be removed.
     */
    @Override
    public void clear() {
        MyHashMap.MyEntry<K, V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;

            for(int i = 0; i < tab.length; ++i) {
                tab[i] = null;
            }
        }
    }

    /**
     * Returns set of entries stored in a hashmap.
     * @return set of entries
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return myEntrySet;
    }

    class MyEntrySet<E> extends HashSet<E> {

    }

    ////////////////////////////////
    @Override
    public Set<K> keySet() {
        return null;
    }

    class MyKeySet<E> extends HashMap<K, V> {

    }

    //////////////////////////////////////////
    @Override
    public Collection<V> values() {
        return null;
    }

    /**
     * Finds an element(entry) with the specific key.
     * @param key unique entry identifier
     * @return entry matching the key; null if no entry is matching the key
     */
    @Override
    public V get(Object key) {
        MyEntry<K, V> myEntry;
        if (key == null) {
            myEntry = nodeThrough(table[0], (K) key);
        } else {
            int hash = hash(key.hashCode());
            int index = indexFor(hash, table.length - 1);
            myEntry = nodeThrough(table[index], (K) key);
        }
        if (myEntry != null) {
            return myEntry.getValue();
        }
        return null;
    }

    private void transfer(MyEntry<K, V> entry, MyEntry<K, V>[] newTable) {
        while (entry != null) {
            int index = 0;
            if (entry.getKey() != null) {
                int hash = hash(entry.getKey().hashCode());
                index = indexFor(hash, table.length - 1);
            }
            if (newTable[index] != null) {
                MyEntry<K, V> newEntry = addEntry(entry.getKey(), entry.getValue());
                newEntry.setNext(newTable[index]);
                newTable[index] = newEntry;
            } else {
                newTable[index] = addEntry(entry.getKey(), entry.getValue());
            }
            entry = entry.getNext();
        }
        table = newTable;
    }

    private void resize() {
        if (size >= threshold) {
            MyEntry<K, V>[] newTable = new MyEntry[table.length * 2];
            for (MyEntry<K, V> entry : table) {
                if (entry != null) {
                    transfer(entry, newTable);
                }
            }
        }
    }

    /**
     * Removes an entry with a specific key.
     * @param key unique entry identifier
     * @return value matching a key that was removed
     */
    @Override
    public V remove(Object key) {
        MyEntry<K, V> result = removeMyEntry(key);
        return result.getValue();
    }

    private MyEntry<K, V> removeMyEntry(Object key) {
        int index = 0;
        if (key != null) {
            int hash = hash(key.hashCode());
            index = indexFor(hash, table.length - 1);
        }
        MyEntry<K, V> myEntry = table[index];
        MyEntry<K, V> result = null;

        if (myEntry != null) {
            if (key != null) {
                if (myEntry.getKey().equals(key) || myEntry.getKey() == key) {
                    result = myEntry;
                    table[index] = myEntry.getNext();
                    myEntrySet.remove(myEntry);
                    size--;
                    return result;
                }
                while (myEntry.getNext() != null) {
                    if (myEntry.getNext().getKey().equals(key) || myEntry.getNext().getKey() == key) {
                        break;
                    }
                    myEntry = myEntry.getNext();
                }
                if (myEntry.getNext() != null) {
                    MyEntry<K, V> myNewEntry = myEntry.getNext().getNext();
                    myEntry.setNext(myNewEntry);
                    result = myEntry;
                    myEntrySet.remove(myEntry);
                    size--;
                    return result;
                }
            } else {
                if (myEntry.getKey() == null) {
                    table[index] = myEntry.getNext();
                    myEntrySet.remove(myEntry);
                    size--;
                    return myEntry;
                } else {
                    while (myEntry.getNext() != null) {
                        if (myEntry.getNext().getKey() == null) {
                            break;
                        }
                        myEntry = myEntry.getNext();
                    }
                    if (myEntry.getNext() != null) {
                        MyEntry<K, V> myNewEntry = myEntry.getNext().getNext();
                        myEntry.setNext(myNewEntry);
                        result = myEntry;
                        myEntrySet.remove(myEntry);
                        size--;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(table) + ", size=" + size + ", table length=" + table.length;
    }

    static class MyEntry<K, V> implements Map.Entry<K, V> {
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

        /**
         * Returns entry hashcode.
         * @return int value - entry hashcode
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (key == null ? 0 : key.hashCode());
            result = prime * result + (value == null ? 0 : value.hashCode());
            return result;
        }

        /**
         * Checks if MyEntry objects are equal.
         * @return true if objects are equal, false otherwise
         */
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
    }

}
