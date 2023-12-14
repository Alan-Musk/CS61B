// (Min) Priotirty Queue: Allowing tracking and removal of the samllest item in a priority queue
public interface MinPQ<Item>{
    // Adds teh item to the priority queue.
    public void add(Item x);
    // Returns the smallest item in the priority queue.
    public Item getSmallest();
    // Removes the smallest item from the priority queue.
    public Item removeSmallest();
    // Returns the size of the priority queue.
    public int size();
}
