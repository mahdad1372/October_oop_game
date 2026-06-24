package Obtacles;

import Interfaces.obstecles_measurement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Mines extends Obstacles implements obstecles_measurement {
    public Mines(int coordinate_x, int coordinate_y, int length, int height) {
        super(coordinate_x, coordinate_y, length, height);
    }
    private final static Image Mine_icon = new ImageIcon("Assets/mine.png").getImage();
    private static final int[][] mines = {
            {460,300,40,40},
            {300,300,40,40},
    };
    private static ArrayList<Obstacles> Mine = new ArrayList<>();

    public static Image getMine_icon() {
        return Mine_icon;
    }

    public static ArrayList<Obstacles> getMine() {
        return Mine;
    }

    public static int[][] getMines_coordinates() {
        return mines;
    }

    public static void setMine(ArrayList<Obstacles> mine) {
        Mine = mine;
    }

    @Override
    public int health_decrease(int health) {
        return health -15;
    }
}
