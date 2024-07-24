import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
/**
 * @author Dhanush Venkataramu.
 * Class for BST operations implementation.
 * @param <T> specifies the generic for class BST.
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * Instance variable root of the BST.
     */
    private Node<T> root;
    /**
     * Comparator instance variable of the BST.
     */
    private Comparator<T> comparator;
    /**
     * Constructor for building BST with natural order.
     */
    public BST() {
        this(null);
    }
    /**
     * Parameterized constructor for building BST with alternative order.
     * @param comp specifies the comparator for BST.
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }
    /**
     * Instance method to return the comparator.
     * @return the comparator of BST.
     */
    public Comparator<T> comparator() {
        return comparator;
    }
    /**
     * Instance method to return the root data of BST.
     * Protecting private data by returning a deep copy.
     * @return null if BST is empty, word in root otherwise.
     */
    public T getRoot() {
        if (root == null) {
            return null;
        }
        return new Node<T>(root.data).data;
    }
    /**
     * Instance method to get height of BST.
     * Helper method to recursively implement.
     * @return 0 if tree is empty, height otherwise.
     */
    public int getHeight() {
        if (root == null) {
            return 0;
        }
       return getHeightHelper(root);
    }
    /**
     * Instance method to get number of nodes in BST.
     * Helper method to recursively implement.
     * @return number of nodes.
     */
    public int getNumberOfNodes() {
        return nodeNumberHelper(root);
    }
    /**
     * Instance method to get search element in BST.
     * return node data if found, null otherwise.
     */
    @Override
    public T search(T toSearch) {
        if (root == null) {
            return null;
        }
        Node<T> res;
        if (comparator == null) {
            res = searchNatural(root, toSearch);
        } else {
            res = searchAlter(root,  toSearch);
        }
        if (res != null) {
            return res.data;
        }
        return null;
    }
    /**
     * Instance method to get insert element in BST.
     * using alternative order if comparator present,
     * natural order otherwise.
     */
    @Override
    public void insert(T toInsert) {
        if (comparator == null) {
            root = insertNatural(root, toInsert);
        } else {
            root = insertAlter(root, toInsert);
        }
    }
    /**
     * Return an iterator to traverse through BST inorder fashion.
     */
    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator();
    }
    /**
     * Helper method to get height recursively.
     * @param node specifies the node currently traversing.
     * @return height of the BST.
     *
     * Recursion Details:
     * Base Case: if we reached the leaf node, return -1.
     * Recursion call: pass the left child to get to the leftmost node,
     * and pass right to child to get to the rightmost node.
     * Increment 1 at each level with the highest left/right subtree height.
     */
    private int getHeightHelper(Node<T> node) {
        if (node == null) {
            return -1;
        }
        int leftTreeHeight =  getHeightHelper(node.left);
        int rightTreeHeight = getHeightHelper(node.right);
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }
    /**
     * Helper Method to get the number of nodes recursively.
     * @param node specifies the node currently traversing.
     * @return the number of nodes in BST.
     *
     * Recursion Details:
     * Base Case: if we reach the leaf node return 0.
     * Recursion call: Increment one for this node and pass the left
     * and right child for two other recursive calls.
     */
    private int nodeNumberHelper(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + nodeNumberHelper(node.right) + nodeNumberHelper(node.left);
    }
    /**
     * Helper Method to search BST with alternative ordering.
     * @param node specifies the current node traversing.
     * @param toSearch specifies the key being searched.
     * @return null if not found, node of BST if found.
     *
     * Recursion Details:
     * Base Case: If we reach the leaf node return null, if we found the
     * node matching key, return the BST node.
     * Recursion Case: If the key is less than the current node data, pass
     * left child to a recursive call with key and comparator, else pass the
     * right child to a recursive call with key and comparator.
     */
    private Node<T> searchAlter(Node<T> node, T toSearch) {
        if (node == null || comparator.compare(toSearch, node.data) == 0) {
            return node;
        }
        if (comparator.compare(toSearch, node.data) < 0) {
            return searchAlter(node.left, toSearch);
        } else {
            return searchAlter(node.right, toSearch);
        }
    }
    /**
     * Helper Method to search BST with natural ordering.
     * @param node specifies the current node traversing.
     * @param toSearch specifies the key being searched.
     * @return null if not found, node of BST if found.
     *
     * Recursion Details:
     * Base Case: If we reach the leaf node return null, if we found the
     * node matching key, return the BST node.
     * Recursion Case: If the key is less than the current node data, pass
     * left child to a recursive call with key, else pass the
     * right child to a recursive call with key.
     */
    private Node<T> searchNatural(Node<T> node, T toSearch) {
        if (node == null || toSearch.compareTo(node.data) == 0) {
            return node;
        }
        if (toSearch.compareTo(node.data) < 0) {
            return searchNatural(node.left, toSearch);
        } else {
            return searchNatural(node.right, toSearch);
        }
    }
    /**
     * Helper Method to insert in BST with alternative ordering.
     * @param node specifies the current node traversing.
     * @param toInsert specifies the key being inserted.
     * @return the root of the BST after inserting.
     *
     * Recursion Details:
     * Base Cases: If we reach the empty leaf node space, insert node
     * in that place. If we find a node with key already present, just
     * return the same node.
     * Recursion Case: If the key is less than the current node data, pass
     * left child to a recursive call with key, else pass the
     * right child to a recursive call with key.
     */
    private Node<T> insertAlter(Node<T> node, T toInsert) {
        if (node == null) {
            return new Node<T>(toInsert);
        }
        int compareResult = comparator.compare(toInsert, node.data);
        if (compareResult == 0) {
            return node;
        }
        if (compareResult < 0) {
            node.left = insertAlter(node.left, toInsert);
        } else {
            node.right = insertAlter(node.right, toInsert);
        }
        return node;
    }
    /**
     * Helper Method to insert in BST with natural ordering.
     * @param node specifies the current node traversing.
     * @param toInsert specifies the key being inserted.
     * @return the root of the BST after inserting.
     *
     * Recursion Details:
     * Base Cases: If we reach the empty leaf node space, insert node
     * in that place. If we find a node with key already present, just
     * return the same node.
     * Recursion Case: If the key is less than the current node data, pass
     * left child to a recursive call with key, else pass the
     * right child to a recursive call with key.
     */
    private  Node<T> insertNatural(Node<T> node, T toInsert) {
        if (node == null) {
            return new Node<T>(toInsert);
        }
        if (toInsert.compareTo(node.data) == 0) {
            return node;
        }
        if (toInsert.compareTo(node.data) < 0) {
            node.left = insertNatural(node.left, toInsert);
        } else {
            node.right = insertNatural(node.right, toInsert);
        }
        return node;
    }
    /**
     * Private nested class to implement iterator methods.
     */
    private final class InOrderIterator implements Iterator<T> {
        /**
         * I used the stack class from Java Collection framework, as it
         * provides methods like pop() and push() to implement a inorder
         * traversal of BST. pop() to get the leftmost child, and push to
         * store nodes till leftmost child and store hen the right child.
         */
        private Stack<Node<T>> myStack;
        /**
         * constructor for nested iterator class.
         */
        private InOrderIterator() {
            myStack = new Stack<Node<T>>();
            fillLeftSub(root);
        }
        /**
         * Method to fill all the nodes on left subtree till
         * the leftmost child to iterate in inorder fashion as iterator
         * is created.
         * @param node specifies the root of the BST.
         */
        private void fillLeftSub(Node<T> node) {
           while (node != null) {
               myStack.push(node);
               node = node.left;
           }
        }
        /**
         * Implementation of hasNext() method.
         * Returns if there are any elements left in stack.
         */
        @Override
        public boolean hasNext() {
            return !myStack.isEmpty();
        }
        /**
         * Implementation of the next() method, pops the leftmost child
         * and then pushes the right child from that subtree using call
         * to own method to load all the left subtree from that node.
         */
        @Override
        public T next() {
            Node<T> cur = myStack.pop();
            fillLeftSub(cur.right);
            return cur.data;
        }
    }
    /**
     * Private static nested class for Node of BST.
     * @param <T> specifies the generic of BST.
     */
    private static class Node<T> {
        /**
         * Instance variable of type T.
         */
        private T data;
        /**
         * left child of the node.
         */
        private Node<T> left;
        /**
         * right child of the node.
         */
        private Node<T> right;
        /**
         * Constructor to build a new node.
         * @param d specifies the data to be stored.
         */
        Node(T d) {
            this(d, null, null);
        }
        /**
         * Constructor to build a new node with links to
         * left and right child.
         * @param d specifies the data to be stored.
         * @param l specifies the left child to be stored.
         * @param r specifies the right child to be stored.
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }

}
