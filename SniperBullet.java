import java.awt.*;

public class SniperBullet extends Bullet{
    public SniperBullet(int SniperBullet_x, int SniperBullet_y,Image Sniper_bullet_image ,int FinalPosition) {

        super(SniperBullet_x, SniperBullet_y);
        this.FinalPosition = FinalPosition;
        this.Sniper_bullet_image = Sniper_bullet_image;
    }
    private int FinalPosition;
    private Image Sniper_bullet_image;

    public Image getSniper_bullet_image(){
        return  this.Sniper_bullet_image;
    }
    public int getFinalPosition(){
        return  this.FinalPosition;
    }
    public void shootingBullet(){
        int bullet_position = super.getPosition_coordinate_y();
        super.setPosition_coordinate_y(bullet_position+=5);
    }

}
