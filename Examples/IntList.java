public class IntList {
    public  int first;
    public IntList rest;

    public IntList(int f,IntList r)
    {
        first=f;
        rest=r;
    }
    public int size()
    {
        if(this.rest==null)
        {
            return 1;
        }
        return 1+this.rest.size();
    }
    public int iterativeSize()
    {
        IntList p=this;
        int totalSize=0;
        while(p.rest!=null)
        {
            totalSize+=1;
            p=p.rest;
        }
        return totalSize;
    }
    public  int get(int i)
    {
        IntList p=this;
        for(int j=0;j<i;j++)
        {
            p=p.rest;
        }
        return p.first;
    }
}
