package alg;

/**
 * Created by i325622 on 5/15/17.
 */
public class TreeNodeTest {

    public boolean divideSearch(int[] array, int target) {

        if(array == null || array.length == 0) {
            return false;
        }

        int min = 0, max = array.length-1;

        while(min < max) {
            int mid = (min+max)/2;
            if(array[mid] == target) {
                return true;
            } else if(array[mid] > target) {
                max = mid-1;
            } else {
                min = mid+1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1,3,5,7};
        TreeNodeTest test = new TreeNodeTest();
        test.divideSearch(array, 3);
    }
}
