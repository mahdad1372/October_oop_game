package Player;
import Assets.Assets;
import Bullet.*;
import Enemy.thief;
import Player.Player;
import Utils.CollisionUtils;
import Panel.MyPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public final class Playercreation {
    public static Player creating_player(){
        Player player = new Player(0,0, Assets.Player_icon);
        return player;
    }
    public static Player PlayerDrawing(Graphics g,Player player){
        g.drawImage(player.getPlayerIcon(), player.getPosition_x(), player.getPosition_y(), null);
        return player;
    }
    public static void player_movement(int keyCode){
        if (keyCode == KeyEvent.VK_SPACE){
            int bullet_position_x = MyPanel.player.getPosition_x();
            int bullet_position_y = MyPanel.player.getPosition_y();
            Bullet bullet = new player_bullet(bullet_position_x, bullet_position_y, Assets.Sniper_Bullet);
            MyPanel.bullet_position.add(bullet);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            MyPanel.direction_player = "left";
            MyPanel.player.player_move_left();
            MyPanel.player.setPlayerIcon(Assets.Player_L);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            MyPanel.direction_player = "right";
            MyPanel.player.player_move_right();
            MyPanel.player.setPlayerIcon(Assets.Player_icon);

        } else if (keyCode == KeyEvent.VK_UP) {
            MyPanel.direction_player = "up";
            MyPanel.player.player_move_up();
            MyPanel.player.setPlayerIcon(Assets.Player_U);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            MyPanel.direction_player = "down";
            MyPanel.player.player_move_down();
            MyPanel.player.setPlayerIcon(Assets.Player_D);
        }
    }
    public static ArrayList<Bullet> BulletPlayerDrawing(Graphics g , ArrayList<Bullet> bullet_position ,String direction_player ,ArrayList<thief> thief_list ) {
        try {
            Graphics2D g2d = (Graphics2D) g;
            for (Bullet b : bullet_position) {
                b.shootingDirection(direction_player);
                g2d.setColor(Color.BLUE);
                g2d.fillOval(
                        b.getPosition_coordinate_x(),
                        b.getPosition_coordinate_y(),
                        10, 10
                );
            }
            bullet_position.removeIf(b ->
                    b.getPosition_coordinate_x() < 0 ||
                            b.getPosition_coordinate_x() > 950 ||
                            b.getPosition_coordinate_y() < 0 ||
                            b.getPosition_coordinate_y() > 400
            );
            Iterator<Bullet> bulletIt = bullet_position.iterator();
            while (bulletIt.hasNext()) {
                Bullet bullet = bulletIt.next();
                Iterator<thief> thiefIt = thief_list.iterator();
                while (thiefIt.hasNext()) {
                    thief th = thiefIt.next();
                    if (CollisionUtils.intersects(bullet, th)) {
                        thiefIt.remove();
                        bulletIt.remove();
                        MyPanel.scores += th.killing_enemy_score();
                        MyPanel.number_enemy_killed++;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bullet_position;
    }
}
