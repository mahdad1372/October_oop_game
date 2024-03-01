import javax.swing.*;
public class Main{
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        MyPanel board = new MyPanel();
        obj.setBounds(10, 10, 950, 400);
        obj.setTitle("Pacman");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        obj.add(board);
    }
}