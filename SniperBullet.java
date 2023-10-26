public class SniperBullet extends Bullet{
    private int Sniper_number;
    private int FinalPosition;
    private  String Direction;
    public SniperBullet(int bullet_x, int bullet_y, int Sn ,int FinalPosition , String direction) {

        super(bullet_x, bullet_y);
        this.Sniper_number = Sn;
        this.FinalPosition = FinalPosition;
        this.Direction = direction;
    }

    @Override
    public int getPosition_x() {
        return super.getPosition_x();
    }

    @Override
    public int getPosition_y() {
        return super.getPosition_y();
    }
    public int getSniper_number() {
        return this.Sniper_number;
    }
    public void shootingBullet(){

        int bullet_position = super.getPosition_y();
        super.setPosition_y(bullet_position+=5);
    }

}
