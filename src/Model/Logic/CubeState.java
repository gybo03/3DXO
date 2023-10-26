package Model.Logic;

import java.util.ArrayList;
import java.util.Objects;

import Model.Game.CubeCore;
import Model.Game.Player;


public class CubeState {
    private CubeState parent;
    private CubeCore cubeCore;
    private ArrayList<CubeState> branches;
    private int winner;

    public CubeState(CubeCore cubeCore, CubeState parent) {
        this.parent = parent;
        this.cubeCore = cubeCore;
        winner = 0;
        branches = new ArrayList<>();
    }

    public CubeState(CubeState copy) {
        this.parent = copy.parent;
        this.cubeCore = new CubeCore(copy);
        this.winner=copy.winner;
        this.branches = new ArrayList<>();
        for (CubeState branch : copy.branches) {
            this.branches.add(new CubeState(branch));
        }
    }

    public int getWinner() {
        return winner;
    }

    public void setParent(CubeState parent) {
        this.parent = parent;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public void setBranches(ArrayList<CubeState> branches) {
        this.branches = branches;
    }

    public CubeState getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeState cubeState = (CubeState) o;
        for (int i = 0; i < cubeCore.getOccupied().length; i++) {
            for (int j = 0; j < cubeCore.getOccupied().length; j++) {
                if(cubeCore.getOccupied()[i][j]!=cubeState.getCubeCore().getOccupied()[i][j]){
                    return false;
                }
            }
        }
        for (int i = 0; i < cubeCore.getCube().length; i++) {
            for (int j = 0; j < cubeCore.getCube().length; j++) {
                for (int k = 0; k < cubeCore.getCube().length; k++) {
                    if (cubeCore.getCube()[i][j][k] != cubeState.getCubeCore().getCube()[i][j][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, cubeCore, branches, winner);
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
