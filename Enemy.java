import java.awt.*;

public abstract class Enemy  {
    public Enemy (Image img, int x, int y,int w , int h){
        this.enemy_x = x;
        this.enemy_y = y;
        this.enemy = img;
        this.width = w;
        this.height = h;

    }
    private int enemy_x;
    private int  enemy_y;
    private Image enemy;
    private int  width ;
    private int  height ;

    public int getPosition_enemy_x() {
        return enemy_x;
    }

    public int getPosition_enemy_y() {
        return enemy_y;
    }
    public void setPosition_enemy_x(int x) {
        this.enemy_x = x;
    }

    public void setPosition_enemy_y(int y) {

        this.enemy_y = y;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Image getImage_enemy() {
        return enemy;
    }


}
