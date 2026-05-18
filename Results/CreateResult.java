package Results;

import Enemy.thief;

import java.awt.*;
import java.util.ArrayList;
import Panel.MyPanel;
public final class CreateResult {
    public static ArrayList<Result_board> creating_result_board (){
        ArrayList<Result_board> Result_boards = new ArrayList<Result_board>();
        Result_boards.add(new Result_board(70,40,780,250,"winner"));
        Result_boards.add(new Result_board(70,40,780,250,"looser"));
        return Result_boards;
    }
    public static void GameLablesDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.setColor(Color.BLUE);
        String score = "Score : "+ MyPanel.scores;
        int x = 750;
        int y = 100;
        g.drawString(score, x, y);
        g.setColor(Color.RED);
        String health = "Health : " + MyPanel.Health +" %";
        g.drawString(health, 750, 150);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.GREEN);
        String exit = "Exit";
        g.drawString(exit, 890, 230);
    }
    public static void ResultBoardDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        FinalResult<String, Integer,Integer,String> result_win = new FinalResult<>("Mahdad", MyPanel.Health,MyPanel.scores,
                "winner");
        double double_health = MyPanel.Health;
        double double_score = MyPanel.scores;
        FinalResult<String, Double,Double,String> result_lose = new FinalResult<>("Mahdad", double_health,double_score,
                "looser");
        if (MyPanel.player.getPosition_x() >= 890){
            MyPanel.executing_game_timer.stop();
            MyPanel.Game_Timer.stop();
            MyPanel.display_menu_winner = true;
        }
        if (MyPanel.Health <= 0){
            MyPanel.executing_game_timer.stop();
            MyPanel.Game_Timer.stop();
            MyPanel.display_menu_winner = false;
        }
        for (Result_board Result_board:MyPanel.Result_boards){
            if (MyPanel.display_menu_winner == true && Result_board.get_result_board_type() == "winner"){
                g2d.setColor(Color.GREEN);
                g2d.fillRect(Result_board.getPosition_menu_x(),Result_board.getPosition_menu_y(),Result_board.getWidth(),Result_board.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString(result_win.getStatus(), 380, 110);
                g2d.drawString("The winner is : " + result_win.getName()+
                        " with the score of " + result_win.getScore()+
                        "and the Health of " + result_win.getHealth(), 80, 210);
                g2d.drawString(Result_board.get_num_enemy_time(MyPanel.number_enemy_killed , MyPanel.Seconds_Duration_Game), 80, 240);
            }
            if (MyPanel.Health <= 0 && Result_board.get_result_board_type() == "looser"){
                g2d.setColor(Color.RED);
                g2d.fillRect(Result_board.getPosition_menu_x(),Result_board.getPosition_menu_y(),Result_board.getWidth(),Result_board.getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 25));
                g2d.setColor(Color.WHITE);
                g2d.drawString(result_lose.getStatus(), 380, 110);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.drawString("The looser is : " + result_lose.getName()+
                        " with the score of " + result_lose.getScore()+
                        " and the Health of " + result_lose.getHealth(), 100, 210);
                g2d.drawString(Result_board.get_num_enemy_time((double) MyPanel.number_enemy_killed , (double) MyPanel.Seconds_Duration_Game), 100, 240);
            }

        }
    }

}
