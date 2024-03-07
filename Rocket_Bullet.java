import java.awt.*;

public class Rocket_Bullet extends Bullet{
    public Rocket_Bullet(int bullet_x, int bullet_y , Image rocket_image) {
        super(bullet_x, bullet_y);
        this.Rocket_image = rocket_image;
    }
    private Image Rocket_image;


    public Image getRocket_image(){
        return  this.Rocket_image;
    }
    public void setRocket_image(Image rocket_img){
        this.Rocket_image = rocket_img;
    }
}
