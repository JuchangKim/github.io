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
public class DataAnalysis <E extends Comparable>{
    
    private E[] data;
    
    public DataAnalysis(E[] data)
    {
        this.data = data;
    }
    
    public boolean bracketEvaluator()
    {
        Stack<Character> stack = new Stack<>();
        
        for(E ch : data)
        {
            if(ch.equals('(') || ch.equals('{') || ch.equals('[') || ch.equals('<'))
            {
                stack.push((Character) ch);
            }
            else if(ch.equals(')') || ch.equals('}') || ch.equals(']') || ch.equals('>'))
                    {
                        if(stack.isEmpty())
                        {
                            return false;
                            
                        }
                        char top = (char) stack.pop();
                        if((ch.equals(')') && top != '(') || (ch.equals('}') && top != '{')
                                || (ch.equals(']') && top != '[') || (ch.equals('>') && top != '<'))
                        {
                            return false;
                        }
                    }
        }
       
        return true;
    }
        
}
