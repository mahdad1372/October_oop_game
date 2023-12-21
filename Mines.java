public class Mines extends obstacles {
    private int radius;
    private int coordinate_x;
    private int coordinate_y;

    public Mines(int radius,int x , int y){
        this.radius = radius;
        this.coordinate_x = x;
        this.coordinate_y = y;
    }

    public int get_radius(){
        return this.radius;
    }
    public int get_coordinate_x(){
        return this.coordinate_x;
    }
    public int get_coordinate_y(){
        return this.coordinate_y;
    }

    @Override
    public int calculate_area() {
        return  radius* 2;
    }

    @Override
    public int health_decrease(int health) {
        return health - 20;
    }
}
