/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

/**
 *
 * @author xhu
 */
import java.util.ArrayList;
import java.util.List;

public class BinaryTree<E extends Comparable<E>, F extends Comparable<F>> {
    Node<E, F> root;
    int number_of_nodes;
    List<Node<E, F>> nodeList;
    boolean reverse;

    public BinaryTree() {
        root = null;
        number_of_nodes = 0;
        nodeList = new ArrayList<>();
        reverse = false;
    }

    public void addElement(E element, F key) {
        Node<E, F> newNode = new Node<>();
        newNode.element = element;
        newNode.key = key;
        addNode(root, newNode);
        number_of_nodes++;
    }
    
    
    /*
    In the MemoManager class, originally addmemo method(date, title, message) in the
    MemoManger pass to the addToTree(memo, key) in the MemoManager, Then, the method
    pass to the addElement(element, key) in this class, finally, the the addElement
    pass to the addNode(root, newNode). In this method, the newNode which has new memo
    element and memo key compare to this binary tree nodes and the newNode added to root as a leaf.
    Then the order of nodes are ascending.
    */
    private void addNode(Node<E, F> root, Node<E, F> newNode) {
        if (root == null) {
            this.root = newNode;
            return;
        }

        if (newNode.compareTo(root) < 0) {
            if (root.left == null) {
                root.left = newNode;
            } else {
                addNode(root.left, newNode);
            }
        } else {
            if (root.right == null) {
                root.right = newNode;
            } else {
                addNode(root.right, newNode);
            }
        }
    }

    /*
    by making boolean which is reverse, the traversal method can be switched reverse order
    or not. If this binary tree has reverse value is false, the traversal(root) order is forward.
    However, If this binary tree has reverse value is true, the traversal(root) order is reversed.
    
    this reverseOrder method which is public condition does not relative to private 
    reverseOrder(root) method, but the private reverseOrder(root) is connected to traversal
    method and using reverseOrder() method, the traversal(root) method is switching to reverse.
    */
    public void reverseOrder() {
        this.reverse = true;
        reverseOrder(root);
    }

    private void reverseOrder(Node<E, F> root) {
        
        if (root != null) {
            reverseOrder(root.right);
            nodeList.add(root);
            reverseOrder(root.left);
        }
    }

    public E searchElement(F key) {
        Node<E, F> resultNode = searchNode(root, key);
        return resultNode != null ? resultNode.element : null;
    }

    /*
    Oringinally, it started from findMemo in the MemoManager class, the findMemo(key) 
    method making distiguish key value type which is String or Date. 
    
    Then it pass to searchElement(key) method in this class. then the searchElement method
    pass to searchNode(root, key), then the searchNode method find the key if there 
    is a same node with key or not. If it finds, the root is returned to back which is
    searchElement method, then the searchElement also return the searched node or
    return null value.
    
    Finally, the findMemo method is back and the return key is
    attached to new memo.title or new memo.date then the new memo is returned.
    */
    
    private Node<E, F> searchNode(Node<E, F> root, F key) {
        if (root == null || root.key.compareTo(key) == 0) {
            return root;
        }

        if (root.key.compareTo(key) > 0) {
            return searchNode(root.left, key);
        } else {
            return searchNode(root.right, key);
        }
    }
    /*
    this traversal(root) method is adding each node to nodeList. However, 
    If this binary tree reverse value is ture, the traversal method is connected to
    reverseOrder(root) method, then the reverseOrder(root) is adding each node 
    to nodeList but it is reversed order. To make reverse, the reverseOrder() method
    is needed to be called. Basically, if the reverse value is false, the root traverse
    from small value to large value. if reverse, it traverse from large value to small value.
    */
    public void traversal(Node<E, F> root) {
        if (root != null) {
            if(!reverse)
            {
                traversal(root.left);
                nodeList.add(root);
                traversal(root.right);
            } else if(reverse)
            {
                reverseOrder(root);
            }
            
        }
        
    }

    public Node<E, F>[] toSortedList() {
        nodeList.clear();
        traversal(root);
        Node<E, F>[] sortedNodes = nodeList.toArray(new Node[nodeList.size()]);
        nodeList.clear(); // Clear the nodeList for future use
        return sortedNodes;
    }
}