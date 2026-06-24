package Player;
import Bullet.*;
import Enemy.thief;
import Results.Result_board;
import Utils.CollisionUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public final class Playercreation {
    public static Player creating_player(){
        Player player = new Player(0,0, Player.getPlayer_icon());
        return player;
    }
    public static Player PlayerDrawing(Graphics g,Player player){
        g.drawImage(player.getPlayerIcon(), player.getPosition_x(), player.getPosition_y(), null);
        return player;
    }
    public static void player_movement(int keyCode){
        if (keyCode == KeyEvent.VK_SPACE){
            int bullet_position_x = Player.getPlayer().getPosition_x();
            int bullet_position_y = Player.getPlayer().getPosition_y();
            Bullet bullet = new player_bullet(bullet_position_x, bullet_position_y,SniperBullet.getSniper_Bullet());
            Bullet.getBullet_position().add(bullet);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Player.setDirection_player("left");
            Player.getPlayer().player_move_left();
            Player.getPlayer().setPlayerIcon(Player.getPlayer_L());
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            Player.setDirection_player("right");
            Player.getPlayer().player_move_right();
            Player.getPlayer().setPlayerIcon(Player.getPlayer_icon());

        } else if (keyCode == KeyEvent.VK_UP) {
            Player.setDirection_player("up");
            Player.getPlayer().player_move_up();
            Player.getPlayer().setPlayerIcon(Player.getPlayer_U());
        } else if (keyCode == KeyEvent.VK_DOWN) {
            Player.setDirection_player("down");
            Player.getPlayer().player_move_down();
            Player.getPlayer().setPlayerIcon(Player.getPlayer_D());
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
                        Result_board.setScores(Result_board.getScores() + th.killing_enemy_score());
                        Result_board.setNumber_enemy_killed(Result_board.getNumber_enemy_killed() + 1);

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
