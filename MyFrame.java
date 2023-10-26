import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class MyFrame extends JFrame implements KeyListener {
//    JLabel label;
    Player player = new Player(0,0,"soldier.png");
//    ArrayList<Maze> Maze_List = new ArrayList<Maze>();
    ArrayList<Enemy> Enemy_List = new ArrayList<Enemy>();
    ImageIcon icon = new ImageIcon("thief.png");
    MyPanel panel;
    MyFrame(){
        panel = new MyPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Good boy");
    }
//    private void drawing_maze(){
//        Maze_List.add(new Maze(20,80,40,40));
//        Maze_List.add(new Maze(150,200,40,150));
//        for (Maze maze:Maze_List){
//            JLabel label = new JLabel();
//            label.setBounds(maze.getPosition_maze_x(),
//                    maze.getPosition_maze_y(),maze.getWidth()
//            ,maze.getHeight());
//            label.setBackground(Color.RED);
//            label.setOpaque(true);
//            this.add(label);
//            this.setVisible(true);
//        }
//    }
    private void draw_enemy(){
//        Enemy_List.add(new Enemy(icon,150,50,50,50,"X",280));
//        Enemy_List.add(new Enemy(icon,300,80,50,50,"Y",280));
//        SniperEnemy sniper = new SniperEnemy(icon,400,50,50,50,"X",280);
        for (Enemy enemy:Enemy_List){
            JLabel label = new JLabel();
            label.setBounds(enemy.getPosition_enemy_x(),
                    enemy.getPosition_enemy_y(),enemy.getWidth()
                    ,enemy.getHeight());
            label.setIcon(icon);
            this.add(label);
            this.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    enemy.enemy_movement(enemy.getPosition_enemy_x(),enemy.getFinal_position(),"X");
                    label.setBounds(enemy.getPosition_enemy_x(),
                            enemy.getPosition_enemy_y(),enemy.getWidth()
                            ,enemy.getHeight());
//                    sniper.create_sniper_bullet();
                }
            };

            // Schedule the task to run every 3 seconds (3000 milliseconds)
            timer.schedule(task, 0, 16);

//            this.add(sniper.sniper_bullet());
//            this.setVisible(true);
//            enemy.enemy_movement();
        }
    }
    private void Lable_Demo(){
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(player.getPosition_x());

            }
        };

        // Schedule the task to run every 3 seconds (3000 milliseconds)
        timer.schedule(task, 0, 1);

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 37:
                player.player_movement("Left");
            break;
            case 38:
                player.player_movement("Down");
            break;
            case 39:
                player.player_movement("Right");
            break;
            case 40:
                player.player_movement("Up");
            break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
