public interface Deque<T> {
    // return the length of a deque
    int size();

    // if a deque has nothing return True or return Flase
    boolean isEmpty();

    // As its name
    void addFirst(T t);

    // As its name
    void addLast(T t);

    // As its name
    T removeFirst();

    // As its name
    T removeLast();

    // As its name
    void printDeque();

    // get the content through the number "index"
    T get(int index);
}
