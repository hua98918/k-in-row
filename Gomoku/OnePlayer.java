/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gomoku;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author k
 */
public class OnePlayer extends JFrame{
    Menu mainMenu=null;
    public OnePlayer(Menu m,int AIlevel){
        setTitle("One Player vs Computer");
        setSize(600,600);
        setLocationRelativeTo(null);
        this.addWindowListener(new MyWindowAdapter());
        OnePlayerBoard board=new OnePlayerBoard(AIlevel);
        this.setLayout(new FlowLayout());
        this.add(board);
        setVisible(true);
        mainMenu=m;
        mainMenu.setVisible(false);
    }
    
    private class MyWindowAdapter extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){  
        super.windowClosing(e);
        mainMenu.setVisible(true);
        }
    }  
}
