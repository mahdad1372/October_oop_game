package Obtacles;

import Bullet.Bullet;
import Player.Player;
import Utils.intersects;
import Panel.MyPanel;
import java.awt.*;
import java.util.ArrayList;

public interface obstacle_creation {
    static void creating_obstacles(ArrayList<Obstacles> obstacles , int[][] coordinates, int damage) {
        for (int[] coordinate : coordinates) {
            obstacles.add(new Obstacles(coordinate[0], coordinate[1], coordinate[2], coordinate[3]) {
                @Override
                public int health_decrease(int Health) {
                    return Health -damage;
                }
            });
        }
    }

    static void Obstacle_drawing(ArrayList<Obstacles> obstacles, ArrayList<Bullet> bullets , Graphics g, Color color , Image image, Player player) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        try{
            if (image == null){
                for (int i = 0 ; i< obstacles.size();i++){
                    g2d.fillRect(obstacles.get(i).getCoordinate_x(),obstacles.get(i).getCoordinate_y(),
                            obstacles.get(i).getLength(),obstacles.get(i).getHeight());
                }
                if (obstacles.size()>0){
                    for (Obstacles obstacle:obstacles){
                        if (intersects.playerIntersectObstacle(player,obstacle)){
                            player.setPosition_x(obstacle.getCoordinate_x() - (obstacle.getLength()+30));
                            player.setPosition_y(obstacle.getCoordinate_y());
                            MyPanel.Health = obstacle.health_decrease(MyPanel.Health);
                        }
                    }
                }
                if (obstacles.size()>0){
                    for (int i=0; i<obstacles.size();i++){
                        for (int j=0; j<bullets.size();j++){
                            if (intersects.bulletIntersectsObstacle(bullets.get(j),obstacles.get(i))){
                                boolean found = bullets.contains(bullets.get(j));
                                if (found){
                                    bullets.remove(bullets.get(j));
                                }
                            }
                        }
                    }
                }
            }else {
                for (int i = 0 ; i< obstacles.size();i++){

                    g2d.fillOval(obstacles.get(i).getCoordinate_x(),obstacles.get(i).getCoordinate_y()
                            ,obstacles.get(i).getLength(),obstacles.get(i).getHeight());
                    g2d.drawImage(image, obstacles.get(i).getCoordinate_x()+obstacles.get(i).getLength()/4,
                            obstacles.get(i).getCoordinate_y()+obstacles.get(i).getHeight()/6, null);
                    if (intersects.playerIntersectObstacle(player, obstacles.get(i))){
                        MyPanel.Health = obstacles.get(i).health_decrease(MyPanel.Health);
                        obstacles.remove(obstacles.get(i));
                    }
                }
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
