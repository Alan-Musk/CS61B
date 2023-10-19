/**
 * This class outputs all palindromes in the words file in the current directory.
 */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 3;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        OffByOne test = new OffByOne();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, test)) {
                System.out.println(word);
            }
        }
    }
}
