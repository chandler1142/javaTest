package alg.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class QuickSort2 {

    public static List<Integer> sort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        }
        int head = list.get(0);
        List<Integer> onelist = new ArrayList<>();

        List<Integer> leftList = list.stream().skip(1).filter(x -> x <= head).collect(Collectors.toList());
        List<Integer> rightList = list.stream().skip(1).filter(x -> x > head).collect(Collectors.toList());

        onelist.addAll(sort(leftList));
        onelist.add(head);
        onelist.addAll(sort(rightList));

        return onelist;
    }

    public static void main(String[] args) {

        Integer[] array = {3,6,2,1,67,8,6,9};
        List<Integer> list = sort(Arrays.asList(array));


//        List<Integer> list = Arrays.asList(array);
//        List newlist = list.stream().skip(1).collect(Collectors.toList());

        System.out.println(123123);

    }
}
