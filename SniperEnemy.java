import javax.swing.*;
import java.awt.*;

public class SniperEnemy extends Enemy {
    JLabel bullet_label;
    ImageIcon icon = new ImageIcon("bullet.png");
    public SniperEnemy(Image img, int x, int y, int w, int h, String direction, int final_position) {
        super(img, x, y, w, h, direction, final_position);
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
    public int getPosition_enemy_y() {
        return super.getPosition_enemy_y();
    }
    @Override
    public Image getImage_enemy() {
        return super.getImage_enemy();
    }

    public  JLabel sniper_bullet(){
        return this.bullet_label;
    }
}
