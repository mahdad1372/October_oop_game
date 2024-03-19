import java.awt.*;

public class Rocket_Bullet extends Bullet{
    public Rocket_Bullet(int Rocket_coordinate_x, int Rocket_coordinate_y , Image rocket_image, int Final_position_x, int Final_position_y) {
        super(Rocket_coordinate_x, Rocket_coordinate_y);
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

    public int getPositionFinal_y() {
        return this.Final_position_y;
    }
    public int getPositionFinal_x() {
        return this.Final_position_x;
    }
    public void Rocket_Shooting(){
        if (getPosition_coordinate_x() != this.Final_position_x){
            int bullet_position_x = super.getPosition_coordinate_x();
            super.setPosition_coordinate_x(bullet_position_x+=5);
        }
        if (getPosition_coordinate_y() != this.Final_position_y){
            int bullet_position_y = super.getPosition_coordinate_y();
            super.setPosition_coordinate_y(bullet_position_y-=5);
        }

    }
}
