/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gomoku;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author k
 */
public class OnePlayerBoard extends JPanel implements MouseListener{
    public static final int eachSide= 15;//row and column
    public static final String icon1_path="1.jpg";//location of stones
    public static final String icon2_path="2.jpg";
    public static final String icon3_path="3.jpg";
    int board[][]=new int[eachSide][eachSide];//2D array to store board
    MyJLabel board_label[][]=new MyJLabel[eachSide][eachSide];//JLabel used to show image of stone
    BufferedImage img1=null;//values for image of board
    BufferedImage img2=null;
    BufferedImage img3=null;
    ImageIcon icon1 =null;
    ImageIcon icon2 =null;
    ImageIcon icon3 =null;
    int icon1_value=1;
    int icon2_value=2;
    int icon3_value=3;
    int AI_level=0;//the level of AI
    boolean player_turn=true;//who's turn
    
    public OnePlayerBoard(int AIlevel){
        try{//initial image of board
            img1 = ImageIO.read(this.getClass().getResource(icon1_path));
            img2 = ImageIO.read(this.getClass().getResource(icon2_path));
            img3 = ImageIO.read(this.getClass().getResource(icon3_path));
        }catch(IOException e){
            e.printStackTrace();
        }
        icon1=new ImageIcon(img1);
        icon2=new ImageIcon(img2);
        icon3=new ImageIcon(img3);
        setLayout(new GridLayout(eachSide,eachSide,0,0));
        for(int i=0;i<board.length;i++){//initialize array and board
            for(int j=0;j<board[i].length;j++){
                board[i][j]=icon1_value;
                board_label[i][j]=new MyJLabel(i,j);
                board_label[i][j].setIcon(icon1);
                board_label[i][j].addMouseListener(this);
                add(board_label[i][j]);
            }
        }
        //set AIlevel
        AI_level=AIlevel;
    }
    
    public void reset(){
        player_turn=true;
        for(int i=0;i<board.length;i++){//reset array and board
            for(int j=0;j<board[i].length;j++){
                board[i][j]=icon1_value;
                board_label[i][j].setIcon(icon1);
            }
        }
        Object[] AIlevel = { 0, 1, 2, 3 };//choose AIlevel;
        Object selection = JOptionPane.showInputDialog(null, "choose the level of AI",
        "Select AI", JOptionPane.QUESTION_MESSAGE, null, AIlevel, AI_level);
            AI_level=(selection!=null)?(int)selection:AI_level;//prevent user closing window, null pointer will occour
    }

    public boolean checkWin(Point point,int icon_values){//check winners
        boolean result=false;
        int sum;
        int x=point.getX(); 
        int y=point.getY();
        int startX, startY;
        sum=1; startX=x-1;//only change x
        while(startX>=0&&board[startX][y]==icon_values){
            startX--;
            sum++;
        }
        startX=x+1;
        while(startX<eachSide&&board[startX][y]==icon_values){
            startX++;
            sum++;
        }
        if(sum>=5){ 
            result=true; 
            return result;
        }
        sum=1; startY=y-1;//only change y
        while(startY>=0&&board[x][startY]==icon_values){
            startY--;
            sum++;
        }
        startY=y+1;
        while(startY<eachSide&&board[x][startY]==icon_values){
            startY++;
            sum++;
        }
        if(sum>=5){ 
            result=true; 
            return result;
        }
        sum=1; startX=x-1; startY=y-1;//increase x y at same time
        while(startY>=0&&startX>=0&&board[startX][startY]==icon_values){
            startY--;
            startX--;
            sum++;
        }
        startX=x+1; startY=y+1;
        while(startY<eachSide&&startX<eachSide&&board[startX][startY]==icon_values){
            startY++;
            startX++;
            sum++;
        }
        if(sum>=5){ 
            result=true; 
            return result;
        }
        sum=1; startX=x-1; startY=y+1;//increase x,decrease y
        while(startY<eachSide&&startX>=0&&board[startX][startY]==icon_values){
            startY++;
            startX--;
            sum++;
        }
        startX=x+1; startY=y-1;
        while(startY>=0&&startX<eachSide&&board[startX][startY]==icon_values){
            startY--;
            startX++;
            sum++;
        }
        if(sum>=5){ 
            result=true; 
            return result;
        }
        return result;
    }

    public Point playersTurn(MyJLabel click_label){//set icon and value, after player made choice
        click_label.setIcon(icon2);
        board[click_label.getI()][click_label.getJ()]=icon2_value;
        //System.out.println(click_label.getI());
        //System.out.println(click_label.getJ());
        player_turn=false;
        return new Point(click_label.getI(),click_label.getJ());
    }
    
    public Point computersTurn(){//set icon and value, after computer made choice
        Point point=null;
        switch(AI_level){
                case 0: 
                    point=AITest();
                    break;
                case 1: 
                    point=AIOne();
                    break;
                default:
                    point=AITest();
                    break;
        }
        board[point.getX()][point.getY()]=icon3_value;
        board_label[point.getX()][point.getY()].setIcon(icon3);
        player_turn=true;
        return point;
    }
    
    public Point AITest(){//random generate location for computer, AI_level=0
        boolean empty=false;
        int x=0,y=0;
        while(!empty){
            x=(int) ((eachSide-1)*Math.random());
            y=(int) ((eachSide-1)*Math.random());
            if(board[x][y]==icon1_value){
                empty=true;
            }
        }
        return new Point(x,y);
    }
    
    public Point AIOne(){//AI_level=1
        int x=0,y=0;
        //function need to be added


        return new Point(x,y);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MyJLabel click_label=((MyJLabel)e.getComponent());
        if(player_turn==true&&click_label.getIcon()==icon1){//whether it is player's turn and there is no stone at same place                
            if(checkWin(playersTurn(click_label),icon2_value)){
                JOptionPane.showMessageDialog(null,"Player wins");
                this.reset();
            }else{
                if(checkWin(computersTurn(),icon3_value)){
                JOptionPane.showMessageDialog(null,"computer wins");
                this.reset();
                }
            }
        }
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    private class Point{//used for AI functions, each AI function returns a type
        private int x;
        private int y;
        Point(int inputX,int inputY){
            x=inputX;
            y=inputY;
        }
        public int getX(){return x;}
        public int getY(){return y;}
    }
}
