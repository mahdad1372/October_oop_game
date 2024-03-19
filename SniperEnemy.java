import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class SniperEnemy extends Army_enemy {
    public SniperEnemy(Image img, int x, int y, int w, int h,SniperBullet Sniper_bullet) {
        super(img, x, y, w, h);
        this.Sniper_bullet = Sniper_bullet;
    }
    private ImageIcon icon = new ImageIcon("bullet.png");
    private SniperBullet Sniper_bullet;
    private ArrayList<SniperBullet> SniperBullet_List = new ArrayList<SniperBullet>();
    public ArrayList<SniperBullet> get_Sniper_bullet(){
        return this.SniperBullet_List;
    }
    public void Shooting_Sniper_Bullet(){
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SniperBullet_List.add(new SniperBullet(Sniper_bullet.getPosition_x(),Sniper_bullet.getPosition_y(),
                        Sniper_bullet.getSniper_bullet_image(),Sniper_bullet.getFinalPosition()));
            }
        };
        timer.schedule(task, 0, 4000);
    }
    public void Editing_Sniper_Bullet_List(){
        for (int i=0;i< SniperBullet_List.size();i++){
            if (SniperBullet_List.get(i).getPosition_y() ==SniperBullet_List.get(i).getFinalPosition()){
                SniperBullet_List.remove(i);
            }
        }
    }

}
