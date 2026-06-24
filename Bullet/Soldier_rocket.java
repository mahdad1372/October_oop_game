package Bullet;

import javax.swing.*;
import java.awt.*;

public class Soldier_rocket extends Rocket_Bullet {
    public Soldier_rocket(int rocket_coordinate_x, int rocket_coordinate_y, Image tank_rocket, int Final_position_x, int Final_position_y) {
        super(rocket_coordinate_x, rocket_coordinate_y,tank_rocket,Final_position_x,Final_position_y);
    }
    private static final Image Soldier_rocket = new ImageIcon("Assets/Soldier_rocket.png").getImage();

    public static Image getSoldier_rocket() {
        return Soldier_rocket;
    }

}
