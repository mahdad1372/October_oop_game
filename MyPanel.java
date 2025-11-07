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
        creating_wall();
        creating_laser();
        creating_sniper();
        creating_enemy();
        creating_mines();
    }

    private Image Sniper_Bullet = new ImageIcon("sniper_bullet.png").getImage();
    private Image Mine = new ImageIcon("mine.png").getImage();
    private Image Player_icon = new ImageIcon("soldier.png").getImage();
    private Image Player_L = new ImageIcon("soldier_l.png").getImage();
    private Image Player_D = new ImageIcon("soldier_d.png").getImage();
    private Image Player_U = new ImageIcon("soldier_u.png").getImage();
    private Image Sniper = new ImageIcon("Sniper.png").getImage();
    private Image Launcher = new ImageIcon("launcher.png").getImage();
    private Image Missile_img = new ImageIcon("missile.png").getImage();
    private Image thief = new ImageIcon("thief.png").getImage();
    private Image Tank_rocket = new ImageIcon("tank_rocket.png").getImage();
    private Image Soldier_rocket = new ImageIcon("Soldier_rocket.png").getImage();
    private Image Tank = new ImageIcon("tank.png").getImage();
    private Image Soldier_enemy = new ImageIcon("Soldier_enemy.png").getImage();
    private String direction_player = "left";
    private Timer  executing_game_timer;
    private Timer Game_Timer;
    private int scores = 0;
    private int Health = 100;

    private int number_enemy_killed = 0;
    private boolean display_menu_winner;
    private int Seconds_Duration_Game;
    private Player player;
    private Tank_enemy tankEnemy;
    private Soldier_enemy soldier_enemy;
    private SniperEnemy sniperEnemy;
    private Mines mine;
    private Laser laser;

    private Missile_launcher MissileLauncher;
    private ArrayList<Result_board> Result_boards = new ArrayList<Result_board>();
    private ArrayList<thief> thief_list = new ArrayList<thief>();
    private ArrayList<Bullet> bullet_position = new ArrayList<Bullet>();
    private ArrayList<Wall> Walls = new ArrayList<Wall>();


    private void creating_player(){
        this.player = new Player(0,0,Player_icon);
    }
    private void creating_Menu(){
        Result_boards.add(new Result_board(70,40,780,250,"winner"));
        Result_boards.add(new Result_board(70,40,780,250,"looser"));
    }
    private void creating_Tank(){
        Tank_rocket tank_rocket = new Tank_rocket(580, 230, Tank_rocket,580,50);
        this.tankEnemy = new Tank_enemy(Tank,580,250,40,40,tank_rocket);
        this.tankEnemy.Shooting_Rocket_Tank();
    }
    private void creating_Soldier(){
        Soldier_rocket rocket = new Soldier_rocket(660, 320, Soldier_rocket,660,50);
        this.soldier_enemy = new Soldier_enemy(Soldier_enemy,660,320,40,40,rocket);
        this.soldier_enemy.Shooting_Rocket_Soldier();
    }
    private void creating_Missile_launcher(){
        Missile Missile = new Missile(160, 310, 270,200,Missile_img);
        this.MissileLauncher = new Missile_launcher(Launcher, 160, 300,30,30,Missile);
        this.MissileLauncher.Adding_Missiles();
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
        WallDrawing(g);
        LaserDrawing(g);
        drawing_Mines(g);
        ResultBoardDrawing(g);
        MissileLauncherDrawing(g);

    }
    private void BulletPlayerDrawing(Graphics g) {

        try {

            Graphics2D g2d = (Graphics2D) g;

            // ---- move & draw bullets ----
            for (Bullet b : bullet_position) {
                b.updatePosition(direction_player);

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

            // ---- bullet vs thief ----
            Iterator<Bullet> bulletIt = bullet_position.iterator();
            while (bulletIt.hasNext()) {
                Bullet bullet = bulletIt.next();

                Iterator<thief> thiefIt = thief_list.iterator();
                while (thiefIt.hasNext()) {
                    thief th = thiefIt.next();

                    if (bulletIntersectsEnemy(bullet, th)) {
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



    private void drawing_Mines(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.yellow);
        try{
            if (this.mine != null){
                g2d.fillOval(this.mine.get_coordinate_x(),this.mine.get_coordinate_y()
                        ,this.mine.calculate_area(),this.mine.calculate_area());
                g2d.drawImage(Mine, this.mine.get_coordinate_x()+ (this.mine.get_radius()/2), this.mine.get_coordinate_y(), null);
            }
            if (this.mine != null) {
                if (playerIntersectMine(this.mine)) {
                    Health = this.mine.health_decrease(Health);
                    this.mine = null;
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    private void TankEnemyDrawing(Graphics g){
        if (this.tankEnemy != null){
            g.drawImage(this.tankEnemy.getImage_enemy(),
                    this.tankEnemy.getPosition_enemy_x(),this.tankEnemy.getPosition_enemy_y(),null);
        }
        if (this.tankEnemy != null){

            for (int i = 0; i < this.tankEnemy.getTank_Rocket().size(); i++){
                if (playerIntersectTankRocket(this.tankEnemy.getTank_Rocket().get(i))) {
                    this.Health -=5;
                    int current_positionplayer_x =this.player.getPosition_x();
                    this.player.setPosition_x(current_positionplayer_x-=40);
                }
                this.tankEnemy.getTank_Rocket().get(i).Rocket_Shooting();
                g.drawImage(this.tankEnemy.getTank_Rocket().get(i).getRocket_image(),
                        this.tankEnemy.getTank_Rocket().get(i).getPosition_coordinate_x(),
                        this.tankEnemy.getTank_Rocket().get(i).getPosition_coordinate_y(),null);
                this.tankEnemy.Editing_Rocket_List();

            }
            for (int i = 0; i < bullet_position.size(); i++){
                if (bulletIntersectsTank(bullet_position.get(i),this.tankEnemy)){
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


            for (int i = 0; i <this.sniperEnemy.get_Sniper_bullet().size(); i++){
                if (playerIntersectSniperBullet(this.sniperEnemy.get_Sniper_bullet().get(i))) {
                    this.Health -=5;
                    int current_positionplayer_x =this.player.getPosition_x();
                    this.player.setPosition_x(current_positionplayer_x-=40);
                }
                this.sniperEnemy.get_Sniper_bullet().get(i).shootingBullet();
                g.drawImage(this.sniperEnemy.get_Sniper_bullet().get(i).getSniper_bullet_image(),
                        this.sniperEnemy.get_Sniper_bullet().get(i).getPosition_coordinate_x(),
                        this.sniperEnemy.get_Sniper_bullet().get(i).getPosition_coordinate_y(),null);
                this.sniperEnemy.Editing_Sniper_Bullet_List();

            }

            for (int i = 0; i < bullet_position.size(); i++){
                if (bulletIntersectsSniperEnemy(bullet_position.get(i),this.sniperEnemy)){
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
            if (playerIntersectEnemy(enemies)){
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
            for (int i=0;i <this.soldier_enemy.getSoldier_Rocket().size();i++){
                this.soldier_enemy.getSoldier_Rocket().get(i).Rocket_Shooting();
                g.drawImage( this.soldier_enemy.getSoldier_Rocket().get(i).getRocket_image(),
                        this.soldier_enemy.getSoldier_Rocket().get(i).getPosition_coordinate_x(),
                        this.soldier_enemy.getSoldier_Rocket().get(i).getPosition_coordinate_y(),null);
            }
        }
        if (this.soldier_enemy != null){
            for (int i = 0; i < bullet_position.size(); i++){
                if (bulletIntersectsSoldierEnemy(bullet_position.get(i),this.soldier_enemy)){
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
            for (int i=0;i <this.MissileLauncher.get_Missiles().size();i++){
                this.MissileLauncher.get_Missiles().get(i).Missile_Shooting();
                g.drawImage(this.MissileLauncher.get_Missiles().get(i).getMissileImage(),
                        this.MissileLauncher.get_Missiles().get(i).getPosition_coordinate_x() ,
                        this.MissileLauncher.get_Missiles().get(i).getPosition_coordinate_y(),null);
            }
            this.MissileLauncher.Editing_Missile_List();
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

    private void WallDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        for (int i = 0 ; i< Walls.size();i++){
            g2d.fillRect(Walls.get(i).getPosition_Wall_x(),Walls.get(i).getPosition_Wall_y(),
                    Walls.get(i).getWidth(),Walls.get(i).getHeight());
        }
        if (Walls.size()>0){
            for (int i=0; i<Walls.size();i++){
                for (int j=0; j<bullet_position.size();j++){
                    if (bulletIntersectsMaze(bullet_position.get(j),Walls.get(i))){
                        boolean found = bullet_position.contains(bullet_position.get(j));
                        if (found){
                            bullet_position.remove(bullet_position.get(j));
                        }
                    }
                }
            }
        }
        if (Walls.size()>0){
            for (Wall wall:Walls){
                if (playerIntersectMaze(wall)){
                    this.player.setPosition_x(wall.getPosition_Wall_x() - (wall.getWidth()+30));
                    this.player.setPosition_y(wall.getPosition_Wall_y());
                }
            }
        }
    }
    private void creating_wall() {
        int[][] w = {
                {130,0,20,140},
                {130,200,20,140},
                {150,40,120,20},
                {270,40,20,120},
                {270,100,180,20},
                {270,240,20,130},
                {270,345,180,20},
                {430,120,20,120},
                {530,190,20,170},
                {530,0,20,120},
                {550,300,100,20},
                {630,80,20,220},
                {630,20,300,20},
                {710,20,20,160},
                {710,270,20,100},
                {710,180,230,20}
        };

        for (int[] v : w) {
            Walls.add(new Wall(v[0], v[1], v[2], v[3]));
        }
    }
    private void LaserDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.fillRect(laser.get_coordinate_x(),laser.get_coordinate_y(),
                laser.calculate_area()/laser.get_height(),
                laser.calculate_area()/laser.get_length());
        if (this.laser != null){
            if (playerIntersectLaser(laser)){
                this.player.setPosition_x(laser.get_coordinate_x());
                this.player.setPosition_y(laser.get_coordinate_y());
                Health = laser.health_decrease(Health);
            }
        }
    }
    private void creating_mines(){
        obstacles mine = new Mines(20,410,300);
        this.mine = (Mines) mine;
    }
    private void creating_laser(){
        obstacles laser = new Laser(5,230,710,250);
        this.laser = (Laser) laser;
    }
    private void creating_sniper(){
        SniperBullet sniperBullet = new SniperBullet(470,10,Sniper_Bullet,400);
        this.sniperEnemy = new SniperEnemy(Sniper,470,10,20,20,sniperBullet);
        this.sniperEnemy.Shooting_Sniper_Bullet();
    }
    private void creating_enemy() {

        Object[][] data = {
                {200,70,30,30,"Y",320},
                {390,165,30,30,"X",120},
                {350,310,30,30,"Y",120},
                {595,140,30,30,"X",450},
                {580,10,30,30,"Y",180},
                {665,50,30,30,"Y",210}
        };

        for (Object[] d : data) {
            thief_list.add(new thief(
                    thief,                  // <-- your sprite/bitmap
                    (int) d[0],
                    (int) d[1],
                    (int) d[2],
                    (int) d[3],
                    (String) d[4],
                    (int) d[5]
            ));
        }
    }
    private boolean bulletIntersectsEnemy(Bullet bullet, Enemy enemy) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(enemyRect);
    }
    private boolean bulletIntersectsSoldierEnemy(Bullet bullet, Soldier_enemy soldier_enemy) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10, 10);
        Rectangle soldier_enemy_Rect = new Rectangle(soldier_enemy.getPosition_enemy_x(), soldier_enemy.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(soldier_enemy_Rect);
    }
    private boolean bulletIntersectsTank(Bullet bullet, Tank_enemy Tank) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10, 10);
        Rectangle tankRect = new Rectangle(Tank.getPosition_enemy_x(), Tank.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(tankRect);
    }
    private boolean bulletIntersectsSniperEnemy(Bullet bullet, SniperEnemy sniper) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(sniper.getPosition_enemy_x(), sniper.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(enemyRect);
    }
    private boolean bulletIntersectsMaze(Bullet bullet, Wall wall) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_coordinate_x(), bullet.getPosition_coordinate_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(wall.getPosition_Wall_x(), wall.getPosition_Wall_y(),
                wall.getWidth(),wall.getHeight());

        return bulletRect.intersects(enemyRect);
    }
    private boolean playerIntersectMaze(Wall wall) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle wallRect = new Rectangle(wall.getPosition_Wall_x(), wall.getPosition_Wall_y(),
                wall.getWidth(),wall.getHeight());

        return playerRect.intersects(wallRect);
    }
    private boolean playerIntersectLaser(Laser laser) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle wallRect = new Rectangle(laser.get_coordinate_x(), laser.get_coordinate_y(),
                laser.get_length(),laser.get_height());

        return playerRect.intersects(wallRect);
    }
    private boolean playerIntersectEnemy(Enemy enemy) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle enemyRect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                enemy.getWidth(),enemy.getHeight());

        return playerRect.intersects(enemyRect);
    }
    private boolean playerIntersectTankRocket(Tank_rocket tank_rocket) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle enemyRect = new Rectangle(tank_rocket.getPosition_coordinate_x(), tank_rocket.getPosition_coordinate_y(),
                10,30);

        return playerRect.intersects(enemyRect);
    }
    private boolean playerIntersectSniperBullet(SniperBullet sniperBullet) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle bulletRect = new Rectangle(sniperBullet.getPosition_coordinate_x(), sniperBullet.getPosition_coordinate_y(),
                10,30);

        return playerRect.intersects(bulletRect);
    }
    private boolean playerIntersectMine(Mines mine) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle mineRect = new Rectangle(mine.get_coordinate_x(), mine.get_coordinate_y(),
                mine.get_radius()*2, mine.get_radius()*2);

        return playerRect.intersects(mineRect);
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
            Bullet bullet = new Bullet(bullet_position_x, bullet_position_y);
            bullet_position.add(bullet);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            this.direction_player = "left";
            this.player.player_movement(this.direction_player);
            this.player.setPlayerIcon(this.Player_L);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.direction_player = "right";
            this.player.player_movement(this.direction_player);
            this.player.setPlayerIcon(this.Player_icon);

        } else if (keyCode == KeyEvent.VK_UP) {
            this.direction_player = "up";
            this.player.player_movement(this.direction_player);
            this.player.setPlayerIcon(this.Player_U);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.direction_player = "down";
            this.player.player_movement(this.direction_player);
            this.player.setPlayerIcon(this.Player_D);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
