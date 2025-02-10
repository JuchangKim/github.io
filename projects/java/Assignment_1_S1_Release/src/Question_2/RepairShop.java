/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author xhu
 */
public class RepairShop {
    int x;
    int y;
    int width = 30;
    int height = 10;
    boolean occupied;
    Phone occupyingPhone;
    
    public RepairShop(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(500, 0, width, height);
    }
    
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
        
    
     public  boolean enterRepairShop(Phone phone) {
         synchronized(this)
         {
            if(!occupied)
            {
             
                 this.occupyingPhone = phone;
                 occupyingPhone.lifespan = -1;
                 occupyingPhone.x = 500;
                 occupyingPhone.y = 0;
                 this.occupied = true;
                 try{
                        Thread.sleep(3000);
                        
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                 return true;
            }
            else
            {
                 return false;
            }
         }
    }
     public void exitRepairShop()
     {
         synchronized(this)
         {
             
            this.occupied = false;
            this.occupyingPhone.setState(Phone.PhoneState.HEALTHY);
            this.occupyingPhone = null;
         }
     }
     
     public boolean isOccupied()
     {
         return occupied;
     }
     public Phone getOccupyingPhone()
     {
         return occupyingPhone;
     }
}
