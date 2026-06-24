package Player;

import javax.swing.*;
import java.awt.*;

public class Player {
    public Player(int player_coordinate_x , int player_coordinate_y, Image player_image){
        this.position_x =player_coordinate_x;
        this.position_y = player_coordinate_y;
        this.Image_player = player_image;
    }
    private static Player player;
    private static String direction_player = "left";
    private int position_x;
    private int position_y;
    private Image Image_player;
    private static final Image Player_icon = new ImageIcon("Assets/soldier.png").getImage();
    private static final Image Player_L = new ImageIcon("Assets/soldier_l.png").getImage();
    private static final Image Player_D = new ImageIcon("Assets/soldier_d.png").getImage();
    private static final Image Player_U = new ImageIcon("Assets/soldier_u.png").getImage();

    public static Image getPlayer_D() {
        return Player_D;
    }

    public static Image getPlayer_L() {
        return Player_L;
    }

    public static Image getPlayer_icon() {
        return Player_icon;
    }

    public static Image getPlayer_U() {
        return Player_U;
    }

    public static String getDirection_player() {
        return direction_player;
    }

    public static void setDirection_player(String direction_player) {
        Player.direction_player = direction_player;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Player.player = player;
    }

    public int getPosition_x(){
        return this.position_x;
    }
    public int getPosition_y(){
        return this.position_y;
    }
    public Image getPlayerIcon(){
        return  this.Image_player;
    }

    public void setPlayerIcon(Image img){
        this.Image_player = img;
    }
    public void setPosition_x(int x){
        this.position_x = x;
    }
    public void setPosition_y(int y){
        this.position_y = y;
    }
    public void player_move_left(){
        this.position_x-=5;
    }
    public void player_move_right(){
        this.position_x+=5;
    }
    public void player_move_up(){
        this.position_y-=5;
    }
    public void player_move_down(){
        this.position_y+=5;
    }
}
