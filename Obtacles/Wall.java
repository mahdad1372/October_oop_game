package Obtacles;

import Interfaces.obstecles_measurement;

import java.util.ArrayList;

public class Wall extends Obstacles implements obstecles_measurement {
    public Wall(int coordinate_x, int coordinate_y, int length, int height) {
        super(coordinate_x, coordinate_y, length, height);
    }
    private static final int[][] wall = {
            {130,0,20,140},
            {130,200,20,140},
            {150,40,120,20},
            {270,40,20,120},
            {270,100,180,20},
            {270,240,20,130},
            {270,345,180,20},
            {430,120,20,120},
            {530,190,20,170},
            {530,0,20,120},
            {550,300,100,20},
            {630,80,20,220},
            {630,20,300,20},
            {710,20,20,160},
            {710,270,20,100},
            {710,180,230,20}
    };
    public static int[][] getWall(){
        return wall;
    }
    private static ArrayList<Obstacles> Walls = new ArrayList<Obstacles>();

    public static void setWalls(ArrayList<Obstacles> walls) {
        Walls = walls;
    }

    public static ArrayList<Obstacles> getWalls() {
        return Walls;
    }

    @Override
    public int health_decrease(int health) {
        return 0;
    }
}
