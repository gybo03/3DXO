package UI;

import GameEngine.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Scanner;

public class UserInterface {


    //trololol
    public static void run(int sizeOfSquare,int sizeOfLine,int numOfPlayers,int numOfColumns){
        JFrame jFrame=new JFrame("3DXO");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.BLACK);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(true);
        //jFrame.setSize((sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1)+6,(sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1)+28);



        MyJPanel jPanel=new MyJPanel(numOfColumns,sizeOfSquare,sizeOfLine);
        jPanel.setPreferredSize(new Dimension((sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1),(sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1)));
        jPanel.repaint();

        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);

        Cube game = new Cube(numOfColumns, numOfPlayers);
        //prvi
        game.addChip(0,0);
        //drugi
        game.addChip(2,0);
        //prvi
        game.addChip(1,0);
        //drugi
        game.addChip(2,2);
        //prvi
        game.addChip(3,0);
        //drugi
        game.addChip(2,1);
        //prvi
        game.addChip(2,0);
        //drugi
        game.addChip(1,2);
        //prvi
        game.addChip(2,0);
        //drugi
        game.addChip(2,3);

        System.out.println(game);
        /*Scanner scanner = new Scanner(System.in);
        int x, y;
        while (game.getWinner() == 0) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            game.addChip(x, y);
            System.out.println(game);
        }*/
        System.out.println(game.getWinner());
    }

}
