import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SniperEnemy extends Enemy {
    private JLabel bullet_label;
    private ImageIcon icon = new ImageIcon("bullet.png");
    private int Sniper_number;
    public SniperEnemy(Image img, int x, int y, int w, int h, String direction, int final_position, int Sn) {
        super(img, x, y, w, h, direction, final_position);
        this.Sniper_number = Sn;
    }

    private ArrayList<SniperBullet> SniperBullet_List = new ArrayList<SniperBullet>();

    public void add_bullet(int x , int y , int Sn ,int FinalPosition , String direction){
        SniperBullet_List.add(new SniperBullet(x,y,Sn,FinalPosition,direction));
    }
    public ArrayList<SniperBullet> return_bullet(){
        return this.SniperBullet_List;
    }

    public void create_sniper_bullet(){
        this.bullet_label = new JLabel();
        this.bullet_label.setBounds(super.getPosition_enemy_x(),
                super.getPosition_enemy_y(),super.getWidth()
                ,super.getHeight());
        this.bullet_label.setIcon(icon);

    }

    @Override
    public void set_Position_enemy_y(int y) {
        super.set_Position_enemy_y(y);
    }

    @Override
    public void set_Position_enemy_x(int x) {
        super.set_Position_enemy_x(x);
    }

    @Override
    public int getPosition_enemy_y() {
        return super.getPosition_enemy_y();
    }
    @Override
    public Image getImage_enemy() {
        return super.getImage_enemy();
    }
    public int getSniper_number() {
        return this.Sniper_number;
    }

    public  JLabel sniper_bullet(){
        return this.bullet_label;
    }
}
