public class SniperBullet extends Bullet{
    public SniperBullet(int bullet_x, int bullet_y) {
        super(bullet_x, bullet_y);
    }

    @Override
    public int getPosition_x() {
        return super.getPosition_x();
    }

    @Override
    public int getPosition_y() {
        return super.getPosition_y();
    }
    public void shootingBullet(){
        int bullet_position = super.getPosition_y();
        super.setPosition_y(bullet_position+=5);
    }
}
