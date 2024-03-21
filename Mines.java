public class Mines extends obstacles {
    public Mines(int radius,int coordinate_x , int coordinate_y){
        this.radius = radius;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
    }
    private int radius;
    private int coordinate_x;
    private int coordinate_y;
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
        return health - 15;
    }
}
