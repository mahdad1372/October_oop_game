public class Missile extends Bullet{
    public Missile(int bullet_x, int bullet_y , int Fx , int Fy) {
        super(bullet_x, bullet_y);
        this.Final_position_x =Fx;
        this.Final_position_y =Fy;
    }    private int Final_position_x;
    private int Final_position_y;

    public int getPositionFinal_y() {
        return this.Final_position_y;
    }
    public int getPositionFinal_x() {
        return this.Final_position_x;
    }
    public void Missile_Shooting(){
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
