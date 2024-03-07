import java.awt.*;

public class Tank_rocket extends Rocket_Bullet{

    public Tank_rocket(int rocket_x, int rocket_y, Image tank_rocket) {
        super(rocket_x, rocket_y,tank_rocket);
    }

    @Override
    public int getPosition_x() {
        return super.getPosition_x();
    }

    @Override
    public int getPosition_y() {
        return super.getPosition_y();
    }

    @Override
    public Image getRocket_image() {
        return super.getRocket_image();
    }

    @Override
    public void setPosition_x(int x) {
        super.setPosition_x(x);
    }

    @Override
    public void setPosition_y(int y) {
        super.setPosition_y(y);
    }

    @Override
    public void setRocket_image(Image rocket_img) {
        super.setRocket_image(rocket_img);
    }
}
