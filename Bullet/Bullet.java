package Bullet;

import java.awt.*;

public abstract class Bullet implements bullet_movement{

    private int position_coordinate_x;
    private int position_coordinate_y;
    private Image bulletimg;
    public Bullet(int x, int y, Image bullet_img) {
        this.position_coordinate_x = x;
        this.position_coordinate_y = y;
        this.bulletimg = bullet_img;
    }

    public int getPosition_coordinate_x() { return position_coordinate_x; }
    public int getPosition_coordinate_y() { return position_coordinate_y; }

    public Image getBulletimg() {
        return bulletimg;
    }

    public void setPosition_coordinate_x(int position_coordinate_x) {
        this.position_coordinate_x = position_coordinate_x;
    }
    public void setPosition_coordinate_y(int position_coordinate_y) {
        this.position_coordinate_y = position_coordinate_y;
    }

    public void setBulletimg(Image bulletimg) {
        this.bulletimg = bulletimg;
    }
}