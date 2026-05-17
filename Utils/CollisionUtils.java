package Utils;

import Bullet.Bullet;
import Enemy.Enemy;
import Obtacles.Obstacles;
import Player.Player;

import java.awt.*;

public final class CollisionUtils {

    public static boolean intersects(Bullet bullet, Enemy enemy) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10, 10);
        Rectangle enemy_Rect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(enemy_Rect);
    }
    public static boolean bulletIntersectsObstacle(Bullet bullet, Obstacles obstacle) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(obstacle.getCoordinate_x(), obstacle.getCoordinate_y(),
                obstacle.getLength(),obstacle.getHeight());

        return bulletRect.intersects(enemyRect);
    }
    public static boolean playerIntersectObstacle(Player player, Obstacles obstacles) {
        Rectangle playerRect = new Rectangle(player.getPosition_x(), player.getPosition_y(),
                30, 30);
        Rectangle wallRect = new Rectangle(obstacles.getCoordinate_x(), obstacles.getCoordinate_y(),
                obstacles.getLength(),obstacles.getHeight());

        return playerRect.intersects(wallRect);
    }
    public static boolean playerIntersectBullet(Player player,Bullet bullet) {
        Rectangle playerRect = new Rectangle(player.getPosition_x(), player.getPosition_y(),
                30, 30);
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10,30);

        return playerRect.intersects(bulletRect);
    }
    public static boolean playerIntersectEnemy(Player player,Enemy enemy) {
        Rectangle playerRect = new Rectangle(player.getPosition_x(), player.getPosition_y(),
                30, 30);
        Rectangle enemyRect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                enemy.getWidth(),enemy.getHeight());

        return playerRect.intersects(enemyRect);
    }
}
