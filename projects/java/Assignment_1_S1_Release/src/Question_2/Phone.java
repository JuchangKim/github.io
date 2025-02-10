// Question: Which object(s) have you chosen for the synchronize? Why?
/*
Answer: Synchronized Object : RepairShop.
        Synchronized Methods : enterRepairShop, exitRepairShop.
        Reason:  The RepairShop object represents the resource (the repair shop)
                 that needs protection against concurrent access. 
                 By synchronizing on the RepairShop object itself,
                 we ensure that access to critical sections of code
                 (entering and exiting the repair shop) is serialized, 
                 meaning only one thread can execute those sections at a time,
                 thereby preventing race conditions and ensuring thread safety.

*/

package Question_2;

import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Toolkit;

public class Phone implements Runnable{
  
  int x = 0; 
  int y = 0;  
  int vx = 0; 
  int vy = 0;  
  int delay = 10;  
  int width = 1000;  
  int height = 1000;  
  Panel panel;
  RepairShop repairShop;
  Random random = new Random();
  
   // default constructor that creates a new set 
   // that is initially empty
    private Color color = Color.BLUE;
  
  
    enum PhoneState
    {
        HEALTHY,
        INFECTED,
        MOVING_TO_REPAIR_SHOP
    }
    
    PhoneState state = PhoneState.HEALTHY;
    int lifespan = 500;
    
  public Phone() {
    
   
   x = random.nextInt(width);
        y = random.nextInt(height);
        vx = random.nextBoolean() ? -1 : 1;
        vy = random.nextBoolean() ? -1 : 1;
    
  }
  
  public Phone(Panel panel)
  {
      this.panel = panel;
      this.repairShop = panel.repairShop;
  }
  
  public void setPanel(Panel panel)
  {
      this.panel = panel;
  }
  public void setRange(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
   
  
  public void run() {
      while(lifespan != 0)
      {
          move();
          if(state == PhoneState.INFECTED)
          {
              
              lifespan--;
              transmitVirus();
          } else if(state == PhoneState.MOVING_TO_REPAIR_SHOP)
          {
              moveTowardsRepairShop();
              
          } 
      }
      if(lifespan == 0)
      {
      panel.removePhone(this);
      }
  }
   
  private void transmitVirus()
  {
      java.util.LinkedList<Phone> phoneCopy = new java.util.LinkedList<>(panel.phoneList);
      for(Phone otherPhone : phoneCopy)
      {
          if(otherPhone != null && otherPhone.getState() == PhoneState.HEALTHY)
          {
              double distance = Math.sqrt(Math.pow(x - otherPhone.x, 2) + Math.pow(y - otherPhone.y, 2));
              if(distance <= 30)
              {
                  otherPhone.setState(PhoneState.INFECTED);
                  try{
                        Thread.sleep(2000);
                        this.setState(Phone.PhoneState.MOVING_TO_REPAIR_SHOP);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
              }
          }
          else if (otherPhone != null && otherPhone != this && otherPhone.getState() == PhoneState.INFECTED) {
            double distance = Math.sqrt(Math.pow(x - otherPhone.x, 2) + Math.pow(y - otherPhone.y, 2));
            if (distance <= 30) {
                new Thread(() -> {
                    try{
                        Thread.sleep(3000);
                        this.setState(Phone.PhoneState.MOVING_TO_REPAIR_SHOP);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }).start();
                
                moveTowardsRepairShop(); // Move infected phone towards repair shop
                
                return;
            }
         }
      }
      
  }
  public void move() {
      
      if(state != PhoneState.INFECTED)
      {
     x += vx;
     y += vy;
     
    if (x > width || x < 0)
      vx *= -1; 
    if (y > height || y < 0)
      vy *= -1; 
      }
    try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  }  
  
  private void moveTowardsRepairShop()
  {
      int dx = 500 - x;
      int dy = 10 - y;
      
      double distanceToRepairShop = Math.sqrt(dx * dx + dy * dy);
    
    // Check if the phone is close to the repair shop
    if (distanceToRepairShop < 2) {
        synchronized(panel.repairShop) {
            panel.repairShop.enterRepairShop(this);
            panel.repairShop.exitRepairShop();
        }
        return;
    }
      
      double magnitude = Math.sqrt(dx * dx + dy * dy);
      double normalizedDx = dx / magnitude;
      double normalizedDy = dy / magnitude;
      
      vx = (int) Math.round(normalizedDx);
      vy = (int) Math.round(normalizedDy);
      
      x += vx;
      y += vy;
      
      
      lifespan--;
    
  }

void setColor(Color color) {
   this.color = color;
}
public void setState(PhoneState state)
{
    this.state = state;
}
public PhoneState getState()
{
    return state;
}
public Color getColor()
{
    return color;
}
}
