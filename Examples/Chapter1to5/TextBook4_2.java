package Chapter1to5;

import java.util.*;

public class TextBook4_2 {
    public static ArrayList getWords(String inputFileName)
    {
        ArrayList<String> wordlist=new ArrayList<String>();
        In in=new In(inputFileName);
        while(!in.isEmpty())
        {
            String word=in.readString();
            wordlist.add(word);
        }
        in.close();
        return wordlist;
    }
    public static Set<String> countUniqueWords(List<String> ls)
    {
        Set<String> words= new HashSet<String>();
        for(String word:ls)
        {
            words.add(word);
        }
        return words;
    }
    public static HashMap<String,Integer> collectWordCount(List<String> targets,List<String> words){
        HashMap<String,Integer> wordCount=new HashMap<String,Integer>();

        //先统计每个单词出现的次数
        for(String word:words)
        {
            wordCount.put(word,wordCount.getOrDefault(word,0)+1);
        }

        //遍历目标列表并查找每个目标单词的出现次数
        HashMap<String,Integer> result=new HashMap<>();
        for(String target:targets)
        {
            result.put(target, wordCount.getOrDefault(target,0));
        }
        return result;
    }

    public static void main(String[] args) {
        String inputFileName= "Chapter1to5/Text.txt";
        ArrayList<String> words=getWords(inputFileName);
        for(String word:words)
        {
            System.out.println(word);
        }
        System.out.println(countUniqueWords(words).size());

        // convert Set to List
        List<String> uniqueWords=new ArrayList<String>();
        for(String set:countUniqueWords(words))
        {
            uniqueWords.add(set);
        }
        // trail for Exercise 4.4.3
        HashMap<String,Integer> hashMap=new HashMap<String ,Integer>();
        hashMap=collectWordCount(uniqueWords,words);
        System.out.println(hashMap.entrySet());
    }
}
