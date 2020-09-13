import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyEntryTest {
    MyEntry myEntry = new MyEntry(12345, 5, 7, 1);

    @Test
    void getKey() {
        myEntry.getKey();
    }

    @Test
    void getValue() {
        myEntry.getValue();
    }

    @Test
    void setKey() {
        myEntry.setKey(null);
        myEntry.setKey(12);
        myEntry.setKey(-35);
    }

    @Test
    void setValue() {
        myEntry.setValue(null);
        myEntry.setValue(567);
        myEntry.setValue(-67);
    }

    @Test
    void getHash() {
        myEntry.getHash();
    }
}