import java.util.Arrays;

/**
 * Hashmap implementation with the usage of arrays.
 */

public class MyHashMap {
    private final int DEFAULT_CAPACITY = 3;
    private MyList[] table = new MyList[DEFAULT_CAPACITY];
    private int threshold = (int) (table.length * 0.75);
    private int size;

    /**
     * Returns size of map.
     * @return int value; size of map
     */
    public int getSize() {
        return size;
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private void addEntry(int hash, Object key, Object value, int index) {
        table[index] = new MyList();
        table[index].add(new MyEntry(hash, key, value, index));
        resize();
        size++;
    }

    private void putForNullKey(Object value) {
        if (table[0] != null) {
            table[0].add(new MyEntry(0, null, value, 0));
        }
        else {
            addEntry(0, null, value, 0);
        }
        size++;
    }

    /**
     * Adds an element(entry) into a hashmap.
     * If hashmap already contains an element with the specified key, an element will be replaced with the new one.
     * @param key unique entry identifier
     * @param value value matching a specific key
     */
    public void put(Object key, Object value) {
        if (key != null) {
            int hash = hash(key.hashCode());
            int index = indexFor(hash, table.length - 1);

            if (table[index] != null) {
                boolean isInsert = false;
                for (int i = 0; i < table[index].getSize(); i++) {
                    MyEntry entry = (MyEntry) table[index].get(i);
                    try {
                        if (entry.getHash() == hash && (entry.getKey() == key || key.equals(entry.getKey()))) {
                            entry.setValue(value);
                            isInsert = true;
                            break;
                        }
                    } catch (NullPointerException e) {

                    }
                }
                if (!isInsert) {
                    table[index].add(new MyEntry(hash, key, value, index));
                    size++;
                }
            } else {
                addEntry(hash, key, value, index);
            }
        } else {
            putForNullKey(value);
        }
    }

    /**
     * Finds an element(entry) with the specific key.
     * @param key unique entry identifier
     * @return entry matching the key; null if no entry is matching the key
     */
    public MyEntry get(Object key) {
        int index = 0;
        try {
            int hash = hash(key.hashCode());
            index = indexFor(hash, table.length);
        } catch (NullPointerException e) {

        }

        MyList list = table[index];
        try {
            for (int i = 0; i < list.getSize(); i++) {
                MyEntry entry = (MyEntry) list.get(i);
                if (entry != null && entry.getKey() == key) {
                    return entry;
                    // System.out.println("element w key #" + key + " " + entry.toString());
                    // break;
                }
            }
        } catch (NullPointerException e) {

        }
        return null;
    }

    /**
     * Returns true if hashmap has an entry with the specified key, false otherwise.
     * @param key unique entry identifier
     * @return true if hashmap has an entry with the specified key, false otherwise
     */
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    private void transfer(MyList[] newTable) {
        for (MyList list : table) {
            if (list != null) {
                for (int i = 0; i < list.getSize(); i++) {
                    MyEntry entry = (MyEntry) list.get(i);
                    try {
                        int hash = hash(entry.getKey().hashCode());
                        int index = indexFor(hash, newTable.length);
                        newTable[index].add(entry);
                    } catch (NullPointerException e) {

                    }
                }
            }
        }
        table = newTable;
    }

    private void resize() {
        if (size >= threshold) {
            MyList[] newTable = new MyList[table.length * 2];
            for (int i=0; i < newTable.length; i++) {
                newTable[i] = new MyList();
            }
            transfer(newTable);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(table) + ", size=" + size + ", table length=" + table.length;
    }

}
