package Panel;
import Assets.Assets;
import Bullet.Bullet;
import Player.Player;
import Enemy.*;
import Player.*;
import Obtacles.Obstacles;
import Obtacles.obstacle_creation;
import Results.CreateResult;
import Results.Result_board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class MyPanel extends JPanel implements KeyListener{
    public static String direction_player = "left";
    public static Timer  executing_game_timer;
    public static Timer Game_Timer;
    public static int scores = 0;
    public static int Health = 100;
    public static int number_enemy_killed = 0;
    public static boolean display_menu_winner;
    public static int Seconds_Duration_Game;
    public static Player player;
    private static Army_enemy tankEnemy;
    private static Army_enemy soldier_enemy;
    private static Army_enemy sniperEnemy;
    private static Army_enemy MissileLauncher;
    public static ArrayList<Result_board> Result_boards = new ArrayList<Result_board>();
    private static ArrayList<thief> thief_list = new ArrayList<thief>();
    public static ArrayList<Bullet> bullet_position = new ArrayList<>();
    private static ArrayList<Obstacles> Walls = new ArrayList<Obstacles>();
    private static ArrayList<Obstacles> Mines = new ArrayList<Obstacles>();
    private static ArrayList<Obstacles> Laser = new ArrayList<Obstacles>();
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
                Seconds_Duration_Game++;
            }
        });
        Game_Timer.start();
        this.player = Playercreation.creating_player();
        this.Result_boards = CreateResult.creating_result_board();
        this.tankEnemy = Enemycreation.creating_Tank();
        this.soldier_enemy = Enemycreation.creating_Soldier();
        this.MissileLauncher = Enemycreation.creating_Missile_launcher();
        this.sniperEnemy = Enemycreation.creating_sniper();
        this.thief_list = Enemycreation.creating_enemy_thief();
        obstacle_creation.creating_obstacles(Walls, Assets.wall,0);
        obstacle_creation.creating_obstacles(Mines, Assets.mines,15);
        obstacle_creation.creating_obstacles(Laser, Assets.laser,10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.soldier_enemy = Enemycreation.armyenemey_drawing(g,this.soldier_enemy,this.player,bullet_position,this.Health,"up");
        this.tankEnemy = Enemycreation.armyenemey_drawing(g,this.tankEnemy,this.player,bullet_position,this.Health,"up");
        this.MissileLauncher = Enemycreation.armyenemey_drawing(g,this.MissileLauncher,this.player,bullet_position,this.Health,"up");
        this.sniperEnemy = Enemycreation.armyenemey_drawing(g,this.sniperEnemy,this.player,bullet_position,this.Health,"down");
        this.bullet_position = Playercreation.BulletPlayerDrawing(g,bullet_position,this.direction_player,this.thief_list);
        this.thief_list = Enemycreation.ThiefEnemyDrawing(g,this.thief_list,player);
        this.player = Playercreation.PlayerDrawing(g,this.player);
        obstacle_creation.Obstacle_drawing(Walls,bullet_position,g, Color.BLUE,null,this.player);
        obstacle_creation.Obstacle_drawing(Laser,bullet_position,g, Color.RED,null,this.player);
        obstacle_creation.Obstacle_drawing(Mines,bullet_position,g, Color.YELLOW, Assets.Mine,this.player);
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
