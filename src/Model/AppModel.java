package Model;

import Controller.Sender;
import Model.Game.CubeCore;
import Model.Game.Player;
import Model.Logic.CubeState;
import Model.Logic.CubeTree;
import View.BoardFrame;
import View.WinnerFrame;

import java.awt.*;


public class AppModel extends Sender {
    private static AppModel instance;
    private CubeTree cubeTree;
    private Player currentPlayer;

    private AppModel() {
    }

    private void initialize() {
        currentPlayer = new Player(1, false, null, Color.RED);
        Player player2 = new Player(2, true, currentPlayer, Color.BLUE);
        //Player player3=new Player(3,true,currentPlayer,Color.GREEN);
        currentPlayer.setNextPlayer(player2);
        //player2.setNextPlayer(player3);
        //later change numOfColumns to get value from a MenuFrame
        cubeTree = new CubeTree(new CubeState(new CubeCore(4), null));
    }

    public void nextPlayer() {
        currentPlayer = currentPlayer.getNextPlayer();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void playAMove(int i, int j) {
        //saves the current state of the cubeTree
        CubeState temp1 = new CubeState(cubeTree.getRoot());
        if (currentPlayer.isItAi()) {
            //saves the current player because the fillBranches changes the current player
            Player temp = currentPlayer;

            cubeTree.eraseBranches(cubeTree.getRoot());

            //change depth later to get value from a MenuFrame

            cubeTree.fillBranches(cubeTree.getRoot(), cubeTree.getRoot(), 4,currentPlayer);

            //reverts to original current player after generating the tree
            currentPlayer = temp;

            //plays the move that the AI chose
            cubeTree.getRoot().getCubeCore().playAMove(cubeTree.makeAMove(cubeTree.getRoot(), currentPlayer.getId())[0], cubeTree.makeAMove(cubeTree.getRoot(), currentPlayer.getId())[1],currentPlayer.getId());

            //connects the next move to the last when AIs move
            cubeTree.getRoot().setParent(temp1);
        } else {
            //erases the last tree because there is a new root in town :)
            cubeTree.eraseBranches(cubeTree.getRoot());

            //plays the move that the player chose
            cubeTree.getRoot().getCubeCore().playAMove(i, j,currentPlayer.getId());

            //maybe player won check here
            cubeTree.getRoot().setWinner(cubeTree.getRoot().getCubeCore().findWinner(1));

            //connects the next move to the last when players move
            cubeTree.getRoot().setParent(temp1);
        }

        //if something changed then it notifies the recipient
        if (!temp1.equals(cubeTree.getRoot())) {
            //first notyfies the recipient and then changes the player
            notifyRecipient(this);

            //changes the player
            nextPlayer();
        }
    }
    public CubeState getRoot() {
        return cubeTree.getRoot();
    }

    public CubeTree getCubeTree() {
        return cubeTree;
    }


    public static AppModel getInstance() {
        if (instance == null) {
            instance = new AppModel();
            instance.initialize();
        }
        return instance;
    }

}
