public class IntList {
    public  int first;
    public IntList rest;

    public void addFirst(int x)
    {
        this.rest=new IntList(this.first,this.rest);
        this.first=x;
    }
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
    public int getRecursion(int i)
    {
        if (i==0)
        {
            return this.first;
        }
        return this.rest.getRecursion(i-1);
    }

    public static void main(String[] args) {
        IntList num=new IntList(4,null);
        num=new IntList(3,num);
        num=new IntList(2,num);
        num=new IntList(1,num);
        System.out.println(num.get(0));
    }
}
