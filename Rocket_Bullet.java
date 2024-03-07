import java.awt.*;

public class Rocket_Bullet extends Bullet{
    private Image Rocket_image;
    public Rocket_Bullet(int bullet_x, int bullet_y , Image rocket_image) {
        super(bullet_x, bullet_y);
        this.Rocket_image = rocket_image;
    }

    @Override
    public int getPosition_y() {
        return super.getPosition_y();
    }

    @Override
    public int getPosition_x() {
        return super.getPosition_x();
    }

    @Override
    public void setPosition_x(int x) {
        super.setPosition_x(x);
    }

    @Override
    public void setPosition_y(int y) {
        super.setPosition_y(y);
    }
    public Image getRocket_image(){
        return  this.Rocket_image;
    }
    public void setRocket_image(Image rocket_img){
        this.Rocket_image = rocket_img;
    }
}
