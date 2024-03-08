import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SniperEnemy extends Army_enemy {
    public SniperEnemy(Image img, int x, int y, int w, int h, String direction, int final_position, int Sn) {
        super(img, x, y, w, h, direction, final_position);
        this.Sniper_number = Sn;
    }
    private ImageIcon icon = new ImageIcon("bullet.png");
    private int Sniper_number;


    private ArrayList<SniperBullet> SniperBullet_List = new ArrayList<SniperBullet>();

    public void add_bullet(int x , int y , int Sn ,int FinalPosition , String direction){
        SniperBullet_List.add(new SniperBullet(x,y,Sn,FinalPosition,direction));
    }
    public ArrayList<SniperBullet> return_bullet(){
        return this.SniperBullet_List;
    }

    public int getSniper_number() {
        return this.Sniper_number;
    }
}
