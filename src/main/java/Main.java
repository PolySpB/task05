import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        System.out.println(myHashMap.put(5, 3));
        System.out.println(myHashMap.put(3, 4));
        System.out.println(myHashMap.toString());

        System.out.println(myHashMap.remove(5, 3));
        System.out.println(myHashMap.toString());

        System.out.println(myHashMap.containsValue(4));
        System.out.println(myHashMap.containsValue(9));
        System.out.println(myHashMap.containsValue(null));

        Map<Integer, Integer> mapToBeInserted = new HashMap<>();
        mapToBeInserted.put(1, 1);
        mapToBeInserted.put(9, 9);
        myHashMap.putAll(mapToBeInserted);
        System.out.println(myHashMap.toString());

        System.out.println(myHashMap.entrySet().toString());
        myHashMap.put(5, 7);
        myHashMap.put(1, 5);
        System.out.println(myHashMap.entrySet().toString() + " " + myHashMap.size());
        myHashMap.remove(1);
        System.out.println(myHashMap.entrySet().toString() + " " + myHashMap.size());
        System.out.println(myHashMap.keySet());




        /*System.out.println(myHashMap.toString());
        myHashMap.put(1, 8);
        System.out.println(myHashMap.toString());
        myHashMap.put(4, 4);
        System.out.println(myHashMap.toString());
        myHashMap.put(2, 4);
        myHashMap.put(null, 7);
        System.out.println(myHashMap.toString());

        System.out.println(myHashMap.get(5));
        System.out.println(myHashMap.get(3));
        System.out.println(myHashMap.get(333));
        System.out.println(myHashMap.get(null));

        System.out.println("MyHashMap size=" + myHashMap.size());

        System.out.println(myHashMap.containsKey(5));
        System.out.println(myHashMap.containsKey(null));

        System.out.println(myHashMap);
        System.out.println(myHashMap.remove(5));
        System.out.println(myHashMap);
        System.out.println(myHashMap.remove(null));
        System.out.println(myHashMap);
        System.out.println(myHashMap.remove(4));
        System.out.println(myHashMap);
        System.out.println(myHashMap.remove(-2));*/
    }
}
