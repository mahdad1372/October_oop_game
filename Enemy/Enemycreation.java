package Enemy;

import Assets.Assets;
import Bullet.*;
import Player.Player;
import Utils.CollisionUtils;
import Panel.MyPanel;
import java.awt.*;
import java.util.ArrayList;

public final class Enemycreation {
    private static int score;
    public static Army_enemy creating_Tank(){
        Tank_rocket tank_rocket = new Tank_rocket(580, 230, Assets.Tank_rocket,580,50);
        Army_enemy tank = new Tank_enemy(Assets.Tank,580,250,40,40,tank_rocket);
        tank.Shooting_Rocket();
        return tank;
    }
    public static Army_enemy creating_Soldier() {
        Soldier_rocket rocket = new Soldier_rocket(660, 320, Assets.Soldier_rocket, 660, 50);
        Army_enemy Soldier_enemy = new Soldier_enemy(Assets.Soldier_enemy, 660, 320, 40, 40, rocket);
        Soldier_enemy.Shooting_Rocket();
        return Soldier_enemy;
    }
    public static Army_enemy creating_Missile_launcher(){
        Missile Missile = new Missile(160, 310, Assets.Missile_img, 270,200);
        Army_enemy missile_launcher = new Missile_launcher(Assets.Launcher, 160, 300,30,30,Missile);
        missile_launcher.Shooting_Rocket();
        return missile_launcher;
    }
    public static void creating_sniper(Army_enemy enemy){
        SniperBullet sniperBullet = new SniperBullet(470,10, Assets.Sniper_Bullet,400);
        enemy = new SniperEnemy(Assets.Sniper,470,10,20,20,sniperBullet);
        enemy.Shooting_Rocket();
    }



    public static Army_enemy armyenemey_drawing(Graphics g , Army_enemy armenemy, Player player, ArrayList<Bullet> Bullet_list,Integer Health,String shooting_direction){
        if (armenemy != null){
            g.drawImage(armenemy.getImage_enemy(),
                    armenemy.getPosition_enemy_x(),armenemy.getPosition_enemy_y(),null);
        }
        if (armenemy != null){

            for (int i = 0; i < armenemy.getRocket().size(); i++){
                if (CollisionUtils.playerIntersectBullet(player,armenemy.getRocket().get(i))) {
                    MyPanel.Health -=5;
                    int current_positionplayer_x =player.getPosition_x();
                    player.setPosition_x(current_positionplayer_x-=40);
                }
                armenemy.getRocket().get(i).shootingDirection(shooting_direction);
                g.drawImage(armenemy.getRocket().get(i).getBulletimg(),
                        armenemy.getRocket().get(i).getPosition_coordinate_x(),
                        armenemy.getRocket().get(i).getPosition_coordinate_y(),null);
                armenemy.Editing_Rocket_List();
            }
            for (int i = 0; i < Bullet_list.size(); i++){
                if (armenemy != null && CollisionUtils.intersects(Bullet_list.get(i), armenemy)) {
                    MyPanel.scores += armenemy.killing_enemy_score();
                    getScore();
                    Bullet_list.remove(i);
                    armenemy = null;
                    break;
                }
            }
        }
        return armenemy;
    }
    public static int getScore() {
        return score;
    }
}
