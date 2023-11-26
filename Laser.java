public class Laser extends obstacles {
  private int length;
  private int height;
  public Laser(int h , int l){
    this.length = l;
    this.height = h;
  }

  @Override
  public int calculate_area() {
    return length *2;
  }

  @Override
  public int health_decrease(int health) {
    return health -10;
  }
}
