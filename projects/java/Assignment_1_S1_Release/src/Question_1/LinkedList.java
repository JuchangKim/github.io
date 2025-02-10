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
public class LinkedList <E extends Comparable>{
    
    public int size = 0;
    public Node<E> head;
    
    public void addHead(E data)
    {
        Node<E> newNode = new Node<>();
        newNode.data = data;
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    public Node getHead()
    {
        return head;
    }
    
    public void add(E data)
    {
        if(head == null)
        {
            addHead(data);
            
            return;
        }
        Node<E> newNode = new Node<>();
        newNode.data = data;
        add(head, newNode);
    }
    
    private void add(Node head, Node node)
    {
        if(head.next == null)
        {
            head.next = node;
            size++;
        }
        else
        {
            add(head.next, node);
        }
    }
    
    public void printLinkedList()
    {
        printLinkedList(head);
    }
    
    private void printLinkedList(Node node)
    {
        if(node == null)
        {
            return;
        }
        System.out.println(node.data);
        printLinkedList(node.next);
    }
    
    public boolean contains(E data)
    {
        Node<E> newNode = new Node<>();
        newNode.data = data;
        return contains(head, newNode);
    }
    
    private boolean contains(Node head, Node node)
    {
        if(head == null)
        {
            return false;
        }
        if(head.data.equals(node.data))
        {
            return true;
        }
        return contains(head.next, node);
    }
    
    public void remove(E data)
    {
        Node<E> newNode = new Node<>();
        newNode.data = data;
        if(head == null)
        {
            return;
        }
        if(head.data.equals(data))
        {
            head = head.next;
            size--;
            return;
        }
        if(contains(newNode.data))
        {
        removeFromBody(head, newNode);
        }
    }
    
    public void remove(int position)
    {
     if(position < 0 || position >= size)
     {
         return;
     }
     size--;
     if(position == 0)
     {
         head = head.next;
         return;
     }
     removeByIndex(head, position - 1);
    }
    
    private void removeByIndex(Node head, int position)
    {
        if(position == 0)
        {
            head.next = head.next.next;
            
            return;
        }
        removeByIndex(head.next, position - 1);
    }
    
    private void removeFromBody(Node head, Node node)
    {
        if(head == null || node == null)
        {
            return;
        }
        
        if(head.next != null && head.next.data.equals(node.data))
        {
            head.next = head.next.next;
            size--;
            return;
        }
        removeFromBody(head.next, node);
    }
    
    public Node removeFromHead()
    {
        if(head == null)
        {
            return null;
        }
        Node<E> removedNode = head;
        head = head.next;
        size--;
        return removedNode;
    }
    
    public Node removeFromTail()
    {
        if(head == null || head.next == null)
        {
            return removeFromHead();
        }
        return removeFromTail(head);
    }
    
    private Node removeFromTail(Node node)
    {
        if(node.next.next == null)
        {
            Node<E> removedNode = node.next;
            
            node.next = null;
            size--;
            
            return removedNode;
        }
        return removeFromTail(node.next);
    }
    
    public void addInOrder(E data)
    {
        Node<E> newNode = new Node<>();
        newNode.data = data;
        addInOrder(head, newNode);
        size++;
    }
    
    private void addInOrder(Node currentNode, Node newNode)
    {
        if(currentNode == null || newNode.data.compareTo(currentNode.data) < 0)
        {
            newNode.next = currentNode;
            head = newNode;
        }
        else if(currentNode.next == null || newNode.data.compareTo(currentNode.next.data) < 0)
        {
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
        else
        {
            addInOrder(currentNode.next, newNode);
        }
    }
    
    public Node getNode(int index)
    {
        if(index < 0 ||  index >= size)
        {
            return null;
        }
        return getNode(index, head);
    }
    
    private Node getNode(int index, Node head)
    {
        if(index == 0)
        {
            return head;
        }
        return getNode(index - 1, head.next);
    }
    
    public E getData(int index)
    {
        if(index < 0 || index >= size)
        {
            return null;
        }
        return getData(index, head);
    }
    
    private E getData(int index, Node head)
    {
        if(index == 0)
        {
            return (E) head.data;
        }
        return getData(index - 1, head.next);
    }    
}
