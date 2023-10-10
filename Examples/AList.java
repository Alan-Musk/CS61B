/**
 * Array based list.
 *
 * @author Josh Hug
 */

public class AList<item> {
    /**
     * Creates an empty list.
     */
    private item[] items;
    private int index;

    public AList() {
        items =(item[]) new Object[100];
        index = 0;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(item x) {
        if(index==items.length)
        {
            resize(index+1);
        }
        items[index]=x;
        index+=1;
    }

    /**
     * Returns the item from the back of the list.
     */
    public item getLast() {
        return items[index - 1];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public item get(int i) {
        if (i >= 0 && i < items.length) {
            return items[i];
        }
        return null;
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return index;
    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    public item removeLast() {
        item temp = getLast();
        index -= 1;
        return temp;
    }
    private void resize(int capacity)
    {
        item[] a=(item []) new Object[capacity];
        System.arraycopy(items,0,a,0,index);
        items=a;
    }
} 