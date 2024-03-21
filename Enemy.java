import java.awt.*;

public abstract class Enemy  {
    public Enemy (Image enemy_image, int coordinate_x, int coordinate_y,int width , int height){
        this.enemy_coordinate_x = coordinate_x;
        this.enemy_coordinate_y = coordinate_y;
        this.enemy_image = enemy_image;
        this.width = width;
        this.height = height;

    }
    private int enemy_coordinate_x;
    private int  enemy_coordinate_y;
    private Image enemy_image;
    private int  width ;
    private int  height ;

    public int getPosition_enemy_x() {
        return enemy_coordinate_x;
    }

    public int getPosition_enemy_y() {
        return enemy_coordinate_y;
    }
    public void setPosition_enemy_x(int coordinate_x) {
        this.enemy_coordinate_x = coordinate_x;
    }

    public void setPosition_enemy_y(int coordinate_y) {

        this.enemy_coordinate_y = coordinate_y;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Image getImage_enemy() {
        return enemy_image;
    }

    abstract int killing_enemy_score();

}
