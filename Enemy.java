import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.awt.Graphics2D;
import java.util.TimerTask;


public class Enemy  {
    private int enemy_x;
    private int  enemy_y;
    private Image enemy;
    private int enemy_2x=300;
    private int  enemy_2y = 300;
    private int  width ;
    private int  height ;
    private boolean display = true;
    private boolean increase_x = true;
    private boolean increase_y = true;
    private int final_position;
    private int curr_position;
    private String Direction;
    private int enemy_number;
    public Enemy (Image img, int x, int y,int w , int h,String direction, int final_position){
        this.enemy_x = x;
        this.enemy_y = y;
        this.enemy = img;
        this.width = w;
        this.height = h;
        this.final_position = final_position;
        this.Direction = direction;
        if (direction == "Y"){
            this.curr_position = this.enemy_y;
        }else {
            this.curr_position = this.enemy_x;
        }
    }
    public void set_display_enemy(Boolean display){
        if (display == false){
            this.width = 0;
            this.height = 0;
        }
    }
    public int getFinal_position() {
        return this.final_position;
    }
    public String getDirection() {
        return this.Direction;
    }
    public int getPosition_enemy_x() {
        return enemy_x;
    }
    public int get_enemy_number() {
        return enemy_number;
    }

    public int getPosition_enemy_y() {
        return enemy_y;
    }
    public int getCurr_position() {
        return curr_position;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Image getImage_enemy() {
        return enemy;
    }

    public void set_Position_enemy_x(int x) {
        this.width = x;
        this.height=x;
    }
    public void set_Position_enemy_y(int y) {
      this.enemy_y = y;
    }


    public void enemy_movement(int curr_position,int final_position ,String direction){
        if (direction == "Y"){
            if (this.enemy_y > final_position && increase_y == true){
                this.enemy_y -=1;
            }
            else if (this.enemy_y == final_position && increase_y == true && this.enemy_y < curr_position){
                increase_y = false;
                this.enemy_y +=1;
            }
            else if (this.enemy_y > final_position && increase_y == false && this.enemy_y < curr_position){
                this.enemy_y +=1;
            }
            else if  (this.enemy_y < final_position && increase_y == true){
                this.enemy_y +=1;
            } else if (this.enemy_y == final_position) {
                increase_y = false;
                this.enemy_y -=1;
            }else if (this.enemy_y > curr_position && increase_y == false) {
                this.enemy_y -=1;
            }else if (this.enemy_y == curr_position && increase_y == false) {
                increase_y = true;
            }
        } else if (direction == "X") {
            if (this.enemy_x > final_position && increase_x == true){
                this.enemy_x -=1;
            } else if (this.enemy_x == final_position && this.enemy_x < curr_position && increase_x == true){
                this.increase_x = false;
                this.enemy_x +=1;
            }else if (increase_x == false && this.enemy_x < curr_position ){
                this.enemy_x +=1;
            }
            else if (increase_x == false && this.enemy_x == curr_position && this.enemy_x > final_position ){
                this.enemy_x -=1;
                increase_x = true;
            }


        }
    }


}
