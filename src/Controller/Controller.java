package Controller;

import Model.AppModel;
import View.BoardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {
    int numOfColumns = 3;

    public Controller() {
    }

    private int iCoord(int x) {
        for (int i = 0; i < BoardFrame.getInstance().getMainPanel().getSize().height; i += BoardFrame.getInstance().getMainPanel().getSize().height / numOfColumns) {
            if (x < i) {
                return (i - BoardFrame.getInstance().getMainPanel().getSize().height / numOfColumns) / (BoardFrame.getInstance().getMainPanel().getSize().height / numOfColumns);
            }
        }
        return 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        AppModel.getInstance().playAMove(iCoord(e.getX()), iCoord(e.getY()));
        while (AppModel.getInstance().getCurrentPlayer().isItAi()) {
            //if its an Ai then it does not need i or j
            AppModel.getInstance().playAMove(0, 0);
        }
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
