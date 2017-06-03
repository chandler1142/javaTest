package alg;

/**
 * Created by i325622 on 5/11/17.
 */
public class StringToLong {

    private static final long MAX_NEGTIVE_VALUE = 0x8000000000000000l;
    private static final long MAX_POSITIVE_VALUE = 0x7fffffffffffffffl;

    /**
     * @param str
     * @return Long value
     */
    public Long stringToLong(String str) {

        long result = 0;
        boolean negtive = false;
        int i = 0, len = str.length();
        long limit = -MAX_NEGTIVE_VALUE;


        return result;
    }

    public static void main(String[] args) {
        int radix = 10;
        System.out.println(MAX_POSITIVE_VALUE);
        System.out.println(MAX_POSITIVE_VALUE/10);
        Long.parseLong("9223372036854775808");
    }

}
