/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gomoku;

import javax.swing.JLabel;

/**
 *
 * @author k
 */
public class MyJLabel extends JLabel{
    private int i,j;//connect array and label
    
    private MyJLabel(){
        super();
    }
    
    public MyJLabel(int arrayI, int arrayJ)
    {
        this();
        i=arrayI;
        j=arrayJ;
    }
    
    public int getI(){
        return i;
    }
    
    public int getJ(){
        return j;
    }

}
