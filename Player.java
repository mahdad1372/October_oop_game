import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    JLabel label;
    private int position_x;
    private int position_y;
    private ImageIcon Icon_player;
    public Player(int x , int y, String icon){
        this.position_x =x;
        this.position_y = y;
        this.Icon_player = new ImageIcon(icon);
    }




    public int getPosition_x(){
        return this.position_x;
    }
    public int getPosition_y(){
        return this.position_y;
    }
    public ImageIcon getPlayerIcon(){
        return  this.Icon_player;
    }
    public JLabel getLabelPlayer(){
        return this.label;
    }
    public void setPosition_x(int x){
        this.position_x = x;
    }
    public void setPosition_y(int y){
        this.position_y = y;
    }
    public void setPlayerIcon(String icon){
        this.Icon_player = new ImageIcon(icon);;
    }
    public void player_movement(String direction){
        switch (direction){
            case "Left":
                this.label.setLocation(this.label.getX()-10,this.label.getY());
                break;
            case "Down": this.label.setLocation(this.label.getX(),this.label.getY()-10);
                break;
            case "Right":
                this.label.setLocation(this.label.getX()+10,this.label.getY());
                break;
            case "Up": this.label.setLocation(this.label.getX(),this.label.getY()+10);
                break;
        }
    }
}
