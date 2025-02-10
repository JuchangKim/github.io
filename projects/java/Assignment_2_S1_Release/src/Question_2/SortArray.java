/*
Question 1: Is quick sort the best way of finding median? Why?
Answer: No, quick sort is not the best way of finding the median in this specific case 
because it has a time complexity of O(nlogn), which is less efficient compared to the 
linear time complexity achievable with bin sort for a fixed range of values.

Question 2: What is another good way of finding median?
Answer: Bin sort (or bucket sort) is a better way to find the median in this context. 
Since the pixel intensity values are in a fixed range (0 to 255), we can efficiently
use bin sort to count occurrences and find the median with a total time complexity of 
O(m), where 𝑚 is the range of pixel values. 
This method is more efficient and straightforward for this specific problem.
*/


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import java.util.Collections;

/**
 *
 * @author xhu
 */
public class SortArray <E extends Comparable>{
    
    E[] array;
    
    public SortArray(E[] array)
    {
        this.array = array;
    }
    
    public void setArray(E[] array)
    {
        this.array = array;
    }
    
    public void quickSort()
    {
     quickSort(array, 0, array.length - 1);   
    }
    
    private void quickSort(E[] list, int h, int t)
    {
        if(h < t)
        {
            int pivot = partition(list, h ,t);
            quickSort(list, h, pivot - 1);
            quickSort(list, pivot + 1, t);
        }
    }
    private int partition(E[] list, int h, int t)
    {
        E pivot = list[t];
        int i = h - 1;
        
        for(int j = h; j <= t - 1; j++)
        {
            if(list[j].compareTo(pivot) <= 0)
            {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, t);
        return i + 1;
    }
    private void swap(E[] list, int a, int b)
    {
        E temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }    
}
