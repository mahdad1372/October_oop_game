public class SniperBullet extends Bullet{
    public SniperBullet(int bullet_x, int bullet_y, int Sn ,int FinalPosition , String direction) {

        super(bullet_x, bullet_y);
        this.Sniper_number = Sn;
        this.FinalPosition = FinalPosition;
        this.Direction = direction;
    }
    private int Sniper_number;
    private int FinalPosition;
    private  String Direction;



    public void shootingBullet(){

        int bullet_position = super.getPosition_y();
        super.setPosition_y(bullet_position+=5);
    }

}
