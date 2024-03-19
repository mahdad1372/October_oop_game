import java.io.*;
import java.util.*;
public class Menu {
    public Menu ( int coordinate_x, int coordinate_y,int width , int height , String menu_type){
        this.Menu_coordinate_x = coordinate_x;
        this.Menu_coordinate_y = coordinate_y;
        this.width = width;
        this.height = height;
        this.menu_type = menu_type;
    }
    private int Menu_coordinate_x;
    private int  Menu_coordinate_y;
    private int  width ;
    private int  height ;
    private String menu_type;

    public int getPosition_menu_x() {
        return Menu_coordinate_x;
    }


    public int getPosition_menu_y() {
        return Menu_coordinate_y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String get_menu_type() {
        return menu_type;
    }

    public String get_num_enemy_time(int enemy_num , int time){
        return  "In the " + time + " seconds you have killed " + enemy_num + " enemies" ;
    }
    public String get_num_enemy_time(double enemy_num , double time){
        return  "In the " + time + " seconds you have killed " + enemy_num + " enemies" ;
    }
}
