import java.util.List;

public interface Map61B<K,V>{
    // Associate key with value
    void put(K key,V value);
    // Checks if map contains the key
    boolean containsKey(K key);
    // Returns value, assuming key exists.
    V get(K key);
    //Return a list of all keys
    List<K> keys();
    // Returns number of keys
    int size();
}
