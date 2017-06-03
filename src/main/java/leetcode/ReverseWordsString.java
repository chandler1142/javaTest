package leetcode;

/**
 * Created by i325622 on 5/31/17.
 */
public class ReverseWordsString {

    public String reverseWords(String s) {
        if(s == null) {
            return "";
        }

        s = reverse(s);
        String[] words = s.split(" ");
        String result = "";
        for(int i=0;i<words.length;i++) {
            result += reverse(words[i]);
            if(i != words.length-1) {
                result += " ";
            }
        }

        return result.trim();
    }

    String reverse(String s) {
        s = s.trim();
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=ch.length-1;i>=0;i--) {
            sb.append(ch[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = " 1    2  ";
        ReverseWordsString reverseWordsString = new ReverseWordsString();
        String r = reverseWordsString.reverseWords(s);
        System.out.println(r);
    }
}
