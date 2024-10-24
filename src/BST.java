/**
 * @file: BST.java
 * @description: This program implements all necessary functions and actions of a BST, such as an iterator, insert, remove etc
 * @author: Tucker Corwen
 * @date: October 35, 2024
 */


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

class BST<T extends Comparable<T>> {
    private Node<T> root; // Root node of the tree
    private int size; // Number of nodes in the tree

    // Implement the constructor
    public BST() {
        root = null;
        size = 0;
    }

    // Implement the getMax method
    public Node<T> getMax(Node<T> root) {
        if (root == null) {
            return null; // If tree is empty, return null
        }
        // Traverse to the rightmost node (which is the maximum)
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    // Implement the clear method
    public void clear() {
        root = null;  // Remove the reference to the root, making the tree empty
        size = 0;     // Reset the size of the tree to 0
    }

    // Implement the isEmpty method
    public boolean isEmpty() { return root == null; }

    // Implement the size method
    public int size(){
        return size;
    }

    // Implement the insert method
    public void insert(T obj){
        root = insertHelper(root, obj);
        size++;
    }
    // Recursive helper function to insert 'obj' into the subtree rooted at 'root'
    public Node<T> insertHelper(Node<T> root, T obj){
        if (root == null) {
            size++;
            // If root is null, we have reached the correct position for insertion
            return new Node<T>(obj, null, null);
        }else if(root.getElement().compareTo(obj) > 0){
            // If obj is smaller than the current node, insert into the left subtree
            root.setLeft(insertHelper(root.getLeft(), obj));
        }else{
            // If obj is greater, insert into the right subtree
            root.setRight(insertHelper(root.getRight(), obj));
        }
        return root; // Return the updated root node
    }


    // Implement the remove method
    public T remove(T obj){
        T temp = searchHelper(root, obj); // Search for the element to be removed
        if (temp != null) {
            root = removeHelper(root, obj); // Recursively remove the element
            size--;
        }
        return temp; // Return the removed element, or null if not found
    }

    // Recursive helper function to remove 'obj' from the subtree rooted at 'root'
    public Node<T> removeHelper(Node<T> root, T obj){
        if (root == null) return null;

        if (root.getElement().compareTo(obj) > 0){
            // If obj is smaller, continue searching in the left subtree
            root.setLeft(removeHelper(root.getLeft(), obj));
        }else if (root.getElement().compareTo(obj) < 0){
            // If obj is greater, continue searching in the right subtree
            root.setRight(removeHelper(root.getRight(), obj));
        }else{
            if (root.getLeft() == null){
                return root.getRight(); // If no left child, replace with right subtree
            }else if (root.getRight() == null){
                return root.getLeft(); // If no right child, replace with left subtree
            }else{
                // Node has two children: find the maximum node in the left subtree
                Node<T> temp = getMax(root.getLeft());
                // Replace current node's value with the maximum value
                root.setElement(temp.getElement());
                // Recursively remove the maximum node from the left subtree
                root.setLeft(removeHelper(root.getLeft(), temp.getElement()));
            }
        }
        return root; // Return the updated root
    }

    // Implement the search method
    public T search(T obj){ return searchHelper(root, obj); }

    // Recursive helper function to search for 'obj' in the subtree rooted at 'root'
    public T searchHelper(Node<T> root, T obj){
        if (root == null) return null;
        if (root.getElement().compareTo(obj) > 0){
            // If obj is smaller, search in the left subtree
            return searchHelper(root.getLeft(), obj);
        }
        else if (root.getElement().compareTo(obj) < 0){
            // If obj is greater, search in the right subtree
            return searchHelper(root.getRight(), obj);
        }else{
            // If obj is equal, return the current node's element (found)
            return root.getElement();
        }
    }

    // In-Order Traversal and Printing
    public void print(){
        inOrderHelper(root);
        System.out.println();
    }

    // Recursive helper function for in-order traversal
    private void inOrderHelper(Node<T> obj){
        if (obj == null) return;

        inOrderHelper(obj.getLeft());
        System.out.println(obj.getElement());
        inOrderHelper(obj.getRight());
    }


    // Implement the iterator method
    public Iterator iterator(){
        return new BSTIterator();
    }


    // Implement the BSTIterator class
    private class BSTIterator implements Iterator<T>{
        private Node<T> nextNode = root;
        private int size;
        private Stack<Node<T>> nodeStack = new Stack<Node<T>>();

        // Check if there is a next node to visit
        @Override
        public boolean hasNext() { return nextNode != null; }

        // Prepare an in-order traversal starting from the root
        public void inorder_iterator(){
            if(root != null){
                nodeStack = new Stack<Node<T>>();
                goLeftFrom(root);
            }
        }

        // Helper function to traverse the leftmost path from a given node
        private void goLeftFrom(Node<T> obj){
            while (obj.getLeft() != null){
                nodeStack.push(obj);
                obj = obj.getLeft();
            }
        }

        // Return the next element in the traversal
        @Override
        public T next() {
            Node<T> current = null; // The current node being visited
            if (!nodeStack.empty()) {
                current = nodeStack.peek();
                nodeStack.pop();

                if (current.getRight() != null) { // If there is a right subtree, visit its leftmost path
                    goLeftFrom(current.getRight());
                }

            }
            return current.getElement(); // Return the element of the current node
        }

    }
}

