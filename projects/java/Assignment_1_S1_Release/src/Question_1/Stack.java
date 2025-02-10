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
public class Stack <E extends Comparable>{
    LinkedList<E> stack = new LinkedList();
    
    public void push(E data)
    {
        stack.addHead(data);
    }
    
    public E pop()
    {
        return (E) stack.removeFromHead().data;
    }
    
    public void printStack()
    {
         stack.printLinkedList();
    }
    
    public int getSize()
    {
        return stack.size;
    }

    public boolean isEmpty() {
        if(stack.head == null)
        {
            return true;
        }
        else
        {
            return false;//To change body of generated methods, choose Tools | Templates.
        }
    }
}
