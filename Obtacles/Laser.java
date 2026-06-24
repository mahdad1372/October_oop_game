package Obtacles;

import Interfaces.obstecles_measurement;

import java.util.ArrayList;

public class Laser extends Obstacles implements obstecles_measurement {
  public Laser(int coordinate_x, int coordinate_y, int length, int height) {
    super(coordinate_x, coordinate_y, length, height);
  }
  private static final int[][] laser = {
          {725,250,200,10},
          {430,290,10,50},
  };
  public static int[][] getlaser_coordinates(){
    return laser;
  }
  private static ArrayList<Obstacles> Laser = new ArrayList<>();

  public static ArrayList<Obstacles> getLaser() {
    return Laser;
  }

  @Override
  public int health_decrease(int health) {
    return health -10;
  }
}
