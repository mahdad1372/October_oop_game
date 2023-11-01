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
    private Image image;

    private Image Bullet = new ImageIcon("bullet_down.png").getImage();

    private Image Player = new ImageIcon("soldier.png").getImage();
    private Image Player_L = new ImageIcon("soldier_l.png").getImage();
    private Image Player_D = new ImageIcon("soldier_d.png").getImage();
    private Image Player_U = new ImageIcon("soldier_u.png").getImage();
    private Image Player_icon = Player;
    private Image Sniper_down = new ImageIcon("Sniper_down.png").getImage();
    private Image Sniper_up = new ImageIcon("Sniper_up.png").getImage();
    private Image Launcher = new ImageIcon("launcher.png").getImage();
    private Image Missile_img = new ImageIcon("missile.png").getImage();
    private Image thief = new ImageIcon("thief.png").getImage();
    private String direction_player = "left";
    private int Bullet_y = 10;
    private int imageY;
    private Timer timer;
    private int player_x;
    private int player_y ;
    private int scores = 0;
    private int Health = 100;
    private boolean display_menu_winner;
    ArrayList<Menu> Menu_list = new ArrayList<Menu>();
    ArrayList<Enemy> enemy_lists = new ArrayList<Enemy>();
    ArrayList<thief> thief_list = new ArrayList<thief>();
    ArrayList<SniperBullet> SniperBullets = new ArrayList<SniperBullet>();
    ArrayList<SniperEnemy> SniperEnemy = new ArrayList<SniperEnemy>();
    ArrayList<Missile> Missile = new ArrayList<Missile>();
    ArrayList<Bullet> bullet_position = new ArrayList<Bullet>();
    ArrayList<Wall> Walls = new ArrayList<Wall>();
    public MyPanel() {
        Menu_list.add(new Menu(230,40,380,250,"winner"));
        Menu_list.add(new Menu(230,40,380,250,"looser"));
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
        setBackground(Color.white);

        image = new ImageIcon("thief.png").getImage();
        imageY = 10;

        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        WallCoordinates();
        SniperCoordinates();
        Shooting_Missile_Sniper();
        Enemy_coordinates();
        Shooting_Bullet_Sniper();
    }
    private void Shooting_Bullet_Sniper(){
        java.util.Timer timer = new java.util.Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i =0; i < SniperEnemy.size();i++){
                SniperBullets.add(new SniperBullet(SniperEnemy.get(i).getPosition_enemy_x()
                        ,SniperEnemy.get(i).getPosition_enemy_y(),SniperEnemy.get(i).getSniper_number(),
                        SniperEnemy.get(i).getFinal_position(), SniperEnemy.get(i).getDirection()));
                }
            }
        };

        timer.schedule(task, 0, 2000);

    }
    private void Shooting_Missile_Sniper(){
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

                bullet_position.get(index).getPosition_x_on_time(direction_player);

            }
        };
        executor2.scheduleAtFixedRate(function1, 0, 12, TimeUnit.MILLISECONDS);

    }
    public boolean Missile_Intersect(Missile missile) {
        Rectangle FinalRect = new Rectangle(missile.getPositionFinal_x(), missile.getPositionFinal_y(),
                10, 10);
        Rectangle MissileRect = new Rectangle(missile.getPosition_x(), missile.getPosition_y(),
                30,30);

        return FinalRect.intersects(MissileRect);
    }
    public boolean playerIntersectMissile(Missile missile) {
        Rectangle playerRect = new Rectangle(player_x, player_y,
                30, 30);
        Rectangle missile_rect = new Rectangle(missile.getPositionFinal_x(), missile.getPositionFinal_y(),
                20,20);

        return playerRect.intersects(missile_rect);

    }
    private void createBullet(Graphics2D g2d , int x , int y , int r){
        g2d.setColor(Color.BLUE);
        int radius = r;
        g2d.fillOval(x, y, radius, radius);
        g2d.drawOval(x, y, radius, radius);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(Player_icon, player_x, player_y, null);
        g.drawImage(Launcher, 160, 300, null);
        for (int i=0;i< SniperBullets.size();i++){
            g.drawImage(Bullet, SniperBullets.get(i).getPosition_x() , SniperBullets.get(i).getPosition_y(),null);
            SniperBullets.get(i).shootingBullet();

        }
        for (int i=0;i< Missile.size();i++){
            g.drawImage(Missile_img, Missile.get(i).getPosition_x() , Missile.get(i).getPosition_y(),null);
            Missile.get(i).Missile_Shooting();
            if (Missile.get(i).getPosition_x() ==Missile.get(i).getPositionFinal_x() &&
                    Missile.get(i).getPosition_y() ==Missile.get(i).getPositionFinal_y()){
                Missile.remove(i);

            }

        }
        for (int j=0;j< Missile.size();j++){
            if (playerIntersectMissile(Missile.get(j))){
                System.out.println(playerIntersectMissile(Missile.get(j)));
            }
        }
        for (int i=0;i< SniperEnemy.size();i++){
            g.drawImage(SniperEnemy.get(i).getImage_enemy(),
                    SniperEnemy.get(i).getPosition_enemy_x() , SniperEnemy.get(i).getPosition_enemy_y(),null);

            SniperBullets.get(i).shootingBullet();

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
        if (thief_list.size()>0) {
            for (int j = 0; j < thief_list.size(); j++) {
                for (int i = 0; i < bullet_position.size(); i++) {
                    if (bulletIntersectsEnemy(bullet_position.get(i), thief_list.get(j))) {
                        if (thief_list.contains(thief_list.get(j))) {
                            thief_list.remove(thief_list.get(j));
                        }
                        if (bullet_position.contains(bullet_position.get(i))) {
                            bullet_position.remove(bullet_position.get(i));
                        }
                        scores += 10;
                    }
                }
            }
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
//                            if (SniperEnemy.get(j).getSniper_number() == SniperBullets.get(t).getSniper_number()){
//                                SniperBullets.remove(SniperBullets.get(t));
//                            }
                            scores += 10;
                    }
                }
            }
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
                    player_x = wall.getPosition_Wall_x() - (wall.getWidth()+30);
                    player_y = wall.getPosition_Wall_y();
                }
            }
        }
        for (thief enemies:thief_list){
            if (playerIntersectEnemy(enemies)){
                player_x -=40;
                Health-=10;
            }
        }

        if (player_x >= 890){
            display_menu_winner = true;
        }
        if (Health <= 0){
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
        for (Menu menu:Menu_list){
            if (display_menu_winner == true && menu.get_menu_type() == "winner"){

                g2d.setColor(Color.GREEN);
                g2d.fillRect(menu.getPosition_menu_x(),menu.getPosition_menu_y(),menu.getWidth(),menu.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString("WINNER", 380, 110);
            }
            if (Health <= 0 && menu.get_menu_type() == "looser"){
                g2d.setColor(Color.RED);
                g2d.fillRect(menu.getPosition_menu_x(),menu.getPosition_menu_y(),menu.getWidth(),menu.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString("LOOSER", 380, 110);
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
        Walls.add(new Wall(710,250,230,20));
    }
    public void SniperCoordinates(){

        SniperEnemy.add(new SniperEnemy(Sniper_down,470,10,20,20,"Y",400,1));
//        SniperEnemy.add(new SniperEnemy(Sniper_up,650,320,20,20,"Y",10,2));
    }
    public void Enemy_coordinates(){
        thief_list.add(new thief(thief,200,70,30,30,"Y",320));
        thief_list.add(new thief(thief,390,165,30,30,"X",120));
        thief_list.add(new thief(thief,350,310,30,30,"Y",120));
//        enemy_list.add(new Enemy(thief,480,310,30,30,"Y",70));
        thief_list.add(new thief(thief,595,140,30,30,"X",450));
        thief_list.add(new thief(thief,580,10,30,30,"Y",180));
        thief_list.add(new thief(thief,665,50,30,30,"Y",210));
    }
    public boolean bulletIntersectsEnemy(Bullet bullet, Enemy enemy) {
        Rectangle bulletRect = new Rectangle(bullet.getPosition_x(), bullet.getPosition_y(),
                10, 10);
        Rectangle enemyRect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                30,30);

        return bulletRect.intersects(enemyRect);
    }
    public boolean SniperBulletIntersectsPlayer(SniperBullet bullet) {
        Rectangle playerRect = new Rectangle(player_x, player_y,
                30, 30);
        Rectangle SniperBullet = new Rectangle(bullet.getPosition_x(), bullet.getPosition_y(),
                10,10);

        return playerRect.intersects(SniperBullet);
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
        Rectangle playerRect = new Rectangle(player_x, player_y,
                30, 30);
        Rectangle enemyRect = new Rectangle(wall.getPosition_Wall_x(), wall.getPosition_Wall_y(),
                wall.getWidth(),wall.getHeight());

        return playerRect.intersects(enemyRect);
    }
    public boolean playerIntersectEnemy(Enemy enemy) {
        Rectangle playerRect = new Rectangle(player_x, player_y,
                30, 30);
        Rectangle enemyRect = new Rectangle(enemy.getPosition_enemy_x(), enemy.getPosition_enemy_y(),
                enemy.getWidth(),enemy.getHeight());

        return playerRect.intersects(enemyRect);
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            Bullet bullet = new Bullet(player_x, player_y);
            bullet_position.add(bullet);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            this.direction_player = "left";
            this.Player_icon = this.Player_L;
            this.player_x -=5;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.direction_player = "right";
            this.Player_icon = this.Player;
            this.player_x +=5;

        } else if (keyCode == KeyEvent.VK_UP) {
            this.direction_player = "up";
            this.Player_icon = this.Player_U;
            this.player_y -=5;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.direction_player = "down";
            this.Player_icon = this.Player_D;
            this.player_y +=5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
}
// this.setPreferredSize(new Dimension(500,500));