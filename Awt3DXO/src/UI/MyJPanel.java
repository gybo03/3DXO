package UI;

import GameEngine.Cube;

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

    public MyJPanel(int numOfPlayers, int numOfColumns, int sizeOfSquare, int sizeOfline) {
        cube = new Cube(numOfColumns, numOfPlayers);
        this.numOfPlayers = numOfPlayers;
        this.numOfColumns = numOfColumns;
        this.sizeOfSquare = sizeOfSquare;
        this.sizeOfLine = sizeOfline;
        this.addMouseListener(this);
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

    @Override
    public void mouseReleased(MouseEvent e) {
        whichSquareIsPressed(e.getX(), e.getY());
        //System.out.println(e.getX() + " " + e.getY());
        if(cube.findWinner()==0){
            if (lastMouseX == mouseX && lastMouseY == mouseY && cube.getOccupied()[mouseX][mouseY] < numOfColumns) {
                cube.addChip(mouseY, mouseX);
                System.out.println(cube.occupiedOutput());
                int sizeOfPieceOfSquare = sizeOfSquare / numOfColumns;
                int leftOver = sizeOfSquare - sizeOfPieceOfSquare * numOfColumns;
                Graphics g = getGraphics();
                //System.out.println(cube.getTurn()%numOfPlayers);
                switch ((cube.getTurn() - 1) % numOfPlayers) {
                    case 0:
                        g.setColor(Color.RED);
                        break;
                    case 1:
                        g.setColor(Color.BLUE);
                        break;
                    case 2:
                        g.setColor(Color.GREEN);
                        break;
                }

                //System.out.println(mouseX + " " + mouseY);
                System.out.println(mouseX*100+" "+(numOfColumns-cube.getOccupied()[mouseX][mouseY])*sizeOfPieceOfSquare);
                if (cube.getOccupied()[mouseX][mouseY] == 1) {
                    g.fillRect(mouseX * 100, (numOfColumns - cube.getOccupied()[mouseX][mouseY]) * sizeOfPieceOfSquare+mouseY*(sizeOfSquare + sizeOfLine), sizeOfSquare, sizeOfPieceOfSquare + leftOver);
                } else {
                    g.fillRect(mouseX * 100, (numOfColumns - cube.getOccupied()[mouseX][mouseY]) * sizeOfPieceOfSquare+mouseY*(sizeOfSquare + sizeOfLine), sizeOfSquare, sizeOfPieceOfSquare);
                }
                //g2d.setColor(Color.RED);
                //System.out.println(g2d);


                //System.out.println(mouseY*100);
                //addLine(mouseY*100,(numOfColumns-cube.getOccupied()[mouseX][mouseY])*sizeOfPieceOfSquare,sizeOfSquare,sizeOfPieceOfSquare);
            }
        }else{
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
