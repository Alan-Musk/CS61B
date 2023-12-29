package lab9;

import edu.princeton.cs.algs4.In;

import java.util.*;

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
        if (p == null) return null;
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
        } else if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.value = value;
        }
        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
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
        RootAndValue rv = removeHelper(root, key, null);
        root = rv.newRoot;
        return rv.removedValue;
    }

    private RootAndValue removeHelper(Node p, K key, Node parent) {
        if (p == null) return new RootAndValue(null, null);
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            RootAndValue leftResult = removeHelper(p.left, key, p);
            p.left = leftResult.newRoot;
            return new RootAndValue(p, leftResult.removedValue);
        } else if (cmp > 0) {
            RootAndValue rightResult = removeHelper(p.right, key, p);
            p.right = rightResult.newRoot;
            return new RootAndValue(p, rightResult.removedValue);
        } else {
            size--;
            if (p.right == null) return new RootAndValue(p.left, p.value);
            if (p.left == null) return new RootAndValue(p.right, p.value);
//            有两个节点
            Node t = p;
            Node minRight = min(t.right);
            minRight.right = deleteMin(t.right);
            minRight.left = t.left;
            return new RootAndValue(minRight, t.value);// 返回新的根和被删除的值
        }
    }

    private class RootAndValue {
        private Node newRoot;
        private V removedValue;

        public RootAndValue(Node newRoot, V removedValue) {
            this.newRoot = newRoot;
            this.removedValue = removedValue;
        }
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
        RootAndValue rv = removeHelper(root, key, value, null);
        root = rv.newRoot;
        return rv.removedValue;
    }

    private RootAndValue removeHelper(Node p, K key, V value, Node parent) {
        if (p == null) return new RootAndValue(null, null);

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            RootAndValue leftResult = removeHelper(p.left, key, value, p);
            p.left = leftResult.newRoot;
            return new RootAndValue(p, leftResult.removedValue);
        } else if (cmp > 0) {
            RootAndValue rightResult = removeHelper(p.right, key, value, p);
            p.right = rightResult.newRoot;
            return new RootAndValue(p, rightResult.removedValue);
        } else { // 键匹配，检查值是否也匹配
            if (p.value.equals(value)) {
                size--; // 更新大小
                if (p.right == null) return new RootAndValue(p.left, p.value);
                if (p.left == null) return new RootAndValue(p.right, p.value);

                // 节点有两个子节点，找到右子树的最小节点来替换
                Node t = p;
                Node minRight = min(t.right);
                minRight.right = deleteMin(t.right);
                minRight.left = t.left;

                return new RootAndValue(minRight, t.value); // 返回新的根和被删除的值
            } else {
                return new RootAndValue(p, null); // 值不匹配，不进行删除操作
            }
        }
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

   private class BSTIterator implements Iterator<K>{
        private Stack<Node> stack=new Stack<>();
        public BSTIterator(Node root)
        {
            pushAllLeft(root);
        }

        @Override
       public boolean hasNext()
        {
            return !stack.isEmpty();
        }

        @Override
       public K next(){
            if(!hasNext()) throw new NoSuchElementException();
            Node nextNode=stack.pop();
            pushAllLeft(nextNode.right);
            return nextNode.key;
        }
        private void pushAllLeft(Node node)
        {
            while(node!=null)
            {
                stack.push(node);
                node=node.left;
            }
        }
   }


    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        bstmap.remove("cat");
    }
}
