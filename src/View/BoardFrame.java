package View;

import Controller.Controller;
import Controller.Recipient;
import Model.AppModel;
import Model.Logic.CubeState;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame implements Recipient {
    private static BoardFrame instance;

    private JPanel mainPanel;

    private JPanel[][] panelMatrix;

    private int numOfColumns = 3;

    private BoardFrame() {

    }

    private void initialize() {
        this.setTitle("3D X O");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //later change it to get value from a MenuFrame and to be resizable only from diaogonals
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //change later to get value from a MenuFrame
        int numOfColumns = 3;
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setBackground(Color.GRAY);
        mainPanel.addMouseListener(new Controller());
        panelMatrix = new JPanel[numOfColumns][numOfColumns];
        mainPanel.setLayout(new GridLayout(numOfColumns, numOfColumns));
        for (int i = 0; i < numOfColumns; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                panelMatrix[i][j] = new JPanel();
                panelMatrix[i][j].setOpaque(false);
                panelMatrix[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panelMatrix[i][j].setVisible(true);
                panelMatrix[i][j].setPreferredSize(new Dimension(this.getWidth() / numOfColumns, this.getHeight() / numOfColumns));
                //there will be offset here for border
                panelMatrix[i][j].setLocation(i * this.getWidth() / numOfColumns, j * this.getHeight() / numOfColumns);
                //Y_AXIS is vertical
                panelMatrix[i][j].setLayout(new BoxLayout(panelMatrix[i][j], BoxLayout.Y_AXIS));
                //add 3 panels to each panel
                for (int k = 0; k < numOfColumns; k++) {
                    JPanel miniPanel = new JPanel();
                    miniPanel.setBackground(Color.GRAY);
                    miniPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelMatrix[i][j].add(miniPanel);
                }
                //panelMatrix[i][j].addMouseListener(new Controller(panelMatrix[i][j]));
                //add panel to frame
                mainPanel.add(panelMatrix[i][j]);
            }
        }
        mainPanel.repaint();
        this.add(mainPanel);
        this.pack();
        AppModel.getInstance().addRecipient(this);
    }

    public static BoardFrame getInstance() {
        if (instance == null) {
            instance = new BoardFrame();
            instance.initialize();
        }
        return instance;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void update(Object that) {
        if (that instanceof AppModel) {
            CubeState root = ((AppModel) that).getRoot();
            //if the root and his parent are different than paint that component
            for (int i = 0; i < root.getCubeCore().getCube().length; i++) {
                for (int j = 0; j < root.getCubeCore().getCube().length; j++) {
                    if (root.getCubeCore().getOccupied()[i][j] != root.getParent().getCubeCore().getOccupied()[i][j] && root.getCubeCore().getOccupied()[i][j] != numOfColumns - root.getCubeCore().getOccupied()[i][j]) {
                        panelMatrix[j][i].getComponents()[numOfColumns - root.getCubeCore().getOccupied()[i][j]].setBackground(AppModel.getInstance().getCurrentPlayer().getColor());
                    }
                }
            }
        }
    }
}
