package Bullet;

import javax.swing.*;
import java.awt.*;

public class Missile extends Bullet {
    public Missile(int missile_coordinate_x, int missile_coordinate_y ,Image missle_img, int missile_Final_position_x , int missile_Final_position_y) {
        super(missile_coordinate_x, missile_coordinate_y,missle_img);
        this.Final_position_missile_x =missile_Final_position_x;
        this.Final_position_missile_y =missile_Final_position_y;
    }

    private int Final_position_missile_x;
    private int Final_position_missile_y;

    public int getPositionFinal_y() {
        return this.Final_position_missile_y;
    }
    public int getPositionFinal_x() {
        return this.Final_position_missile_x;
    }
    private static final Image Missile_img = new ImageIcon("Assets/missile.png").getImage();

    public static Image getMissile_img() {
        return Missile_img;
    }

    @Override
    public void shootingDirection(String direction) {
        if (direction == "up"){
            if (getPosition_coordinate_x() != this.Final_position_missile_x){
                int bullet_position_x = super.getPosition_coordinate_x();
                super.setPosition_coordinate_x(bullet_position_x+=5);
            }
            if (getPosition_coordinate_y() != this.Final_position_missile_y){
                int bullet_position_y = super.getPosition_coordinate_y();
                super.setPosition_coordinate_y(bullet_position_y-=5);
            }
        }

    }
}
