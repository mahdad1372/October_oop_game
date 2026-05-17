package Panel;

import Assets.assets;
import Bullet.Bullet;
import Player.Player;
import Enemy.*;
import Obtacles.Mines;
import Obtacles.Obstacles;
import Obtacles.obstacle_creation;
import Results.FinalResult;
import Bullet.Tank_rocket;
import Bullet.Missile;
import Bullet.Soldier_rocket;
import Bullet.player_bullet;
import Results.Result_board;
import Utils.intersects;
import Bullet.SniperBullet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;


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
                Seconds_Duration_Game++;
            }
        });
        Game_Timer.start();
        creating_player();
        creating_Menu();
        creating_Tank();
        creating_Soldier();
        creating_Missile_launcher();
        obstacle_creation.creating_obstacles(Walls, assets.wall,0);
        obstacle_creation.creating_obstacles(Mines, assets.mines,15);
        obstacle_creation.creating_obstacles(Laser, assets.laser,10);
        creating_sniper();
        creating_enemy();
    }


    private static String direction_player = "left";
    private static Timer  executing_game_timer;
    private Timer Game_Timer;
    private static int scores = 0;
    public static int Health = 100;
    private int number_enemy_killed = 0;
    private static boolean display_menu_winner;
    private int Seconds_Duration_Game;
    private static Player player;
    private static Army_enemy tankEnemy;
    private static Army_enemy soldier_enemy;
    private static Army_enemy sniperEnemy;

    private static Mines mine;

    private static Army_enemy MissileLauncher;
    private static ArrayList<Result_board> Result_boards = new ArrayList<Result_board>();
    private static ArrayList<thief> thief_list = new ArrayList<thief>();
    private static ArrayList<Bullet> bullet_position = new ArrayList<>();
    private static ArrayList<Obstacles> Walls = new ArrayList<Obstacles>();
    private static ArrayList<Obstacles> Mines = new ArrayList<Obstacles>();
    private static ArrayList<Obstacles> Laser = new ArrayList<Obstacles>();


    private void creating_player(){
        this.player = new Player(0,0, assets.Player_icon);
    }
    private void creating_Menu(){
        Result_boards.add(new Result_board(70,40,780,250,"winner"));
        Result_boards.add(new Result_board(70,40,780,250,"looser"));
    }
    private void creating_Tank(){
        Tank_rocket tank_rocket = new Tank_rocket(580, 230, assets.Tank_rocket,580,50);
        Army_enemy tank = new Tank_enemy(assets.Tank,580,250,40,40,tank_rocket);
        this.tankEnemy = tank;
        this.tankEnemy.Shooting_Rocket();

    }
    private void creating_Soldier(){
        Soldier_rocket rocket = new Soldier_rocket(660, 320, assets.Soldier_rocket,660,50);
        Army_enemy Soldier_enemy = new Soldier_enemy(assets.Soldier_enemy,660,320,40,40,rocket);
        this.soldier_enemy = Soldier_enemy;
        this.soldier_enemy.Shooting_Rocket();
    }
    private void creating_Missile_launcher(){
        Missile Missile = new Missile(160, 310, assets.Missile_img, 270,200);
        Army_enemy missile_launcher = new Missile_launcher(assets.Launcher, 160, 300,30,30,Missile);
        this.MissileLauncher = missile_launcher;
        this.MissileLauncher.Shooting_Rocket();
    }

    private void creating_sniper(){
        SniperBullet sniperBullet = new SniperBullet(470,10, assets.Sniper_Bullet,400);
        this.sniperEnemy = new SniperEnemy(assets.Sniper,470,10,20,20,sniperBullet);
        this.sniperEnemy.Shooting_Rocket();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        SoldierEnemyDrawing(g);
        SniperEnemyDrawing(g);
        TankEnemyDrawing(g);
        BulletPlayerDrawing(g);
        ThiefEnemyDrawing(g);
        GameLablesDrawing(g);
        PlayerDrawing(g);
        ResultBoardDrawing(g);
        MissileLauncherDrawing(g);
        obstacle_creation.Obstacle_drawing(Walls,bullet_position,g, Color.BLUE,null,this.player);
        obstacle_creation.Obstacle_drawing(Laser,bullet_position,g, Color.RED,null,this.player);
        obstacle_creation.Obstacle_drawing(Mines,bullet_position,g, Color.YELLOW, assets.Mine,this.player);
    }

    private void BulletPlayerDrawing(Graphics g) {
        try {
            Graphics2D g2d = (Graphics2D) g;
            // ---- move & draw bullets ----
            for (Bullet b : bullet_position) {
                b.shootingDirection(direction_player);
                g2d.setColor(Color.BLUE);
                g2d.fillOval(
                        b.getPosition_coordinate_x(),
                        b.getPosition_coordinate_y(),
                        10, 10
                );
            }
            // ---- remove bullets off screen ----
            bullet_position.removeIf(b ->
                    b.getPosition_coordinate_x() < 0 ||
                            b.getPosition_coordinate_x() > getWidth() ||
                            b.getPosition_coordinate_y() < 0 ||
                            b.getPosition_coordinate_y() > getHeight()
            );

            // ---- bullet vs Enemy.thief ----
            Iterator<Bullet> bulletIt = bullet_position.iterator();
            while (bulletIt.hasNext()) {
                Bullet bullet = bulletIt.next();

                Iterator<thief> thiefIt = thief_list.iterator();
                while (thiefIt.hasNext()) {
                    thief th = thiefIt.next();

                    if (intersects.intersects(bullet, th)) {
                        thiefIt.remove();
                        bulletIt.remove();
                        scores += th.killing_enemy_score();
                        number_enemy_killed++;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void TankEnemyDrawing(Graphics g){
        if (this.tankEnemy != null){
            g.drawImage(this.tankEnemy.getImage_enemy(),
                    this.tankEnemy.getPosition_enemy_x(),this.tankEnemy.getPosition_enemy_y(),null);
        }
        if (this.tankEnemy != null){

            for (int i = 0; i < this.tankEnemy.getRocket().size(); i++){
                if (intersects.playerIntersectBullet(this.player,this.tankEnemy.getRocket().get(i))) {
                    this.Health -=5;
                    int current_positionplayer_x =this.player.getPosition_x();
                    this.player.setPosition_x(current_positionplayer_x-=40);
                }
                this.tankEnemy.getRocket().get(i).shootingDirection("up");
                g.drawImage(this.tankEnemy.getRocket().get(i).getBulletimg(),
                        this.tankEnemy.getRocket().get(i).getPosition_coordinate_x(),
                        this.tankEnemy.getRocket().get(i).getPosition_coordinate_y(),null);
                this.tankEnemy.Editing_Rocket_List();

            }
            for (int i = 0; i < bullet_position.size(); i++){
                if (intersects.intersects(bullet_position.get(i),this.tankEnemy)){
                    this.scores +=this.tankEnemy.killing_enemy_score();
                    this.tankEnemy = null;
                }}
        }
    }

    private void SniperEnemyDrawing(Graphics g){
        if (this.sniperEnemy != null){
            g.drawImage(this.sniperEnemy.getImage_enemy(),
                    this.sniperEnemy.getPosition_enemy_x(),this.sniperEnemy.getPosition_enemy_y(),null);
        }
        if (this.sniperEnemy != null){
            for (int i = 0; i <this.sniperEnemy.getRocket().size(); i++){
                if (intersects.playerIntersectBullet(this.player,this.sniperEnemy.getRocket().get(i))) {
                    this.Health -=5;
                    int current_positionplayer_x =this.player.getPosition_x();
                    this.player.setPosition_x(current_positionplayer_x-=40);
                }
                this.sniperEnemy.getRocket().get(i).shootingDirection("down");
                g.drawImage(this.sniperEnemy.getRocket().get(i).getBulletimg(),
                        this.sniperEnemy.getRocket().get(i).getPosition_coordinate_x(),
                        this.sniperEnemy.getRocket().get(i).getPosition_coordinate_y(),null);
                this.sniperEnemy.Editing_Rocket_List();

            }

            for (int i = 0; i < bullet_position.size(); i++){
                if (intersects.intersects(bullet_position.get(i),this.sniperEnemy)){
                    this.sniperEnemy = null;
                    this.scores +=10;
                }}
        }
    }

    private void ThiefEnemyDrawing(Graphics g){
        for (int i=0;i< thief_list.size();i++){
            g.drawImage(thief_list.get(i).getImage_enemy(),
                    thief_list.get(i).getPosition_enemy_x(),thief_list.get(i).getPosition_enemy_y(),null);
            thief_list.get(i).thief_movement(thief_list.get(i).getCurr_position(),
                    thief_list.get(i).getFinal_position(),thief_list.get(i).get_direction());
        }
        for (thief enemies:thief_list){
            if (intersects.playerIntersectEnemy(this.player,enemies)){
                int current_positionplayer_x =this.player.getPosition_x();
                this.player.setPosition_x(current_positionplayer_x-=40);
                Health-=10;
            }
        }
    }
    private void SoldierEnemyDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if (this.soldier_enemy != null){
            g.drawImage(this.soldier_enemy.getImage_enemy(), this.soldier_enemy.getPosition_enemy_x()
                    , this.soldier_enemy.getPosition_enemy_y(),null);
            this.soldier_enemy.Editing_Rocket_List();
            for (int i=0;i <this.soldier_enemy.getRocket().size();i++){
                this.soldier_enemy.getRocket().get(i).shootingDirection("up");
                g.drawImage( this.soldier_enemy.getRocket().get(i).getBulletimg(),
                        this.soldier_enemy.getRocket().get(i).getPosition_coordinate_x(),
                        this.soldier_enemy.getRocket().get(i).getPosition_coordinate_y(),null);
            }
        }
        if (this.soldier_enemy != null){
            for (int i = 0; i < bullet_position.size(); i++){
                if (intersects.intersects(bullet_position.get(i),this.soldier_enemy)){
                    this.soldier_enemy = null;
                }}}
    }

    private void GameLablesDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.setColor(Color.BLUE);
        String score = "Score : "+ scores;
        int x = 750;
        int y = 100;
        g.drawString(score, x, y);
        g.setColor(Color.RED);
        String health = "Health : " + Health +" %";
        g.drawString(health, 750, 150);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.GREEN);
        String exit = "Exit";
        g.drawString(exit, 890, 230);
    }
    private void MissileLauncherDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if (this.MissileLauncher != null){
            g.drawImage(this.MissileLauncher.getImage_enemy(), this.MissileLauncher.getPosition_enemy_x(),
                    this.MissileLauncher.getPosition_enemy_y(), null);
            for (int i=0;i <this.MissileLauncher.getRocket().size();i++){
                this.MissileLauncher.getRocket().get(i).shootingDirection("up");
                g.drawImage(this.MissileLauncher.getRocket().get(i).getBulletimg(),
                        this.MissileLauncher.getRocket().get(i).getPosition_coordinate_x() ,
                        this.MissileLauncher.getRocket().get(i).getPosition_coordinate_y(),null);
            }
            this.MissileLauncher.Editing_Rocket_List();
        }
    }
    private void PlayerDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(player.getPlayerIcon(), this.player.getPosition_x(), this.player.getPosition_y(), null);
    }
    private void ResultBoardDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        FinalResult<String, Integer,Integer,String> result_win = new FinalResult<>("Mahdad", this.Health,this.scores,
                "winner");
        double double_health = this.Health;
        double double_score = this.scores;
        FinalResult<String, Double,Double,String> result_lose = new FinalResult<>("Mahdad", double_health,double_score,
                "looser");
        if (this.player.getPosition_x() >= 890){
            executing_game_timer.stop();
            Game_Timer.stop();
            display_menu_winner = true;
        }
        if (Health <= 0){
            executing_game_timer.stop();
            Game_Timer.stop();
            display_menu_winner = false;
        }
        for (Result_board Result_board:Result_boards){
            if (display_menu_winner == true && Result_board.get_result_board_type() == "winner"){
                g2d.setColor(Color.GREEN);
                g2d.fillRect(Result_board.getPosition_menu_x(),Result_board.getPosition_menu_y(),Result_board.getWidth(),Result_board.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString(result_win.getStatus(), 380, 110);
                g2d.drawString("The winner is : " + result_win.getName()+
                        " with the score of " + result_win.getScore()+
                        "and the Health of " + result_win.getHealth(), 80, 210);
                g2d.drawString(Result_board.get_num_enemy_time(number_enemy_killed , Seconds_Duration_Game), 80, 240);
            }
            if (Health <= 0 && Result_board.get_result_board_type() == "looser"){
                g2d.setColor(Color.RED);
                g2d.fillRect(Result_board.getPosition_menu_x(),Result_board.getPosition_menu_y(),Result_board.getWidth(),Result_board.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString(result_lose.getStatus(), 380, 110);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.drawString("The looser is : " + result_lose.getName()+
                        " with the score of " + result_lose.getScore()+
                        " and the Health of " + result_lose.getHealth(), 100, 210);
                g2d.drawString(Result_board.get_num_enemy_time((double) number_enemy_killed , (double) Seconds_Duration_Game), 100, 240);
            }

        }
    }



    private void creating_enemy() {
        for (Object[] coordinate : assets.thief_enemy_coordinates) {
            thief_list.add(new thief(
                    assets.thief,                  // <-- your sprite/bitmap
                    (int) coordinate[0],
                    (int) coordinate[1],
                    (int) coordinate[2],
                    (int) coordinate[3],
                    (String) coordinate[4],
                    (int) coordinate[5]
            ));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            int bullet_position_x = this.player.getPosition_x();
            int bullet_position_y = this.player.getPosition_y();
            Bullet bullet = new player_bullet(bullet_position_x, bullet_position_y, assets.Sniper_Bullet);
            bullet_position.add(bullet);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            this.direction_player = "left";
            this.player.player_move_left();
            this.player.setPlayerIcon(assets.Player_L);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.direction_player = "right";
            this.player.player_move_right();
            this.player.setPlayerIcon(assets.Player_icon);

        } else if (keyCode == KeyEvent.VK_UP) {
            this.direction_player = "up";
            this.player.player_move_up();
            this.player.setPlayerIcon(assets.Player_U);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.direction_player = "down";
            this.player.player_move_down();
            this.player.setPlayerIcon(assets.Player_D);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
