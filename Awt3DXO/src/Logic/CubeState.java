package Logic;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeState cubeState = (CubeState) o;
        return winner == cubeState.winner &&  Objects.equals(cubeCore, cubeState.cubeCore) && Objects.equals(branches, cubeState.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, cubeCore, branches, winner);
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
