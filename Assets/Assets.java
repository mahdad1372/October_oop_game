package Assets;

import javax.swing.*;
import java.awt.*;

public final class Assets {
     public static Image Sniper_Bullet = new ImageIcon("Assets/sniper_bullet.png").getImage();
     public static Image Mine = new ImageIcon("Assets/mine.png").getImage();
     public static Image Player_icon = new ImageIcon("Assets/soldier.png").getImage();
     public static Image Player_L = new ImageIcon("Assets/soldier_l.png").getImage();
     public static Image Player_D = new ImageIcon("Assets/soldier_d.png").getImage();
     public static Image Player_U = new ImageIcon("Assets/soldier_u.png").getImage();
     public static Image Sniper = new ImageIcon("Assets/Sniper.png").getImage();
     public static Image Launcher = new ImageIcon("Assets/launcher.png").getImage();
     public static Image Missile_img = new ImageIcon("Assets/missile.png").getImage();
     public static Image thief = new ImageIcon("Assets/thief.png").getImage();
     public static Image Tank_rocket = new ImageIcon("Assets/tank_rocket.png").getImage();
     public static Image Soldier_rocket = new ImageIcon("Assets/Soldier_rocket.png").getImage();
     public static Image Tank = new ImageIcon("Assets/tank.png").getImage();
     public static Image Soldier_enemy = new ImageIcon("Assets/Soldier_enemy.png").getImage();
    public static Object[][] thief_enemy_coordinates = {
            {200,70,30,30,"Y",320},
            {390,165,30,30,"X",120},
            {350,310,30,30,"Y",120},
            {595,140,30,30,"X",450},
            {580,10,30,30,"Y",180},
            {665,50,30,30,"Y",210}
    };
    public static int[][] wall = {
            {130,0,20,140},
            {130,200,20,140},
            {150,40,120,20},
            {270,40,20,120},
            {270,100,180,20},
            {270,240,20,130},
            {270,345,180,20},
            {430,120,20,120},
            {530,190,20,170},
            {530,0,20,120},
            {550,300,100,20},
            {630,80,20,220},
            {630,20,300,20},
            {710,20,20,160},
            {710,270,20,100},
            {710,180,230,20}
    };
    public static int[][] mines = {
            {460,300,40,40},
    };
    public static int[][] laser = {
            {725,250,200,10},
    };
}
