/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

import java.util.Date;

/**
 *
 * @author xhu
 */
public class Memo {
    Date date;
    String title;
    String message;
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
    sb.append("Date: ").append(date != null ? date.toString() : "null").append("\n");
    sb.append("Title: ").append(title != null ? title : "null").append("\n");
    sb.append("Message: ").append(message != null ? message : "null").append("\n");
    return sb.toString();
        
    }
}
