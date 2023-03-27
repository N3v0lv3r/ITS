

public class BST <Key extends Comparable<Key>, Value>{
//-----------------------------------------------------
// Title: Captain Class
// Author: Azerhan BAĞCI
// Description: This class implements binary search tree.
//-----------------------------------------------------
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        private int count;
        public Node(Key key, Value val) {
            this.key = key; this.val = val;
        }
    }

    private Node root;

    public Value get(Key key) {
        //--------------------------------------------------------
        // Summary: Returns Value when given a value.
        // Precondition: key is a Key.
        // Postcondition: Returned Value.
        // Note: Both Value and Key are generics.
        //--------------------------------------------------------
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp  < 0) x = x.left;
            else if (cmp  > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        //--------------------------------------------------------
        // Summary: When given two values, assigns the root as put(root, a, b) where a and b are the given two values.
        // Precondition: key is Key and val is Value.
        // Postcondition: New root assigned.
        // Note: Both Key and Value are generics.
        //--------------------------------------------------------
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        //--------------------------------------------------------
        // Summary: Adds a new node containing a key and a value to the binary search tree when given three values.
        // Precondition: x is Node, key is Key and val is Value.
        // Postcondition: Added new node.
        // Note: Both Key and Value are generics.
        //--------------------------------------------------------
        if (x == null)
            return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if      (cmp  < 0)
            x.left  = put(x.left,  key, val);
        else if (cmp  > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        return x;
    }

    public Key floor(Key key) {
        //--------------------------------------------------------
        // Summary: Returns the floor key in binary search tree when given a value.
        // Precondition: key is Key.
        // Postcondition: Returned floor key.
        // Note: Key is a generic.
        //--------------------------------------------------------
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        //--------------------------------------------------------
        // Summary: Returns the floor node in binary search tree when given two values.
        // Precondition: x is Node and key is Key.
        // Postcondition: Returned floor node.
        // Note: Key is a generic.
        //--------------------------------------------------------
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public Key min() {
        //--------------------------------------------------------
        // Summary: Returns the minimum key in binary search tree.
        // Precondition: this is not null.
        // Postcondition: Returned the minimum key.
        // Note: Key is a generic.
        //--------------------------------------------------------
        return min(root).key;
    }

    private Node min(Node x) {
        //--------------------------------------------------------
        // Summary: Returns the node that holds the minimum key in binary search tree.
        // Precondition: x is Node.
        // Postcondition: Returned the node that holds the minimum key.
        //--------------------------------------------------------
        if(x.left == null) return x;
        return min(x.left);
    }

    public int size() {
        //--------------------------------------------------------
        // Summary: Returns size(root).
        // Precondition: this is not null.
        // Postcondition: Returned size(root).
        //--------------------------------------------------------
        return size(root);
    }

    private int size(Node x) {
        //--------------------------------------------------------
        // Summary: Returns the size of the binary search tree.
        // Precondition: x is Node.
        // Postcondition: Returned the size of the binary search tree.
        //--------------------------------------------------------
        if (x == null)
            return 0;
        return x.count;
    }

    public void delete(Key key) {
        //--------------------------------------------------------
        // Summary: Assigns the value of root to delete(root, key).
        // Precondition: key is Key.
        // Postcondition: Assigned new value of root.
        // Note: Key is a generic.
        //--------------------------------------------------------
        root = delete(root, key);
    }

    public void deleteMin() {
        //--------------------------------------------------------
        // Summary: Assigns the value of root to deleteMin(root).
        // Precondition: this is not null.
        // Postcondition: Assigned new value of root.
        //--------------------------------------------------------
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        //--------------------------------------------------------
        // Summary: Deletes the node that holds the minimum key and returns it when given a value.
        // Precondition: x is Node.
        // Postcondition: The node that holds the minimum key is deleted and returned.
        //--------------------------------------------------------
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node delete(Node x, Key key) {
        //--------------------------------------------------------
        // Summary: Deletes and returns the node when given two values.
        // Precondition: x is Node and key is Key.
        // Postcondition: Node is deleted and returned.
        // Note: Key is a generic.
        //--------------------------------------------------------
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }



    public Iterable<Key> keys() {
        //--------------------------------------------------------
        // Summary: Returns an Iterable containing all keys in binary search tree.
        // Precondition: this is not null.
        // Postcondition: Iterable returned.
        // Note: Key is a generic.
        //--------------------------------------------------------
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        //--------------------------------------------------------
        // Summary: Traverses between the nodes of binary search tree and stores the keys in a queue when given two values.
        // Precondition: x is Node and q is Queue<Key>.
        // Postcondition: Traversal complete, q holds all the keys.
        // Note: Key is a generic.
        //--------------------------------------------------------
        if (x == null)
            return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }


}
