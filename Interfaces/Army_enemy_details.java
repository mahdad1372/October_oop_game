package Interfaces;

import java.util.ArrayList;
import Bullet.Bullet;
public interface Army_enemy_details {

    void Shooting_Rocket();
    void Editing_Rocket_List();
    ArrayList<? extends Bullet> getRocket();
}
