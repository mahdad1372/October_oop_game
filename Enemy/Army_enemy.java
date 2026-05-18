package Enemy;

import Bullet.Bullet;
import Interfaces.Army_enemy_details;

import java.awt.*;
import java.util.ArrayList;

public class Army_enemy extends Enemy implements Army_enemy_details {
    public Army_enemy(Image enemy_img, int coordinate_x, int coordinate_y, int width , int height , Bullet bullet){
        super(enemy_img, coordinate_x, coordinate_y, width, height);
        this.bullet = bullet;
    }
    private Bullet bullet;
    public void Shooting_Rocket(){

    }

    @Override
    public ArrayList<? extends Bullet> getRocket() {
        return null;
    }

    @Override
    public void Editing_Rocket_List() {

    }

    @Override
    public int killing_enemy_score() {
        return 5;
    }
}
