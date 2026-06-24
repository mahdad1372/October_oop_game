package Enemy;

import Bullet.Bullet;
import Interfaces.Army_enemy_details;

import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;
import Bullet.SniperBullet;

import javax.swing.*;

public class Army_enemy extends Enemy implements Army_enemy_details {
    public Army_enemy(Image enemy_img, int coordinate_x, int coordinate_y, int width , int height , Bullet bullet){
        super(enemy_img, coordinate_x, coordinate_y, width, height);
        this.bullet = bullet;
    }
    private Bullet bullet;
    private ArrayList<SniperBullet> SniperBullet_List = new ArrayList<SniperBullet>();
    private static Army_enemy army_enemy;
    private ArrayList<Integer> nums;
    private ArrayList<Double> newnums;
    private static final Image Armyenemy = new ImageIcon("Assets/military.png").getImage();
    private static final Image Soldier_enemy_icon = new ImageIcon("Assets/Soldier_enemy.png").getImage();
    public static Image getArmyenemy(){
        return Armyenemy;
    }
    public static Army_enemy getArmy_enemy() {
        return army_enemy;
    }

    public static void setArmy_enemy(Army_enemy army_enemy) {
        Army_enemy.army_enemy = army_enemy;
    }
    public static Image getSoldier_enemy_icon(){
        return Soldier_enemy_icon;
    }
    @Override
    public void Shooting_Rocket() {
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SniperBullet_List.add(new SniperBullet(bullet.getPosition_coordinate_x(),bullet.getPosition_coordinate_y(),
                        bullet.getBulletimg(),400));
            }
        };
        timer.schedule(task, 0, 1500);
    }


    @Override
    public ArrayList<? extends Bullet> getRocket() {
        return this.SniperBullet_List;
    }

    @Override
    public void Editing_Rocket_List() {
        for (int i=0;i< SniperBullet_List.size();i++){
            if (SniperBullet_List.get(i).getPosition_coordinate_y() ==400){
                SniperBullet_List.remove(i);
            }
        }
    }

    @Override
    public int killing_enemy_score() {
        return 5;
    }
}
