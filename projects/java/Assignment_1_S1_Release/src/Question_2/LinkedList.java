/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

/**
 *
 * @author xhu
 * @param <E>
 */
public class LinkedList <E extends Comparable>{
    
    public int size = 0;
    public Node<E> head;
    
    
    public void addHead(E data)
    {
        Node<E> newNode = new Node();
        newNode.data = data;
        head = newNode;
        size++;
        
    }
    
    public Node getHead()
    {
        Node<E> current = new Node();
        current = head;
        
        return current;
    }
    
    public void add(E data)
    {
        if (size == 0) {
            addHead(data);
        } else if(size > 0)
        {
            Node<E> current = head;
            Node<E> newNode = new Node<>();
            newNode.data = data;
            
            while(current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
            size++;
        }
    }
    
    private void add(Node head, Node node)
    {

    }
    
    public void printLinkedList()
    {
        Node<E> output = head;
        while(output.next != null)
        {
            System.out.println(output.data + " ");
            output = output.next;
        
        }
    }
    
    private void printLinkedList(Node node)
    {

    }
    
    public boolean contains(E data)
    {
     Node<E> currentNode = head;
        
        boolean found = false;
        while(currentNode != null && !found)
        {
            if(currentNode.data.equals(data))
            {
                found = true;
            }
            else
            {
                currentNode = currentNode.next;
            }
        }
        return found;
    }
    
    private boolean contains(Node head, Node node)
    {
        return false;
    }
    
    public void remove(E data)
    {
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
        
        Node<E> current = head;
        while(current.next != null && !current.next.data.equals(data))
        {
            current = current.next;
        }
        
        if(current.next != null)
        {
            current.next = current.next.next;
            size--;
            while(current.next != null)
            {
                current = current.next;
            }
        }
    }
    
    public void remove(int position)
    {
        if(position > 0 || position <= size)
        {
            if(position == 0)
            {
                head = head.next;
            }
            else
            {
                Node<E> current = head;
                for(int i = 0; i < position - 1; i++)
                {
                    current = current.next;
                }
                current.next = current.next.next;
            }
        }
         size--;
    }
    
    private void removeByIndex(Node head, int position)
    {

    }
    
    private void removeFromBody(Node head, Node node)
    {

    }
    
    public Node removeFromHead()
    {
        Node<E> current = head;
        if(current != null)
        {
            current = head.next;
            size--;
        }
        return current;
    }
    
    public Node removeFromTail()
    {
        Node<E> current = head;
        if(current == null) 
        {
            return null;
        }
        
        if(current.next == null)
        {
            current.data = null;
        }
        else
        {
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
            
        }
        size--;
        return current;
    }
    
    private Node removeFromTail(Node node)
    {
        return null;
    }
    
    public void addInOrder(E data)
    {
        Node<E> newNode = new Node<E>();
        newNode.data = data;
    if (head == null || head.data.compareTo(data) >= 0) {
        newNode.next = head;
        head = newNode;
    } else {
        Node<E> current = head;
        while (current.next != null && current.next.data.compareTo(data) < 0) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }
    size++;
    }
    
    private void addInOrder(Node currentNode, Node newNode)
    {

    }
    
    public Node getNode(int index)
    {
        if(index >= 0 && index <= size)
        {
         Node<E> current = head;
         for(int i = 0; i < index; i++)
         {
             current = current.next;
         }
         
         return current;
        }
        else
        {
        return null;
        }
    }
    
    private Node getNode(int index, Node head)
    {
     return null;   
    }
    
    public E getData(int index)
    {
        Node<E> node = getNode(index);
         if(node != null)
        {
        return node.data;
        }
        else
         {
             return null;
         }
    }
    
    private E getData(int index, Node head)
    {
        return null;
    }    
}
