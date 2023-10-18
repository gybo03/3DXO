package Logic;

import java.util.ArrayList;
import GameEngine.Cube;
import GameEngine.CubeCore;

public class CubeState {
    private CubeState parent;
    private CubeCore cubeCore;
    private ArrayList<CubeState> branches;

    private int  winner;

    public CubeState(CubeCore cubeCore, CubeState parent) {
        this.parent = parent;
        this.cubeCore = cubeCore;
        winner=0;
        branches = new ArrayList<>();
    }

    public int getWinner() {
        return winner;
    }


    public CubeState getParent() {
        return parent;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public CubeCore getCubeCore() {
        return cubeCore;
    }

    public void addState(CubeState state) {
        branches.add(state);
    }
    public ArrayList<CubeState> getBranches() {
        return branches;
    }
}
