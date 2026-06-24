package Bullet;

import javax.swing.*;
import java.awt.*;

public class Tank_rocket extends Rocket_Bullet{
    public Tank_rocket(int rocket_x, int rocket_y, Image tank_rocket, int Final_position_x, int Final_position_y) {
        super(rocket_x, rocket_y,tank_rocket,Final_position_x,Final_position_y);
    }
    public static final Image Tank_rocket = new ImageIcon("Assets/tank_rocket.png").getImage();
    private static final Object[][] Tank_rocket_coordinated = {
            {580, 230, Tank_rocket,580,50}
    };
    public static Object[][] getTank_rocket_coordinated(){
        return Tank_rocket_coordinated;
    }
}
