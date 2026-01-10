public class Laser extends Obstacles {
  public Laser(int coordinate_x, int coordinate_y, int length, int height) {
    super(coordinate_x, coordinate_y, length, height);
  }


  @Override
  public int calculate_area() {
    return getLength() * getHeight();
  }

  @Override
  public int health_decrease(int health) {
    return health -10;
  }
}
