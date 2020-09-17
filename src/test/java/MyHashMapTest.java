import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class MyHashMapTest {
    MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
    List<Integer> listOfKeys = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        myHashMap.put(5, 3);
        myHashMap.put(3, 6);
        myHashMap.put(1, 8);
        myHashMap.put(4, 4);
        myHashMap.put(2, 4);
        myHashMap.put(null, 7);
        Collections.addAll(listOfKeys, 5, 3, 1, 4, 2, null);

    }

    @Test
    void getSize() {
        Assert.assertTrue(myHashMap.size() >= 0);
        Assert.assertTrue(myHashMap.size() == 6);
        myHashMap.put(3, 4);
        Assert.assertTrue(myHashMap.size() == 6);
    }

    @Test
    void put() {
        for (Integer key : listOfKeys) {
            Assert.assertTrue(myHashMap.containsKey(key));
        }
    }

    @Test
    void get() {
        myHashMap.get(null);
        myHashMap.get(0);
        myHashMap.get(-45);
        myHashMap.get(98);
        myHashMap.get(5);
    }

    @Test
    void containsKey() {
        myHashMap.containsKey(5);
        myHashMap.containsKey(0);
        myHashMap.containsKey(99);
        myHashMap.containsKey(-25);
        myHashMap.containsKey(null);
    }

    @Test
    void remove() {
        myHashMap.remove(null);
        myHashMap.remove(5);
        myHashMap.remove(-2);
        myHashMap.remove(4);
    }

}
