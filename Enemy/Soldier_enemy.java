package Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;
import Bullet.Soldier_rocket;

import javax.swing.*;

public class Soldier_enemy extends Army_enemy {
    public Soldier_enemy(Image img, int position_x, int position_y, int w , int h,
                          Soldier_rocket rocket){
        super(img, position_x, position_y, w, h,rocket);
        this.Soldier_Rocket = rocket;
    }
    private Soldier_rocket Soldier_Rocket;


    private ArrayList<Soldier_rocket> Soldier_rocket_list = new ArrayList<>();
    private static Army_enemy soldier_enemy;
    public static Army_enemy getSoldier_enemy() {
        return soldier_enemy;
    }

    public static void setSoldier_enemy(Army_enemy soldier_enemy) {
        Soldier_enemy.soldier_enemy = soldier_enemy;
    }

    @Override
    public void Shooting_Rocket() {
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Soldier_rocket_list.add(new Soldier_rocket(Soldier_Rocket.getPosition_coordinate_x(),Soldier_Rocket.getPosition_coordinate_y(),
                        Soldier_Rocket.getRocket_image(),Soldier_Rocket.getPositionFinal_x(),Soldier_Rocket.getPositionFinal_y()));
            }
        };
        timer.schedule(task, 0, 8000);
    }

    @Override
    public ArrayList<Soldier_rocket> getRocket() {
        return this.Soldier_rocket_list;
    }
    @Override
    public void Editing_Rocket_List(){
        for (int i=0;i< Soldier_rocket_list.size();i++){
            if (Soldier_rocket_list.get(i).getPosition_coordinate_x() ==Soldier_rocket_list.get(i).getPositionFinal_x() &&
                    Soldier_rocket_list.get(i).getPosition_coordinate_y() == Soldier_rocket_list.get(i).getPositionFinal_y()){
                Soldier_rocket_list.remove(i);
            }
        }
    }
}
