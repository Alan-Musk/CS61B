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
    private boolean recurision(Deque<Character> words)
    {
        if(words.isEmpty()||words.size()==1)
        {
            return true;
        } else if (words.removeFirst()==words.removeLast()) {
            return recurision(words);
        }
        return false;
    }
    public boolean isPalindrome(String word)
    {
        Deque<Character> words=wordToDeque(word);
        return recurision(words);
    }
}
