public class Palindrome {
    public Deque<Character> wordToDeque(String word)
    {
        ArrayDeque<Character> words=new ArrayDeque<>();
        for(int i=0;i<word.length();i++)
        {
            words.addLast(word.charAt(i));
        }
        return (Deque<Character>) words;
    }
    public boolean isPalindrome(String word)
    {
        Deque<Character> words=wordToDeque(word);
        while(words.removeFirst()==words.removeLast())
        {
            if(words.isEmpty())
            {
                return true;
            }
        }
        return false;
    }
}
