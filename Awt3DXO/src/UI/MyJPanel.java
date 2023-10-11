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
    private int mouseX;
    private int mouseY;

    private int lastMouseX;
    private int lastMouseY;

    public MyJPanel(int numOfPlayers,int numOfColumns, int sizeOfSquare, int sizeOfline) {
        cube=new Cube(numOfColumns,numOfPlayers);
        this.numOfPlayers=numOfPlayers;
        this.numOfColumns = numOfColumns;
        this.sizeOfSquare = sizeOfSquare;
        this.sizeOfLine = sizeOfline;
        this.addMouseListener(this);
    }

    public void addLine(int x1, int y1, int width, int height) {
        g2d.fillRect(x1, y1, width, height);
        System.out.println("radi retarde");
    }

    public void drawBoard() {
        g2d.setColor(Color.BLACK);
        for (int i = 1; i <= numOfColumns - 1; i++) {
            addLine((sizeOfSquare) * i + sizeOfLine * (i - 1), 0, sizeOfLine, (sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1));
            addLine( 0, (sizeOfSquare) * i + sizeOfLine * (i - 1), (sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1), sizeOfLine);
        }
    }

    private void whichSquareIsPressed(int x,int y){
        lastMouseX=mouseX;
        lastMouseY=mouseY;
        for (int i = 0; i <= numOfColumns*(sizeOfSquare+sizeOfLine); i+=sizeOfSquare+sizeOfLine) {
            if(x<=i){
                mouseY=(i-100)/100;
                break;
            }
        }
        for (int i = 0; i <= numOfColumns*(sizeOfSquare+sizeOfLine); i+=sizeOfSquare+sizeOfLine) {
            if(y<=i){
                mouseX=(i-100)/100;
                break;
            }
        }
        System.out.println(mouseX+" "+mouseY);
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
        whichSquareIsPressed(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        whichSquareIsPressed(e.getX(),e.getY());
        if(lastMouseY==mouseY&&lastMouseX==mouseX&&cube.getOccupied()[mouseX][mouseY]<numOfColumns){
            cube.addChip(mouseX,mouseY);
            int sizeOfPieceOfSquare=sizeOfSquare/numOfColumns;
            //g2d.setColor(Color.RED);
            //System.out.println(g2d);
            Graphics g=getGraphics();
            g.fillRect(mouseY*100,(numOfColumns-cube.getOccupied()[mouseX][mouseY])*sizeOfPieceOfSquare,sizeOfSquare,sizeOfPieceOfSquare);
            System.out.println(mouseY*100);
            addLine(mouseY*100,(numOfColumns-cube.getOccupied()[mouseX][mouseY])*sizeOfPieceOfSquare,sizeOfSquare,sizeOfPieceOfSquare);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
