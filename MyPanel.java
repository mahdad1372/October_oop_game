import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyPanel extends JPanel implements KeyListener{
    public MyPanel() {
        this.player = new Player(0,0,Player_icon);
        Menu_list.add(new Menu(70,40,780,250,"winner"));
        Menu_list.add(new Menu(70,40,780,250,"looser"));
        Tank_rocket tank_rocket = new Tank_rocket(580, 230, Tank_rocket,580,50);
        Tank_enemy tank_enemy = new Tank_enemy(Tank,580,250,40,40,"down",80,tank_rocket);
        this.tankEnemy = tank_enemy;
        Soldier_rocket rocket = new Soldier_rocket(660, 320, Soldier_rocket,660,50);
        this.soldier_enemy = new Soldier_enemy(Soldier_enemy,660,320,40,40,"down",80,rocket);
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
        WallCoordinates();
        LaserCoordinates();
        SniperCoordinates();
        Shooting_Missile();

        Enemy_coordinates();
        Shooting_Bullet_Sniper();
        Mines_coordinate();
        this.soldier_enemy.Shooting_Rocket_Soldier();
        this.tankEnemy.Shooting_Rocket_Tank();

    }

    private Image Sniper_Bullet = new ImageIcon("bullet_down.png").getImage();
    private Image Mine = new ImageIcon("mine.png").getImage();
    private Image Player_icon = new ImageIcon("soldier.png").getImage();
    private Image Player_L = new ImageIcon("soldier_l.png").getImage();
    private Image Player_D = new ImageIcon("soldier_d.png").getImage();
    private Image Player_U = new ImageIcon("soldier_u.png").getImage();
    private Image Sniper = new ImageIcon("Sniper_down.png").getImage();
    private Image Launcher = new ImageIcon("launcher.png").getImage();
    private Image Missile_img = new ImageIcon("missile.png").getImage();
    private Image thief = new ImageIcon("thief.png").getImage();
    private Image Tank_rocket = new ImageIcon("bullet_tank.png").getImage();
    private Image Soldier_rocket = new ImageIcon("tank_rocket.png").getImage();
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
    ArrayList<Mines> Mines_list = new ArrayList<Mines>();
    ArrayList<Laser> Laser_list = new ArrayList<Laser>();
    ArrayList<Menu> Menu_list = new ArrayList<Menu>();
    ArrayList<thief> thief_list = new ArrayList<thief>();
    ArrayList<SniperEnemy> SniperEnemy = new ArrayList<SniperEnemy>();
    ArrayList<Missile> Missile = new ArrayList<Missile>();
    ArrayList<Bullet> bullet_position = new ArrayList<Bullet>();
    ArrayList<Wall> Walls = new ArrayList<Wall>();


    private void Shooting_Bullet_Sniper(){
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i =0; i < SniperEnemy.size();i++){
                    SniperEnemy.get(i).add_bullet(SniperEnemy.get(i).getPosition_enemy_x(),
                            SniperEnemy.get(i).getPosition_enemy_y(),SniperEnemy.get(i).getSniper_number(),
                            SniperEnemy.get(i).getFinal_position(), SniperEnemy.get(i).getDirection());
                }

            }
        };

        timer.schedule(task, 0, 2000);

    }
    private void Shooting_Missile(){
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Missile.add(new Missile(160, 310, 270,200));
            }
        };
        timer.schedule(task, 0, 3000);

    }


    public void bullet_position(Integer index){
        ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);

        Runnable function1 = new Runnable() {
            @Override
            public void run() {
                try {
                    bullet_position.get(index).getPosition_x_on_time(direction_player);
                } catch (Exception e){
                }
            }
        };
        executor2.scheduleAtFixedRate(function1, 0, 12, TimeUnit.MILLISECONDS);
    }
    private void createBullet(Graphics2D g2d , int x , int y , int r){
        g2d.setColor(Color.BLUE);
        int radius = r;
        g2d.fillOval(x, y, radius, radius);
        g2d.drawOval(x, y, radius, radius);
    }

    private void Mines_coordinate(){
        Mines_list.add(new Mines(20,410,300));
    }
    private void create_Mines(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.yellow);
        for (int i=0;i< Mines_list.size();i++){
            g2d.fillOval(Mines_list.get(i).get_coordinate_x(),Mines_list.get(i).get_coordinate_y()
                    ,Mines_list.get(i).calculate_area(),Mines_list.get(i).calculate_area());
            g2d.drawImage(Mine, Mines_list.get(i).get_coordinate_x()+ (Mines_list.get(i).get_radius()/2), Mines_list.get(i).get_coordinate_y(), null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        FinalResult<String, Integer,Integer,String> result_win = new FinalResult<>("Mahdad", this.Health,this.scores,
                "winner");
        double double_health = this.Health;
        double double_score = this.scores;
        FinalResult<String, Double,Double,String> result_lose = new FinalResult<>("Mahdad", double_health,double_score,
                "looser");
        g.drawImage(player.getPlayerIcon(), this.player.getPosition_x(), this.player.getPosition_y(), null);
        g.drawImage(Launcher, 160, 300, null);
        if (SniperEnemy.size()>0){
            ArrayList<SniperBullet> SniperBullet_List = SniperEnemy.get(0).return_bullet();
            for (int i=0;i< SniperBullet_List.size();i++){
                g.drawImage(Sniper_Bullet, SniperBullet_List.get(i).getPosition_x() , SniperBullet_List.get(i).getPosition_y(),null);
                SniperBullet_List.get(i).shootingBullet();

            }
        }

        if (this.soldier_enemy != null){
            g.drawImage(this.soldier_enemy.getImage_enemy(), this.soldier_enemy.getPosition_enemy_x()
                    , this.soldier_enemy.getPosition_enemy_y(),null);
            this.soldier_enemy.Editing_Rocket_List();
            for (int i=0;i <this.soldier_enemy.getSoldier_Rocket().size();i++){
                this.soldier_enemy.getSoldier_Rocket().get(i).Rocket_Shooting();
                    g.drawImage( this.soldier_enemy.getSoldier_Rocket().get(i).getRocket_image(),
                            this.soldier_enemy.getSoldier_Rocket().get(i).getPosition_x(),
                            this.soldier_enemy.getSoldier_Rocket().get(i).getPosition_y(),null);
                }
        }


        for (int i=0;i< Missile.size();i++){
            g.drawImage(Missile_img, Missile.get(i).getPosition_x() , Missile.get(i).getPosition_y(),null);
            Missile.get(i).Missile_Shooting();
            if (Missile.get(i).getPosition_x() ==Missile.get(i).getPositionFinal_x() &&
                    Missile.get(i).getPosition_y() ==Missile.get(i).getPositionFinal_y()){
                Missile.remove(i);
            }
        }


        for (int i=0;i< SniperEnemy.size();i++){
            g.drawImage(SniperEnemy.get(i).getImage_enemy(),
                    SniperEnemy.get(i).getPosition_enemy_x() , SniperEnemy.get(i).getPosition_enemy_y(),null);
        }

        if (this.tankEnemy != null){
            g.drawImage(this.tankEnemy.getImage_enemy(),
                    this.tankEnemy.getPosition_enemy_x(),this.tankEnemy.getPosition_enemy_y(),null);
        }

        for (int i=0;i< thief_list.size();i++){
            g.drawImage(thief_list.get(i).getImage_enemy(),
                    thief_list.get(i).getPosition_enemy_x() , thief_list.get(i).getPosition_enemy_y(),null);
            thief_list.get(i).enemy_movement( thief_list.get(i).getCurr_position(),
                    thief_list.get(i).getFinal_position(),thief_list.get(i).getDirection());

        }
        if (bullet_position.size() > 0){
            for (int i =0; i <bullet_position.size();i++){
                createBullet(g2d,bullet_position.get(i).getPosition_x(),bullet_position.get(i).getPosition_y(),
                        10);
                boolean found = bullet_position.contains(bullet_position.get(i));
                if (found){
                    bullet_position(bullet_position.get(i).getPosition_x());
                    bullet_position(i);
                }
            }
        }

        try{
            for (Bullet bullet:bullet_position){
                for (thief thief:thief_list){
                    if (bulletIntersectsEnemy(bullet,thief)){
                        thief_list.remove(thief);
                        bullet_position.remove(bullet);
                        scores += 10;
                        number_enemy_killed += 1;
                    }
                }
            }
        }catch (Exception e){
            //System.out.println("An unexpected error occurred: " + e.getMessage());
        }


        if (SniperEnemy.size()>0) {
            for (int j = 0; j < SniperEnemy.size(); j++) {
                for (int i = 0; i < bullet_position.size(); i++) {

                        if (bulletIntersectsSniperEnemy(bullet_position.get(i), SniperEnemy.get(j))) {
                            if (SniperEnemy.contains(SniperEnemy.get(j))) {
                                SniperEnemy.remove(SniperEnemy.get(j));
                            }
                            if (bullet_position.contains(bullet_position.get(i))) {
                                bullet_position.remove(bullet_position.get(i));
                            }
                            number_enemy_killed += 1;
                            scores += 10;
                    }
                }
            }
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
                        this.tankEnemy.getTank_Rocket().get(i).getPosition_x(),
                        this.tankEnemy.getTank_Rocket().get(i).getPosition_y(),null);
                this.tankEnemy.Editing_Rocket_List();

            }
            for (int i = 0; i < bullet_position.size(); i++){
                if (bulletIntersectsTank(bullet_position.get(i),this.tankEnemy)){
                    this.tankEnemy = null;
                    this.scores +=10;
                }}
        }

        if (this.soldier_enemy != null){
            for (int i = 0; i < bullet_position.size(); i++){
                if (bulletIntersectsSoldierEnemy(bullet_position.get(i),this.soldier_enemy)){
                   this.soldier_enemy = null;
                }}}

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
        if (Laser_list.size()>0){
            for (Laser laser:Laser_list){
                if (playerIntersectLaser(laser)){
                    this.player.setPosition_x(laser.get_coordinate_x());
                    this.player.setPosition_y(laser.get_coordinate_y());
                    Health = laser.health_decrease(Health);
                }
            }
        }
        if (Mines_list.size()>0) {
                for (int i = 0; i < Mines_list.size(); i++) {
                    if (playerIntersectMine(Mines_list.get(i))) {
                        if (Mines_list.contains(Mines_list.get(i))) {
                            Health = Mines_list.get(i).health_decrease(Health);
                            Mines_list.remove(Mines_list.get(i));
                        }
                    }
                }

        }

        for (thief enemies:thief_list){
            if (playerIntersectEnemy(enemies)){
                int current_positionplayer_x =this.player.getPosition_x();
                this.player.setPosition_x(current_positionplayer_x-=40);
                Health-=10;
            }
        }

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
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.setColor(Color.BLUE);
        String text = "Score : "+ scores;
        int x = 750;
        int y = 100;
        g.drawString(text, x, y);
        g.setColor(Color.RED);
        String text2 = "Health : " + Health +" %";
        g.drawString(text2, 750, 150);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.GREEN);
        String text3 = "Exit";
        g.drawString(text3, 890, 230);
        WallDrawing(g);
        LaserDrawing(g);
        create_Mines(g);
        for (Menu menu:Menu_list){
            if (display_menu_winner == true && menu.get_menu_type() == "winner"){
                g2d.setColor(Color.GREEN);
                g2d.fillRect(menu.getPosition_menu_x(),menu.getPosition_menu_y(),menu.getWidth(),menu.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString(result_win.getStatus(), 380, 110);
                g2d.drawString("The winner is : " + result_win.getName()+
                        " with the score of " + result_win.getScore()+
                        "and the Health of " + result_win.getHealth(), 80, 210);
                g2d.drawString(menu.get_num_enemy_time(number_enemy_killed , Seconds_Duration_Game), 80, 240);
            }
            if (Health <= 0 && menu.get_menu_type() == "looser"){
                g2d.setColor(Color.RED);
                g2d.fillRect(menu.getPosition_menu_x(),menu.getPosition_menu_y(),menu.getWidth(),menu.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString(result_lose.getStatus(), 380, 110);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.drawString("The looser is : " + result_lose.getName()+
                        " with the score of " + result_lose.getScore()+
                        " and the Health of " + result_lose.getHealth(), 100, 210);
                g2d.drawString(menu.get_num_enemy_time((double) number_enemy_killed , (double) Seconds_Duration_Game), 100, 240);
            }

        }

    }
    public void WallDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        for (int i = 0 ; i< Walls.size();i++){
            g2d.fillRect(Walls.get(i).getPosition_Wall_x(),Walls.get(i).getPosition_Wall_y(),
                    Walls.get(i).getWidth(),Walls.get(i).getHeight());
        }
    }
    public void WallCoordinates(){
        Walls.add(new Wall(130,0,20,140));
        Walls.add(new Wall(130,200,20,140));
        Walls.add(new Wall(150,40,120,20));
        Walls.add(new Wall(270,40,20,120));
        Walls.add(new Wall(270,100,180,20));
        Walls.add(new Wall(270,240,20,130));
        Walls.add(new Wall(270,345,180,20));
        Walls.add(new Wall(430,120,20,120));
        Walls.add(new Wall(530,190,20,170));
        Walls.add(new Wall(530,0,20,120));
        Walls.add(new Wall(550,300,100,20));
        Walls.add(new Wall(630,80,20,220));
        Walls.add(new Wall(630,20,300,20));
        Walls.add(new Wall(710,20,20,160));
        Walls.add(new Wall(710,270,20,100));
        Walls.add(new Wall(710,180,230,20));
    }
    public void LaserDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        for (int i = 0 ; i< Laser_list.size();i++){
            g2d.fillRect(Laser_list.get(i).get_coordinate_x(),Laser_list.get(i).get_coordinate_y(),
                    Laser_list.get(i).calculate_area()/Laser_list.get(i).get_height(),
                    Laser_list.get(i).calculate_area()/Laser_list.get(i).get_length());
        }
    }
    public void LaserCoordinates(){
        Laser_list.add(new Laser(5,230,710,250));
    }
    public void SniperCoordinates(){
        SniperEnemy snipers = new SniperEnemy(Sniper,470,10,20,20,"Y",400,1);
        SniperEnemy.add(snipers);
    }
    public void Enemy_coordinates(){
        thief thief1 = new thief(thief,200,70,30,30,"Y",320);
        thief thief2 = new thief(thief,390,165,30,30,"X",120);
        thief thief3 = new thief(thief,350,310,30,30,"Y",120);
        thief thief4 = new thief(thief,595,140,30,30,"X",450);
        thief thief5 = new thief(thief,580,10,30,30,"Y",180);
        thief thief6 = new thief(thief,665,50,30,30,"Y",210);
        thief_list.add(thief1);
        thief_list.add(thief2);
        thief_list.add(thief3);
        thief_list.add(thief4);
        thief_list.add(thief5);
        thief_list.add(thief6);
    }
    public boolean bulletIntersectsEnemy(Bullet bullet, Enemy enemy) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_x(), bullet.getPosition_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(enemyRect);
    }
    public boolean bulletIntersectsSoldierEnemy(Bullet bullet, Soldier_enemy soldier_enemy) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_x(), bullet.getPosition_y(),
                10, 10);
        Rectangle soldier_enemy_Rect = new Rectangle(soldier_enemy.getPosition_enemy_x(), soldier_enemy.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(soldier_enemy_Rect);
    }
    public boolean bulletIntersectsTank(Bullet bullet, Tank_enemy Tank) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_x(), bullet.getPosition_y(),
                10, 10);
        Rectangle tankRect = new Rectangle(Tank.getPosition_enemy_x(), Tank.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(tankRect);
    }
    public boolean bulletIntersectsSniperEnemy(Bullet bullet, SniperEnemy sniper) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_x(), bullet.getPosition_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(sniper.getPosition_enemy_x(), sniper.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(enemyRect);
    }
    public boolean bulletIntersectsMaze(Bullet bullet, Wall wall) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_x(), bullet.getPosition_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(wall.getPosition_Wall_x(), wall.getPosition_Wall_y(),
                wall.getWidth(),wall.getHeight());

        return bulletRect.intersects(enemyRect);
    }
    public boolean playerIntersectMaze(Wall wall) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle wallRect = new Rectangle(wall.getPosition_Wall_x(), wall.getPosition_Wall_y(),
                wall.getWidth(),wall.getHeight());

        return playerRect.intersects(wallRect);
    }
    public boolean playerIntersectLaser(Laser laser) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle wallRect = new Rectangle(laser.get_coordinate_x(), laser.get_coordinate_y(),
                laser.get_length(),laser.get_height());

        return playerRect.intersects(wallRect);
    }
    public boolean playerIntersectEnemy(Enemy enemy) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle enemyRect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                enemy.getWidth(),enemy.getHeight());

        return playerRect.intersects(enemyRect);
    }
    public boolean playerIntersectTankRocket(Tank_rocket tank_rocket) {
        Rectangle playerRect = new Rectangle(this.player.getPosition_x(), this.player.getPosition_y(),
                30, 30);
        Rectangle enemyRect = new Rectangle(tank_rocket.getPosition_x(), tank_rocket.getPosition_y(),
                10,30);

        return playerRect.intersects(enemyRect);
    }
    public boolean playerIntersectMine(Mines mine) {
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
