package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

public class MyJPanel extends JPanel implements MouseListener {

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

    public void drawBoard(Graphics2D g2d, int numOfColumns, int sizeOfSquere, int sizeOfLine) {
        g2d.setColor(Color.BLACK);
        for (int i = 1; i <= numOfColumns - 1; i++) {
            addLine(g2d, (sizeOfSquare) * i + sizeOfLine * (i - 1), 0, sizeOfLine, (sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1));
            addLine(g2d, 0, (sizeOfSquare) * i + sizeOfLine * (i - 1), (sizeOfSquare) * numOfColumns + sizeOfLine * (numOfColumns - 1), sizeOfLine);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        drawBoard(g2d, numOfColumns, sizeOfSquare, sizeOfLine);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
