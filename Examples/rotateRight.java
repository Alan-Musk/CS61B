public class rotateRight<Item> extends SLList<Item> {
    public void rotateRight(SLList L)
    {
        Item temp=removeLast();
        addFirst(temp);
    }

}
