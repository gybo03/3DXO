package UI;

import GameEngine.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Scanner;

public class UserInterface {


    //trololol
    public static void run(int size,int numOfPlayers,int numOfColumns){
        JFrame jFrame=new JFrame("3DXO");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.BLACK);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setSize(size,size);



        MyJPanel jPanel=new MyJPanel(numOfColumns,90,10);
        jPanel.repaint();

        jFrame.add(jPanel);
        jFrame.setVisible(true);

        Cube game = new Cube(size, numOfPlayers);
        Scanner scanner = new Scanner(System.in);
        int x, y;
        while (game.getWinner() == 0) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            game.addChip(x, y);
            System.out.println(game);
        }
        System.out.println(game.getWinner());
    }

}
