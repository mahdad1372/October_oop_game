public class Soldier {
    private Weapon weapon;
    private int coordinate_x;
    private int coordinate_y;

    public Soldier(Weapon weapon , int x , int y){
        this.weapon = weapon;
        this.coordinate_x = x;
        this.coordinate_y = y;
    }
    public void set_coordinate_x(int x){
        this.coordinate_x =x;
    }
    public void set_coordinate_y(int y){
        this.coordinate_y =y;
    }
    public int get_coordinate_x(){
        return coordinate_x;
    }
    public int get_coordinate_y(){
        return coordinate_y;
    }


}
