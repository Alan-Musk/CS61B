package Chapter1to5;

public class Ex4_1 {
    public String findLongest(SLList<String> items)
    {
        int maxDex=0;
        for(int i=0;i< items.size();i++)
        {
            String longestString=items.get(maxDex);
            String thisString=items.get(i);
            if(thisString.length()>longestString.length())
            {
                maxDex=i;
            }
        }
        return items.get(maxDex);
    }
    public String findLongest(AList<String> items)
    {
        int maxDex=0;
        for(int i=0;i< items.size();i++)
        {
            String longestString=items.get(maxDex);
            String thisString=items.get(i);
            if(thisString.length()>longestString.length())
            {
                maxDex=i;
            }
        }
        return items.get(maxDex);
    }
}
