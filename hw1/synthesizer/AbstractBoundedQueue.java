package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public boolean isEmpty() {
        return BoundedQueue.super.isEmpty();
    }

    @Override
    public boolean isFull() {
        return BoundedQueue.super.isFull();
    }

    public abstract T peek();

    public abstract T dequeue();

    public abstract void enqueue(T x);
}
