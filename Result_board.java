import java.io.*;
import java.util.*;
public class Result_board {
    public Result_board ( int coordinate_x, int coordinate_y,int width , int height , String result_board_type){
        this.result_board_position_x = coordinate_x;
        this.result_board_position_y = coordinate_y;
        this.width = width;
        this.height = height;
        this.result_board_type = result_board_type;
    }
    private int result_board_position_x;
    private int  result_board_position_y;
    private int  width ;
    private int  height ;
    private String result_board_type;

    public int getPosition_menu_x() {
        return result_board_position_x;
    }


    public int getPosition_menu_y() {
        return result_board_position_y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String get_result_board_type() {
        return result_board_type;
    }

    public String get_num_enemy_time(int enemy_num , int time){
        return  "In the " + time + " seconds you have killed " + enemy_num + " enemies" ;
    }
    public String get_num_enemy_time(double enemy_num , double time){
        return  "In the " + time + " seconds you have killed " + enemy_num + " enemies" ;
    }
}
