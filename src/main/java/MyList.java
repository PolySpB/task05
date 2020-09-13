import java.util.Arrays;

public class MyList {
    private Object[] array = new Object[3];
    private int size;
    private int index;

    public void add(Object object) {
        array[index++] = object;
        size = index;
    }

    public int getSize() {
        return size;
    }

    public Object get(int index) {
        return array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array) + "  ";
    }

}
