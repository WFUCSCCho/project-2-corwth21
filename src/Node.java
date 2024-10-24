/**
 * @file: Node.java
 * @description: This program implements all necessary functions and actions of a node,
 * such as set and get functions, as well as left and right leaves
 * @author: Tucker Corwen
 * @date: September 23, 2024
 */

public class Node<T extends Comparable<T>> {
    // The value or element stored in this node
    T obj;

    // Reference to the left child node
    Node<T> left;

    // Reference to the right child node
    Node<T> right;

    // Default constructor initializes an empty node with no element and no children
    public Node() {
        this.obj = null;
        this.left = null;
        this.right = null;
    }

    // Constructor with parameters to initialize the node with an element and references to left and right children
    public Node(T obj, Node<T> left, Node<T> right) {
        this.obj = obj;    // Set the element of the node
        this.left = left;  // Set the left child
        this.right = right; // Set the right child
    }

    // Method to set the element of the node
    public void setElement(T obj) {
        this.obj = obj;
    }

    // Method to set the left child node
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    // Method to set the right child node
    public void setRight(Node<T> right) {
        this.right = right;
    }

    // Method to get the left child node
    public Node<T> getLeft() {
        return left;
    }

    // Method to get the right child node
    public Node<T> getRight() {
        return right;
    }

    // Method to get the element stored in this node
    public T getElement() {
        return obj;
    }

    // Method to check if the node is a leaf (i.e., has no children)
    public boolean isLeaf() {
        return left == null && right == null;
    }
}
