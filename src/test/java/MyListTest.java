import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    MyList myList = new MyList();

    @Before
    public void setUp() {
        myList.add(null);
        myList.add(-45);
        myList.add(78);
    }

    @Test
    void add() {
        myList.add(null);
        myList.add(-45);
        myList.add(78);
    }

    @Test
    void getSize() {
        Assert.assertNotNull(myList.getSize());
        // Assert.assertTrue(myList.getSize() > 0);
    }

    @Test
    void get() {
        myList.get(0);
        // myList.get(-1);
        // myList.get(20);
    }
}