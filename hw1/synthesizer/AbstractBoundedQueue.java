package synthesizer;
public abstract class AbstractBoundedQueue<T> implements BoudedQueue<T> {
    protected int fillCount;
    protected int capacity;
    @Override
    public int capacity()
    {
        return capacity;
    }

    @Override
    public int fillCount()
    {
        return fillCount;
    }
    @Override
    public boolean isEmpty()
    {
        return BoudedQueue.super.isEmpty();
    }

    @Override
    public boolean isFull()
    {
        return BoudedQueue.super.isFull();
    }

    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
}
