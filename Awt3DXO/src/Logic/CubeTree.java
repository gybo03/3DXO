package Logic;

import GameEngine.Cube;
import GameEngine.CubeCore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CubeTree {
    private CubeState root;

    public CubeTree(CubeState root) {
        this.root = root;
    }

    public CubeState getRoot() {
        return root;
    }

    //this func fills the tree with all the possible moves to some depth
    public void fillBranches(CubeState state, int depth) {
        if (depth==0){
            return;
        }
        if (state.getBranches().isEmpty()) {
            for (int i = 0; i < state.getCubeCore().getOccupied().length; i++) {
                for (int j = 0; j < state.getCubeCore().getOccupied().length; j++) {
                    if (state.getCubeCore().getOccupied()[i][j] < state.getCubeCore().getOccupied().length) {
                        //creates a new cubeCore with the same values as the current cubeCore
                        CubeCore newCubeCore = new CubeCore(state.getCubeCore().getCube(), state.getCubeCore().getOccupied());
                        //sets the turn of the new cubeCore
                        newCubeCore.setTurn(state.getCubeCore().getTurn());
                        //plays a move on the new cubeCore
                        newCubeCore.playAMove(i, j);
                        //creates a new cubeState with the new cubeCore
                        CubeState newCubeState = new CubeState(newCubeCore,state);
                        //sets the winner of the new cubeState
                        newCubeState.setWinner(newCubeCore.findWinner());
                        //adds the new cubeState to the branches of the current cubeState
                        state.addState(newCubeState);
                    }
                }
            }
        }
        for (CubeState cubeState : state.getBranches()) {
            //if the current cubeState is not a winning state than it fills its branches
            if(cubeState.getWinner()==0){
                fillBranches(cubeState, depth-1);
            }
        }

    }
    //this func is bfs alg on a tree that return path from root to destination
    public static ArrayList<CubeState> findNthWinningPath(CubeState root,int player,int n) {
        if (root == null) {
            return null;
        }
        Queue<CubeState> queue=new LinkedList<>();
        ArrayList<CubeState> winningPath =new ArrayList<>();
        queue.add(root);

        while(n>0){
            if(queue.peek().getWinner()==player){
                if (winningPath.isEmpty()){
                    winningPath.add(queue.peek());
                }else{
                    winningPath.set(0,queue.peek());
                }
                n--;
            }
            queue.addAll(queue.peek().getBranches());
            queue.poll();
        }
        CubeState temp= winningPath.get(0);
        while(!winningPath.contains(root)){
            winningPath.add(temp.getParent());
            temp=temp.getParent();
        }
        return winningPath;
    }
    //this func return coords of the move that the AI should play
    public int[] makeAMove(CubeState root,int player){
        ArrayList<CubeState> winningPaths=findNthWinningPath(root,player,1);
        return Cube.whatMoveWasPlayed(winningPaths.get(winningPaths.size()-1).getCubeCore(),winningPaths.get(winningPaths.size()).getCubeCore());
    }

    public void setRoot(CubeState root) {
        this.root = root;
    }
}
