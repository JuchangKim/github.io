/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author xhu
 */
public class Panel extends JPanel implements KeyListener, ComponentListener {

    //"example" is just an example to show you how the animation can be done
    //You need to remove the "example" before you submit your code.
    
    int INITIAL_CAPACITY = 500;
    LinkedList<Phone> phoneList = new LinkedList<Phone>();
    Phone phone;
    RepairShop repairShop;
    JFrame frame;
    private int repairShopRange = 30;
    
    public Panel(JFrame frame)
    {
        this.frame = frame;
        
        repairShop = new RepairShop(500, 0);
        phoneList = new LinkedList<>();
        phone = new Phone();
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.setFocusable(true);
        
    }
    

    
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        LinkedList<Phone> phoneCopy = new LinkedList<>(phoneList);
        
        for(Phone p: phoneCopy)
        {
        Color color = getColorForState(p.getState());
        g.setColor(color);
        g.fillOval(p.x, p.y, 10, 10);
        }
        //currently we call run() method here. After Phone class is implemented as thread
        //you are going to remove the following code "example.run()".
         
        repairShop.draw(g);
        repaint();
    }
    
    private Color getColorForState(Phone.PhoneState state) {
        switch (state) {
            case HEALTHY:
                return Color.BLUE;
            case INFECTED:
                return Color.RED;
            case MOVING_TO_REPAIR_SHOP:
                return Color.GREEN;
            default:
                return Color.BLUE;
        }
    }
    
     public void addPhone(Phone newphone) {
        newphone.setPanel(this);
         phoneList.add(newphone);
        
        Thread thread = new Thread(newphone);
        thread.start();
    }
     
     public void removePhone(Phone phone) {
        phoneList.remove(phone);
    }
     
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
           Phone newphone = new Phone();
        addPhone(newphone);
        }
        else if(e.getKeyCode() == KeyEvent.VK_V)
        {
            infectRandomPhone();
        }
    }
    
    private void infectRandomPhone() {
        if (!phoneList.isEmpty()) {
            int randomIndex = (int) (Math.random() * phoneList.size());
            Phone randomPhone = phoneList.get(randomIndex);
            if (randomPhone.getState() == Phone.PhoneState.HEALTHY) {
                randomPhone.setState(Phone.PhoneState.INFECTED);
                new Thread(() -> {
                    try{
                        Thread.sleep(3000);
                        randomPhone.setState(Phone.PhoneState.MOVING_TO_REPAIR_SHOP);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
    
    
    
    @Override
    public void keyReleased(KeyEvent ke) {
        
    }    

    @Override
    public void componentResized(ComponentEvent ce) {
        phone.setRange(frame.getWidth(), frame.getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent ce) {

    }

    @Override
    public void componentShown(ComponentEvent ce) {

    }

    @Override
    public void componentHidden(ComponentEvent ce) {

    }
    
    public void setPhoneColor(Phone phone, Color color) {
    phone.setColor(color);
    repaint(); // Repaint the panel to reflect the color change
}
    public LinkedList<Phone> getPhoneListCopy()
    {
        return new LinkedList<>(phoneList);
    }
    private void moveAwayFromRepairShop(Phone p) {
        
    }
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
