package Panel;
import Bullet.Bullet;
import Obtacles.*;
import Player.Player;
import Enemy.*;
import Player.*;
import Results.CreateResult;
import Results.Result_board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyPanel extends JPanel implements KeyListener{

    public MyPanel() {
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
        setBackground(Color.white);
        executing_game_timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        executing_game_timer.start();
        Game_Timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results.Result_board.setSeconds_Duration_Game(Result_board.getSeconds_Duration_Game() +1);
            }
        });
        Game_Timer.start();
        Player.setPlayer(Playercreation.creating_player());
        Result_board.setResult_boards(CreateResult.creating_result_board());
        Tank_enemy.setTankEnemy(Enemycreation.creating_Tank());
        Soldier_enemy.setSoldier_enemy(Enemycreation.creating_Soldier());
        Missile_launcher.setMissileLauncher(Enemycreation.creating_Missile_launcher());
        SniperEnemy.setSniperEnemy(Enemycreation.creating_sniper());
        thief.setThief_list(Enemycreation.creating_enemy_thief());
        Army_enemy.setArmy_enemy(Enemycreation.creating_army_enemy());
        obstacle_creation.creating_wall(Wall.getWalls(), Wall.getWall(),0);
        obstacle_creation.creating_Mine(Mines.getMine(), Mines.getMines_coordinates(),15);
        obstacle_creation.creating_Laser(Laser.getLaser(), Laser.getlaser_coordinates(),10);
    }
    public static Timer  executing_game_timer;
    public static Timer Game_Timer;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Playercreation.PlayerDrawing(g,Player.getPlayer());
        Soldier_enemy.setSoldier_enemy(Enemycreation.armyenemey_drawing(g,Soldier_enemy.getSoldier_enemy(),Player.getPlayer(),Bullet.getBullet_position(),Result_board.getHealth(),"up"));
        Tank_enemy.setTankEnemy(Enemycreation.armyenemey_drawing(g,Tank_enemy.getTankEnemy(),Player.getPlayer(),Bullet.getBullet_position(),Result_board.getHealth(),"up"));
        Missile_launcher.setMissileLauncher(Enemycreation.armyenemey_drawing(g,Missile_launcher.getMissileLauncher(),Player.getPlayer(),Bullet.getBullet_position(),Result_board.getHealth(),"up"));
        SniperEnemy.setSniperEnemy(Enemycreation.armyenemey_drawing(g,SniperEnemy.getSniperEnemy(),Player.getPlayer(),Bullet.getBullet_position(),Result_board.getHealth(),"down"));
        Bullet.setBullet_position(Playercreation.BulletPlayerDrawing(g,Bullet.getBullet_position(),Player.getDirection_player(),thief.getThief_list()));
        thief.setThief_list(Enemycreation.ThiefEnemyDrawing(g,thief.getThief_list(),Player.getPlayer()));
        Army_enemy.setArmy_enemy(Enemycreation.armyenemey_drawing(g,Army_enemy.getArmy_enemy(),Player.getPlayer(),Bullet.getBullet_position(),Result_board.getHealth(),"down"));
        obstacle_creation.Obstacle_drawing(Wall.getWalls(),Bullet.getBullet_position(),g, Color.BLUE,null,Player.getPlayer());
        obstacle_creation.Obstacle_drawing(Laser.getLaser(),Bullet.getBullet_position(),g, Color.RED,null,Player.getPlayer());
        obstacle_creation.Obstacle_drawing(Mines.getMine(),Bullet.getBullet_position(),g, Color.YELLOW, Mines.getMine_icon(),Player.getPlayer());
        CreateResult.GameLablesDrawing(g);
        CreateResult.ResultBoardDrawing(g);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Playercreation.player_movement(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
