import java.util.Timer;
import java.util.TimerTask;

public class Bullet {
    private int Bullet_x;
    private int Bullet_y;


    public  Bullet(int bullet_x, int bullet_y){
        this.Bullet_x = bullet_x;
        this.Bullet_y = bullet_y;
    }

    public int getPosition_x(){
        return Bullet_x;
    }
    public int getPosition_y(){
        return Bullet_y;
    }
    public void setPosition_x(int x){
        this.Bullet_x = x;
    }
    public void setPosition_y(int y){
        this.Bullet_y = y;
    }
    public int getPosition_x_on_time(String direction) {
        if (direction == "right"){
            return  Bullet_x++;
        } else if (direction == "left") {
            return  Bullet_x--;
        }else if (direction == "up") {
            return  Bullet_y--;
        }else if (direction == "down") {
            return  Bullet_y++;
        }else {
            return Bullet_x++;
        }
    }
}
