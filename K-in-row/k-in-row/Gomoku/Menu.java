/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gomoku;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author k
 */
public class Menu extends JFrame{
    static Menu m=null;
    OnePlayer op=null;
    public Menu(){
        setTitle("Gomoku");
        setSize(300, 200);
        setLocationRelativeTo(null);
        JLabel label1=new JLabel(" ");
        JPanel centerPanel = new JPanel();
        JButton button1=new JButton("One Player vs Computer");
        JButton button2=new JButton("Two Player vs Computer");
        button1.addActionListener(new Button1ActionListener());
        button2.addActionListener(new Button2ActionListener());
        centerPanel.add(button1);
        centerPanel.add(button2);
        add(label1,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    public static void main(String[] args){
        m=new Menu();
    }
    
    private class Button1ActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            if(op!=null){
                op.dispose();
                op=null;
            }
            Object[] AIlevel = { 0, 1, 2, 3 };//choose AIlevel;
            Object selection = JOptionPane.showInputDialog(null, "choose the level of AI",
        "Select AI", JOptionPane.QUESTION_MESSAGE, null, AIlevel, 0);
            op=new OnePlayer(m,(selection!=null)?(int)selection:0); //prevent user closing window, null pointer will occour   
        }     
    }  
      
    private class Button2ActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
                
        }     
    }  
}
