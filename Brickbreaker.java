import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import game.Gameplay;
public class Brickbreaker {
    public static void main(String[] args) {
        JFrame f=new JFrame("Brick Breaker");
        f.setSize(700,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        Gameplay aa=new Gameplay();
        f.add(aa);
        f.setVisible(true);
    }
}