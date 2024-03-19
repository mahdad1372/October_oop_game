public class Bullet {
    public  Bullet(int bullet_coordinate_x, int bullet_coordinate_y){
        this.bullet_coordinate_x = bullet_coordinate_x;
        this.bullet_coordinate_y = bullet_coordinate_y;
    }
    private int bullet_coordinate_x;
    private int bullet_coordinate_y;
    public int getPosition_coordinate_x(){
        return bullet_coordinate_x;
    }
    public int getPosition_coordinate_y(){
        return bullet_coordinate_y;
    }
    public void setPosition_coordinate_x(int x){
        this.bullet_coordinate_x = x;
    }
    public void setPosition_coordinate_y(int y){
        this.bullet_coordinate_y = y;
    }
    public int getPosition_coordinateBullet_on_time(String direction_bullet) {
        String direction = direction_bullet;
        switch (direction) {
            case "right":
                return  bullet_coordinate_x++;
            case "left":
                return  bullet_coordinate_x--;
            case "up":
                return  bullet_coordinate_y--;
            case "down":
                return  bullet_coordinate_y++;
            default:
                throw new IllegalArgumentException("Invalid operation choice.");
        }

    }

}
