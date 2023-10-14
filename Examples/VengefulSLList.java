public class VengefulSLList <Item> extends SLList<Item>{
    SLList<Item> deletedItems;

   public VengefulSLList()
   {
       super();
       deletedItems=new SLList<Item>();
   }
   public VengefulSLList(Item x)
   {
       super(x);
       deletedItems=new SLList<Item>();
   }

    @Override
    public Item removeLast() {
        Item x=super.removeLast();
        deletedItems.addLast(x);
        return x;
    }
}
