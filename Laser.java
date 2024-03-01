public class Laser extends obstacles {
  private int length;
  private int height;
  private int coordinate_x;
  private int coordinate_y;
  public Laser(int h , int l , int x, int y ){
    this.length = l;
    this.height = h;
    this.coordinate_x = x;
    this.coordinate_y = y;
  }

  public int get_coordinate_x(){
    return this.coordinate_x;
  }
  public int get_coordinate_y(){
    return this.coordinate_y;
  }
  public int get_length(){
    return this.length;
  }
  public int get_height(){
    return this.height;
  }
  @Override
  public int calculate_area() {
    return length *height;
  }

  @Override
  public int health_decrease(int health) {
    return health -10;
  }
}
