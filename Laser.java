public class Laser extends obstacles {
  public Laser(int height , int length , int coordinate_x, int coordinate_y ){
    this.length = length;
    this.height = height;
    this.coordinate_x = coordinate_x;
    this.coordinate_y = coordinate_y;
  }
  private int length;
  private int height;
  private int coordinate_x;
  private int coordinate_y;


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
