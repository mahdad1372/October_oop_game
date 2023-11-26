public class Mines extends obstacles {
    private int radius;
    public Mines(int radius){
        this.radius = radius;
    }

    public int get_radius(){
        return this.radius;
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
