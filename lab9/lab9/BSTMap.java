package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key.compareTo(p.key) == 0) return p.value;
        else if (p.left == null && p.right == null) {
            return null;
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        }
        return getHelper(key, p.right);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            p = new Node(key, value);
        } else if (key.compareTo(p.key) == 0) {
            p.value = value;
            return p;
        } else if (key.compareTo(p.key) < 0) {
            return putHelper(key, value, p.left);
        }
        return putHelper(key, value, p.right);
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> all_keys = new HashSet<>();
        if (root != null) keySetHelper(all_keys, root);
        return all_keys;
    }

    private void keySetHelper(Set container, Node p) {
        if (p.left != null) keySetHelper(container, p.left);
        container.add(p.key);
        if (p.right != null) keySetHelper(container, p.right);
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node temp = removeHelper(root, key);
        if (temp == null) {
            return null;
        }
        return temp.value;
    }

    private Node removeHelper(Node p, K key) {
        if (p == null) return null;
        int cmp = key.compareTo(p.key);
        if (cmp < 0) p.left = removeHelper(p.left, key);
        else if (cmp > 0) p.right = removeHelper(p.right, key);
        else {
            Node temp;
            if (p.right == null) {
                temp = p;
                p = p.left;
                return temp;
            }
            if (p.left == null) {
                temp = p;
                p = p.right;
                return temp;
            }
            Node t = p;
            temp = p;
            p = min(t.right);
            p.right = deleteMin(t.right);
            p.left = t.left;
            return temp;
        }
        return p;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }


    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        Node temp = removeHelper(root,value,key);
        if (temp == null) {
            return null;
        }
        return temp.value;
    }

    private Node removeHelper(Node p, V value, K key) {
        if (p == null) return null;
        int cmp = key.compareTo(p.key);
        if (cmp < 0) p.left = removeHelper(p.left, value, key);
        else if (cmp > 0) p.right = removeHelper(p.right, value, key);
        else{
            if(p.value==value)
            {
                Node temp;
                if (p.right == null) {
                    temp = p;
                    p = p.left;
                    return temp;
                }
                if (p.left == null) {
                    temp = p;
                    p = p.right;
                    return temp;
                }
                Node t = p;
                temp = p;
                p = min(t.right);
                p.right = deleteMin(t.right);
                p.left = t.left;
                return temp;
            }
            else {
                return null;
            }
        }
        return p;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        size -= 1;
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
