package Bullet;

import java.awt.*;

public class player_bullet extends Bullet {
    public player_bullet(int x, int y, Image bullt) {
        super(x, y,bullt);
    }

    @Override
    public void shootingDirection(String direction) {
        int speed = 20;

        switch (direction) {
            case "left":
                // Get the current value, subtract speed, then set it back
                int newX = getPosition_coordinate_x() - speed;
                setPosition_coordinate_x(newX);
                break;

            case "right":
                setPosition_coordinate_x(getPosition_coordinate_x() + speed);
                break;

            case "up":
                setPosition_coordinate_y(getPosition_coordinate_y() - speed);
                break;

            case "down":
                setPosition_coordinate_y(getPosition_coordinate_y() + speed);
                break;
        }
    }
}
