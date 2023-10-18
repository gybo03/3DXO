package UI;

import GameEngine.Cube;
import GameEngine.CubeCore;
import Logic.CubeState;
import Logic.CubeTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJPanel extends JPanel implements MouseListener {

    private Graphics2D g2d;

    private Cube cube;

    private int numOfPlayers;
    private int numOfColumns;
    private int sizeOfSquare;
    private int sizeOfLine;
    private int mouseY;
    private int mouseX;

    private int lastMouseY;
    private int lastMouseX;
    private CubeTree cubeTree;

    public MyJPanel(int numOfPlayers, int numOfColumns, int sizeOfSquare, int sizeOfline) {
        cube = new Cube(numOfColumns);
        Cube.numOfPlayers = numOfPlayers;
        this.numOfPlayers = numOfPlayers;
        this.numOfColumns = numOfColumns;
        this.sizeOfSquare = sizeOfSquare;
        this.sizeOfLine = sizeOfline;
        this.addMouseListener(this);
        cubeTree = new CubeTree(new CubeState(cube, null));

    }

    public void addLine(int x1, int y1, int width, int height) {
        g2d.fillRect(x1, y1, width, height);
    }

    public void drawBoard() {
        g2d.setColor(Color.BLACK);
        for (int i = 1; i <= numOfColumns - 1; i++) {
            addLine((sizeOfSquare) * i + sizeOfLine * (i - 1), 0, sizeOfLine, (sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1));
            addLine(0, (sizeOfSquare) * i + sizeOfLine * (i - 1), (sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1), sizeOfLine);
        }
    }

    private void whichSquareIsPressed(int x, int y) {
        lastMouseY = mouseY;
        lastMouseX = mouseX;
        for (int i = 0; i <= numOfColumns * (sizeOfSquare + sizeOfLine); i += sizeOfSquare + sizeOfLine) {
            if (x <= i) {
                mouseX = (i - (sizeOfSquare + sizeOfLine)) / (sizeOfSquare + sizeOfLine);
                break;
            }
        }
        for (int i = 0; i <= numOfColumns * (sizeOfSquare + sizeOfLine); i += sizeOfSquare + sizeOfLine) {
            if (y <= i) {
                mouseY = (i - (sizeOfSquare + sizeOfLine)) / (sizeOfSquare + sizeOfLine);
                break;
            }
        }
        //System.out.println(mouseX+" "+mouseY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        drawBoard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //whichSquareIsPressed(e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        whichSquareIsPressed(e.getX(), e.getY());
    }

    private void drawAMove(int i, int j) {
        int sizeOfPieceOfSquare = sizeOfSquare / numOfColumns;
        int leftOver = sizeOfSquare - sizeOfPieceOfSquare * numOfColumns;
        Graphics g = getGraphics();
        //System.out.println(cube.getTurn()%numOfPlayers);
        switch ((cube.getTurn() - 1) % numOfPlayers + 1) {
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.BLUE);
                break;
            case 3:
                g.setColor(Color.GREEN);
                break;
        }
        //System.out.println(i + " " + j);
        //System.out.println(i*100+" "+(numOfColumns-cube.getOccupied()[i][j])*sizeOfPieceOfSquare);
        if (cube.getOccupied()[i][j] == 1) {
            g.fillRect(i * 100, (numOfColumns - cube.getOccupied()[i][j]) * sizeOfPieceOfSquare + j * (sizeOfSquare + sizeOfLine), sizeOfSquare, sizeOfPieceOfSquare + leftOver);
        } else {
            g.fillRect(i * 100, (numOfColumns - cube.getOccupied()[i][j]) * sizeOfPieceOfSquare + j * (sizeOfSquare + sizeOfLine), sizeOfSquare, sizeOfPieceOfSquare);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        whichSquareIsPressed(e.getX(), e.getY());
        //System.out.println(e.getX() + " " + e.getY());
        if (cube.findWinner() == 0) {
            if (lastMouseX == mouseX && lastMouseY == mouseY && cube.getOccupied()[mouseX][mouseY] < numOfColumns) {
                cube.makeAMove(mouseY, mouseX);
                //System.out.println(cube.occupiedOutput());
                drawAMove(mouseX, mouseY);
                cubeTree.fillBranches(new CubeState(cube,cubeTree.getRoot()), cubeTree.getRoot(),6);
                int[] move = cubeTree.makeAMove(cubeTree.getRoot(), (Cube.turn % numOfPlayers) + 1);
                cube.makeAMove(move[0], move[1]);
                drawAMove(move[0], move[1]);

                //g2d.setColor(Color.RED);
                //System.out.println(g2d);


                //System.out.println(mouseY*100);
                //addLine(mouseY*100,(numOfColumns-cube.getOccupied()[mouseX][mouseY])*sizeOfPieceOfSquare,sizeOfSquare,sizeOfPieceOfSquare);
            }
        } else {
            System.out.println(cube.getWinner());
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
