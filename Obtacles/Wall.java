package Obtacles;

public class Wall extends Obstacles {
    public Wall(int coordinate_x, int coordinate_y, int length, int height) {
        super(coordinate_x, coordinate_y, length, height);
    }

    @Override
    public int health_decrease(int health) {
        return 0;
    }
}
