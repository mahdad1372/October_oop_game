public class Bullet {

    private int position_coordinate_x;
    private int position_coordinate_y;

    public Bullet(int x, int y) {
        this.position_coordinate_x = x;
        this.position_coordinate_y = y;
    }

    public int getPosition_coordinate_x() { return position_coordinate_x; }
    public int getPosition_coordinate_y() { return position_coordinate_y; }

    public void setPosition_coordinate_x(int position_coordinate_x) {
        this.position_coordinate_x = position_coordinate_x;
    }
    public void setPosition_coordinate_y(int position_coordinate_y) {
        this.position_coordinate_y = position_coordinate_y;
    }

    public void updatePosition(String direction) {
        int speed = 20;

        switch (direction) {
            case "left":  position_coordinate_x -= speed; break;
            case "right": position_coordinate_x += speed; break;
            case "up":    position_coordinate_y -= speed; break;
            case "down":  position_coordinate_y += speed; break;
        }
    }
}