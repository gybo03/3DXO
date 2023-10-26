package View;

import Controller.Recipient;
import Model.AppModel;

import javax.swing.*;
import java.awt.*;

public class WinnerFrame extends JFrame implements Recipient {
    private static WinnerFrame instance;
    private JPanel panel;
    private Label label;

    private WinnerFrame() {

    }

    private void initialize() {
        this.setTitle("3D X O");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(200, 100));
        panel = new JPanel();
        label = new Label();
        label.setFont(new Font("Serif", Font.BOLD, 30));
        panel.add(label);
        add(panel);
        pack();
        AppModel.getInstance().addRecipient(this);
    }

    public static WinnerFrame getInstance() {
        if (instance == null) {
            instance = new WinnerFrame();
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void update(Object that) {
        if (that instanceof AppModel) {
            if (((AppModel) that).getCubeTree().getRoot().getWinner() != 0) {
                label.setText("Player " + ((AppModel) that).getCubeTree().getRoot().getWinner() + " won!");
                label.setForeground(Color.white);
                label.setBackground(((AppModel) that).getCurrentPlayer().findPlayerWithId(((AppModel) that).getCubeTree().getRoot().getWinner()).getColor());
                panel.setBackground(((AppModel) that).getCurrentPlayer().findPlayerWithId(((AppModel) that).getCubeTree().getRoot().getWinner()).getColor());
                setVisible(true);
            }
        }
    }
}
