package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class MyJPanel extends JPanel {

    private int numOfColumns;
    private int sizeOfSquare;
    private int sizeOfLine;

    public MyJPanel(int numOfColumns, int sizeOfSquare, int sizeOfline) {
        this.numOfColumns = numOfColumns;
        this.sizeOfSquare = sizeOfSquare;
        this.sizeOfLine = sizeOfline;
    }

    public void addLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.fillRect(x1, y1, x2, y2);
    }

    public void drawBoard(Graphics2D g2d, int numOfColumns,int sizeOfSquere,int sizeOfLine){
        for (int i = 1; i <= numOfColumns-1; i++) {
            addLine(g2d,sizeOfSquere*i,0,sizeOfLine,sizeOfSquere*numOfColumns);
            addLine(g2d, 0, sizeOfSquare * i, sizeOfSquare * numOfColumns, sizeOfLine);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        drawBoard(g2d,numOfColumns,sizeOfSquare,sizeOfLine);
    }
}
