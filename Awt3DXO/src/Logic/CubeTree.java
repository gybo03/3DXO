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

    public void fillBranches(CubeState state,CubeState parent, int depth) {
        if (depth==0){
            return;
        }
        if (state.getBranches().isEmpty()) {
            for (int i = 0; i < state.getCubeCore().getOccupied().length; i++) {
                for (int j = 0; j < state.getCubeCore().getOccupied().length; j++) {
                    if (state.getCubeCore().getOccupied()[i][j] < state.getCubeCore().getOccupied().length) {
                        CubeCore newCubeCore = new CubeCore(state.getCubeCore().getOccupied().length);
                        newCubeCore.setCube(state.getCubeCore().getCube());
                        newCubeCore.setOccupied(state.getCubeCore().getOccupied());
                        newCubeCore.playAMove(i, j, (Cube.turn % Cube.numOfPlayers) + 1);
                        CubeState newCubeState = new CubeState(newCubeCore,parent);
                        newCubeState.setWinner(newCubeCore.findWinner());
                        state.addState(newCubeState);
                    }
                }
            }
        }
        for (CubeState cubeState : state.getBranches()) {
            //System.out.println(depth);
            int newDepth=depth-1;
            fillBranches(cubeState,state, newDepth);
        }

    }
    public static ArrayList<CubeState> findNthWinningPath(CubeState root,int player,int n) {
        if (root == null) {
            return null;
        }
        Queue<CubeState> queue=new LinkedList<>();
        ArrayList<CubeState> winningPaths=new ArrayList<>();
        queue.add(root);

        while(n>0){
            if(queue.peek().getWinner()==player){
                if (winningPaths.isEmpty()){
                    winningPaths.add(queue.peek());
                }else{
                    winningPaths.set(0,queue.peek());
                }
                n--;
            }
            queue.addAll(queue.peek().getBranches());
            queue.poll();
        }
        CubeState temp=winningPaths.get(0);
        while(!winningPaths.contains(root)){
            winningPaths.add(temp.getParent());
            temp=temp.getParent();
        }
        return winningPaths;
    }

    public int[] makeAMove(CubeState root,int player){
        ArrayList<CubeState> winningPaths=findNthWinningPath(root,player,1);
        return Cube.whatMoveWasPlayed(winningPaths.get(winningPaths.size()-1).getCubeCore(),winningPaths.get(winningPaths.size()).getCubeCore());
    }

    public void setRoot(CubeState root) {
        this.root = root;
    }
}
