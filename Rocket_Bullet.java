import java.awt.*;

public class Rocket_Bullet extends Bullet{
    public Rocket_Bullet(int bullet_x, int bullet_y , Image rocket_image, int Final_position_x, int Final_position_y) {
        super(bullet_x, bullet_y);
        this.Rocket_image = rocket_image;
        this.Final_position_x = Final_position_x;
        this.Final_position_y = Final_position_y;
    }
    private Image Rocket_image;
    private int Final_position_x;
    private int Final_position_y;


    public Image getRocket_image(){
        return  this.Rocket_image;
    }
    public void setRocket_image(Image rocket_img){
        this.Rocket_image = rocket_img;
    }
    public int getPositionFinal_y() {
        return this.Final_position_y;
    }
    public int getPositionFinal_x() {
        return this.Final_position_x;
    }
    public void Rocket_Shooting(){
        if (getPosition_x() != this.Final_position_x){
            int bullet_position_x = super.getPosition_x();
            super.setPosition_x(bullet_position_x+=5);
        }
        if (getPosition_y() != this.Final_position_y){
            int bullet_position_y = super.getPosition_y();
            super.setPosition_y(bullet_position_y-=5);
        }

    }
}
