package Model.Logic;


import Model.AppModel;
import Model.Game.CubeCore;
import Model.Game.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CubeTree {
    private CubeState root;

    public CubeTree(CubeState root) {
        this.root = root;
        this.root.setParent(new CubeState(new CubeCore(root.getCubeCore().getOccupied().length), null));
    }

    public CubeState getRoot() {
        return root;
    }

    public void eraseBranches(CubeState root) {
        root.setBranches(new ArrayList<>());
    }

    //this func fills the tree with all the possible moves to some depth
    public void fillBranches(CubeState root, CubeState state, int depth) {
        if (depth == 0) {
            return;
        }
        for (int i = 0; i < state.getCubeCore().getOccupied().length; i++) {
            for (int j = 0; j < state.getCubeCore().getOccupied().length; j++) {
                if (state.getCubeCore().getOccupied()[i][j] < state.getCubeCore().getOccupied().length) {
                    //creates a new cubeCore with the same values as the current cubeCore
                    CubeCore newCubeCore = new CubeCore(state);
                    //plays a move on the new cubeCore
                    newCubeCore.playAMove(i, j);
                    //creates a new cubeState with the new cubeCore
                    CubeState newCubeState = new CubeState(newCubeCore, state);
                    //sets the winner of the new cubeState
                    newCubeState.setWinner(newCubeCore.findWinner());
                    //adds the new cubeState to the branches of the current cubeState
                    if (state.equals(root)) {
                        root.addState(newCubeState);
                    } else {
                        state.addState(newCubeState);
                    }

                }
            }
        }
        for (CubeState cubeState : state.getBranches()) {
            //if the current cubeState is not a winning state than it fills its branches
            if (cubeState.getWinner() == 0) {
                AppModel.getInstance().nextPlayer();
                fillBranches(root, cubeState, depth - 1);
            }
        }

    }

    //this func is bfs alg on a tree that return path from root to destination
    public static ArrayList<CubeState> findNthWinningPath(CubeState root, int player, int n) {
        if (root == null) {
            return null;
        }
        Queue<CubeState> queue = new LinkedList<>();
        ArrayList<CubeState> winningPath = new ArrayList<>();
        queue.add(root);

        while (n > 0) {

            if (queue.peek().getWinner() == player) {
                if (winningPath.isEmpty()) {
                    winningPath.add(queue.peek());
                } else {
                    winningPath.set(0, queue.peek());
                }
                n--;
            }
            if (!queue.peek().getBranches().isEmpty()) {
                queue.addAll(queue.peek().getBranches());
            }
            queue.poll();
        }

        return findPathBack(winningPath, root);
    }

    private static ArrayList<CubeState> findPathBack(ArrayList<CubeState> path, CubeState root) {
        CubeState temp = path.get(0);
        while (!path.contains(root)) {
            path.add(temp.getParent());
            temp = temp.getParent();
        }
        return path;
    }

    //this func return coords of the move that the AI should play
    public int[] makeAMove(CubeState root, int player) {
        ArrayList<CubeState> winningPaths = findNthWinningPath(root, player, 1);
        ArrayList<CubeState> losingPaths = findLosingPath(root, player);
        //see if ai will lose before it can win
        if (losingPaths.size()>winningPaths.size()) {
            return CubeCore.whatMoveWasPlayed(winningPaths.get(winningPaths.size() - 2).getCubeCore(), winningPaths.get(winningPaths.size() - 1).getCubeCore());
        } else {
            return CubeCore.whatMoveWasPlayed(losingPaths.get(losingPaths.size()-2).getCubeCore(), losingPaths.get(losingPaths.size()-1).getCubeCore());
        }

    }

    private ArrayList<CubeState> findLosingPath(CubeState root, int player) {
        ArrayList<CubeState> path=new ArrayList<>();
        int closestLoss=5;
        path.add(root);
        Player player1=AppModel.getInstance().getCurrentPlayer().findPlayerWithId(player);
        Player temp=player1.getNextPlayer();
        while(temp.getId()!=player1.getId()){
            if(findNthWinningPath(root,temp.getId(),1).size()<closestLoss){
                closestLoss=findNthWinningPath(root,temp.getId(),1).size();
                if(!path.isEmpty()){
                    path.clear();
                }
                path.addAll(findNthWinningPath(root,temp.getId(),1));
            }
            temp=temp.getNextPlayer();
        }
        return path;
    }

    public void setRoot(CubeState root) {
        this.root = root;
    }
}
