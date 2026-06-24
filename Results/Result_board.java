package Results;

import javax.swing.*;
import java.util.ArrayList;

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
    private static int Seconds_Duration_Game;
    private static boolean display_menu_winner;
    private static int number_enemy_killed = 0;
    private static int scores = 0;
    private static int Health = 100;

    public static int getNumber_enemy_killed() {
        return number_enemy_killed;
    }

    public static void setNumber_enemy_killed(int number_enemy_killed) {
        Result_board.number_enemy_killed = number_enemy_killed;
    }

    public static int getScores() {
        return scores;
    }

    public static void setScores(int scores) {
        Result_board.scores = scores;
    }

    public static int getHealth() {
        return Health;
    }

    public static void setHealth(int health) {
        Health = health;
    }

    public static boolean getdisplay_menu_winner() {
        return display_menu_winner;
    }
    public static void setdisplay_menu_winner(Boolean status) {
        display_menu_winner = status;
    }

    public static int getSeconds_Duration_Game() {
        return Seconds_Duration_Game;
    }

    public static void setSeconds_Duration_Game(int seconds_Duration_Game) {
        Seconds_Duration_Game = seconds_Duration_Game;
    }

    private static ArrayList<Result_board> Result_boards = new ArrayList<Result_board>();

    public static ArrayList<Result_board> getResult_boards() {
        return Result_boards;
    }

    public static void setResult_boards(ArrayList<Result_board> result_boards) {
        Result_boards = result_boards;
    }

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
