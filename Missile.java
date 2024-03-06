public class Missile extends Bullet{
    private int Final_position_x;
    private int Final_position_y;
    @Override
    public int getPosition_x() {
        return super.getPosition_x();
    }

    @Override
    public int getPosition_y() {
        return super.getPosition_y();
    }
    public Missile(int bullet_x, int bullet_y , int Fx , int Fy) {
        super(bullet_x, bullet_y);
        this.Final_position_x =Fx;
        this.Final_position_y =Fy;
    }
    public int getPositionFinal_y() {
        return this.Final_position_y;
    }
    public int getPositionFinal_x() {
        return this.Final_position_x;
    }
    public void Missile_Shooting(){
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
