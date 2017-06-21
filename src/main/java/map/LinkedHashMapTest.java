package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by i325622 on 6/21/17.
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap();
        map.put("2", "22");
        map.put("1", "11");
        map.put("4", "44");
        map.put("3", "33");

        for (Map.Entry e : map.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }
}
