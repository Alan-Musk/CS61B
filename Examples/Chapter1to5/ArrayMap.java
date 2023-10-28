package Chapter1to5;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ArrayMap<K, V> implements Map61B<K, V> {
    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }
    private int keyIndex(K key)
    {
        for (int i=0;i<size;i++)
        {
            if(keys[i].equals(key))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void put(K key, V value) {
        int index=keyIndex(key);
        if(index==-1)
        {
            keys[size]=key;
            values[size]=value;
            size+=1;
        }else{
            values[index]=value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return keyIndex(key)>-1;
    }

    @Override
    // Returns value, assuming key exists.
    public V get(K key) {
        int index=keyIndex(key);
        return values[index];
    }

    @Override
    //Return a list of all keys
    public List<K> keys() {
        List<K> keyList=new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            keyList.add(keys[i]);
        }
        return keyList;
    }

    @Override
    // Returns number of keys
    public int size() {
        return size;
    }

    @Test
    public void test()
    {
        ArrayMap<Integer,Integer> am=new ArrayMap<Integer,Integer>();
        am.put(2,5);
        int expected=  5;
        assertEquals((Integer) expected,am.get(2));
    }
}
