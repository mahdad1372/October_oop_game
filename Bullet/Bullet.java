package Bullet;

import Interfaces.bullet_movement;

import java.awt.*;
import java.util.ArrayList;

public abstract class Bullet implements bullet_movement {

    private int position_coordinate_x;
    private int position_coordinate_y;
    private Image bulletimg;
    private static ArrayList<Bullet> bullet_position = new ArrayList<>();
    public Bullet(int x, int y, Image bullet_img) {
        this.position_coordinate_x = x;
        this.position_coordinate_y = y;
        this.bulletimg = bullet_img;
    }

    public int getPosition_coordinate_x() { return position_coordinate_x; }
    public int getPosition_coordinate_y() { return position_coordinate_y; }

    public static ArrayList<Bullet> getBullet_position() {
        return bullet_position;
    }

    public static void setBullet_position(ArrayList<Bullet> bullet_position) {
        Bullet.bullet_position = bullet_position;
    }

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