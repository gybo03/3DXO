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
        jFrame.setResizable(false);

        MyJPanel jPanel=new MyJPanel(numOfPlayers,numOfColumns,sizeOfSquare,sizeOfLine);
        jPanel.setPreferredSize(new Dimension((sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1),(sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1)));
        jPanel.repaint();

        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);

    }

}
