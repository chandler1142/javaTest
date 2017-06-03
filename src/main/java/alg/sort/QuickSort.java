package alg.sort;

import java.util.Arrays;

/**
 * Created by i325622 on 5/31/17.
 */
public class QuickSort {


    public int partition(int low, int max, int[] array) {

        int tag = array[low];

        if(low < max) {
            while (low < max) {

                while(array[max] > tag && low < max)
                    max--;
                if(low < max) {
                    array[low] = array[max];
                    low++;
                }

                while(array[low] < tag && low < max)
                    low++;
                if(low < max) {
                    array[max] = array[low];
                    max--;
                }
            }
        }

        array[low] = tag;
        return low;
    }

    public void quickSort(int[] array, int low, int max) {

        if(array == null || array.length == 1)
            return;

        if(low >= max)
            return;

        int p = partition(low, max, array);
        if(p > low) {
            quickSort(array, low, p-1);
        }
        if(p < max) {
            quickSort(array, p+1, max);
        }
    }


    public static void main(String[] args) {
        int[] array = {1,3,2,4};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array, 0, array.length-1);

        Arrays.stream(array).forEach(n->{
            System.out.println(n);
        });
    }
}
