import org.junit.Assert;
import org.junit.Before;

class MyHashMapTest {
    MyHashMap myHashMap = new MyHashMap();

    @Before
    public void setUp() {
        myHashMap.put(5, 3);
        myHashMap.put(3, 4);
        myHashMap.put(3, 6);
        myHashMap.put(1, 8);
        myHashMap.put(4, 4);
        myHashMap.put(2, 4);
        myHashMap.put(null, 7);
    }

    @org.junit.jupiter.api.Test
    void getSize() {
        Assert.assertNotNull(myHashMap.getSize());
        Assert.assertTrue(myHashMap.getSize() >= 0);
    }

    @org.junit.jupiter.api.Test
    void put() {
        myHashMap.put(5, 3);
        myHashMap.put(3, 4);
        myHashMap.put(3, 6);
        myHashMap.put(1, 8);
        myHashMap.put(4, 4);
        myHashMap.put(2, 4);
        myHashMap.put(null, 7);
    }

    @org.junit.jupiter.api.Test
    void get() {
        myHashMap.get(null);
        myHashMap.get(0);
        myHashMap.get(-45);
        myHashMap.get(98);
        myHashMap.get(5);
    }

    @org.junit.jupiter.api.Test
    void containsKey() {
        myHashMap.containsKey(5);
        myHashMap.containsKey(0);
        myHashMap.containsKey(99);
        myHashMap.containsKey(-25);
        myHashMap.containsKey(null);
    }
}