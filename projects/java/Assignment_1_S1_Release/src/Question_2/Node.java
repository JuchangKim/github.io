/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

/**
 *
 * @author xhu
 */
public class Node <E extends Comparable> {
    public E data;
    public Node <E> next;
    
    public boolean equals(Node node)
    {
        if(this.data.equals(node.data))
        {
            return true;
        }
        return false;
    }
    
    public int compareTo(Node node)
    {
        return this.data.compareTo(node.data);
    }
}
