package Model.Game;

import Model.AppModel;
import Model.Logic.CubeState;

import java.util.LinkedList;

public class CubeCore {

    private int[][][] cube;
    private int[][] occupied;
    public CubeCore(int numOfColumns) {
        cube = new int[numOfColumns][numOfColumns][numOfColumns];
        occupied = new int[numOfColumns][numOfColumns];
    }

    public CubeCore(CubeState cubeState) {
        this.cube = deepCopyCube(cubeState.getCubeCore().getCube());
        this.occupied = deepCopyOccupied(cubeState.getCubeCore().getOccupied());
    }
    public void playAMove(int i, int j,int player) {
        gravityMechanic(i, j,player);
    }
    private void gravityMechanic(int i, int j,int player) {
        if (occupied[i][j] < cube.length) {
            int k = occupied[i][j];
            occupied[i][j]++;
            //System.out.println(k+" "+j+" "+i);
            cube[j][i][k] = player;
        }
    }

    public void setCube(int[][][] cube) {
        this.cube = cube;
    }

    public void setOccupied(int[][] occupied) {
        this.occupied = occupied;
    }

    public static int[] whatMoveWasPlayed(CubeCore cubeCore1, CubeCore cubeCore2) {
        int[] move = new int[2];
        for (int i = 0; i < cubeCore1.getOccupied().length; i++) {
            for (int j = 0; j < cubeCore1.getOccupied().length; j++) {
                if (cubeCore1.getOccupied()[i][j] != cubeCore2.getOccupied()[i][j]) {
                    move[0] = i;
                    move[1] = j;
                    return move;
                }
            }
        }
        return null;
    }

    public int findWinner() {
        int combination = 1;
        int winner = 0;


        //<editor-fold desc="straight lines">
        /*
            {0, 0, 0}
            {1, 1, 1}
            {0, 0, 0}

            {0, 0, 0}
            {2, 2, 0}
            {0, 0, 0}

            {0, 0, 0}
            {0, 0, 0}
            {0, 0, 0}
        */

        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length; j++) {
                for (int k = 0; k < cube.length - 1; k++) {
                    if (cube[j][i][k] != 0 && cube[j][i][k + 1] != 0 && cube[j][i][k] == cube[j][i][k + 1]) {
                        combination++;
                        winner = cube[j][i][k];
                        if (combination == cube.length) {
                            return winner;
                        }
                    } else {
                        winner = 0;
                        combination = 1;
                        break;
                    }
                }
                for (int k = 0; k < cube.length - 1; k++) {
                    if (cube[i][k][j] != 0 && cube[i][k + 1][j] != 0 && cube[i][k][j] == cube[i][k + 1][j]) {
                        combination++;
                        winner = cube[i][k][j];
                        if (combination == cube.length) {
                            return winner;
                        }
                    } else {
                        winner = 0;
                        combination = 1;
                        break;
                    }
                }
                for (int k = 0; k < cube.length - 1; k++) {
                    if (cube[k][i][j] != 0 && cube[k + 1][i][j] != 0 && cube[k][i][j] == cube[k + 1][i][j]) {
                        combination++;
                        winner = cube[k][i][j];
                        if (combination == cube.length) {
                            return winner;
                        }
                    } else {
                        winner = 0;
                        combination = 1;
                        break;
                    }
                }
            }
        }
        //</editor-fold>


        //<editor-fold desc="xy">
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length-1; j++) {
                if(cube[i][j][j]!=0&&cube[i][j][j]!=0&&cube[i][j][j]==cube[i][j+1][j+1]){
                    combination++;
                    winner=cube[i][j][j];
                    if(combination== cube.length){
                        return winner;
                    }
                }else{
                    combination=1;
                    winner=0;
                    break;
                }
            }
            for (int j = cube.length-1; j > 0; j--) {
                if(cube[i][j][j]!=0&&cube[i][j-1][j-1]!=0&&cube[i][j][j]==cube[i][j-1][j-1]){
                    combination++;
                    winner=cube[i][j][j];
                    if(combination== cube.length){
                        return winner;
                    }
                }else{
                    combination=1;
                    winner=0;
                    break;
                }
            }
            combination=1;
            winner=0;
        }
        //</editor-fold>

        //<editor-fold desc="yz">
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length - 1; j++) {
                if (cube[j][j][i] != 0 && cube[j + 1][j + 1][i] != 0 && cube[j][j][i] == cube[j + 1][j + 1][i]) {
                    combination++;
                    winner = cube[j][j][i];
                    if (combination == cube.length) {
                        return winner;
                    }
                } else {
                    winner = 0;
                    combination = 1;
                    break;
                }
            }
            for (int j = cube.length - 1; j > 0; j--) {
                if (cube[j][j][i] != 0 && cube[j - 1][j - 1][i] != 0 && cube[j][j][i] == cube[j - 1][j - 1][i]) {
                    combination++;
                    winner = cube[j][j][i];
                    if (combination == cube.length) {
                        return winner;
                    }
                } else {
                    winner = 0;
                    combination = 1;
                    break;
                }
            }
            winner=0;
            combination=1;
        }
        //</editor-fold>

        //<editor-fold desc="zx">
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length - 1; j++) {
                if (cube[j][i][j] != 0 && cube[j + 1][i][j + 1] != 0 && cube[j][i][j] == cube[j + 1][i][j + 1]) {
                    combination++;
                    winner = cube[j][i][j];
                    if (combination == cube.length) {
                        return winner;
                    }
                } else {
                    winner = 0;
                    combination = 1;
                    break;
                }
            }
            for (int j = cube.length - 1; j > 0; j--) {
                if (cube[j][i][j] != 0 && cube[j - 1][i][j - 1] != 0 && cube[j][i][j] == cube[j - 1][i][j - 1]) {
                    combination++;
                    winner = cube[j][i][j];
                    if (combination == cube.length) {
                        return winner;
                    }
                } else {
                    winner = 0;
                    combination = 1;
                    break;
                }
            }
            winner=0;
            combination=1;
        }
        //</editor-fold>

        //<editor-fold desc="3dD">
        for (int x = 0; x < cube.length - 1; x++) {
            if (cube[x][x][x] != 0 && cube[x + 1][x + 1][x + 1] != 0 && cube[x][x][x] == cube[x + 1][x + 1][x + 1]) {
                combination++;
                winner = cube[x][x][x];
                if (combination == cube.length) {
                    return winner;
                }
            } else {
                winner = 0;
                combination = 1;
                break;
            }
        }
        for (int x = 0; x < cube.length - 1; x++) {
            if (cube[cube.length - x - 1][x][x] != 0 && cube[cube.length - x - 2][x + 1][x + 1] != 0 && cube[cube.length - x - 1][x][x] == cube[cube.length - x - 2][x + 1][x + 1]) {
                combination++;
                winner = cube[cube.length - x - 1][x][x];
                if (combination == cube.length) {
                    return winner;
                }
            } else {
                winner = 0;
                combination = 1;
                break;
            }
        }
        for (int x = 0; x < cube.length - 1; x++) {
            if (cube[x][x][cube.length - x - 1] != 0 && cube[x + 1][x + 1][cube.length - x - 2] != 0 && cube[x][x][cube.length - x - 1] == cube[x + 1][x + 1][cube.length - x - 2]) {
                combination++;
                winner = cube[x][x][cube.length - x - 1];
                if (combination == cube.length) {
                    return winner;
                }
            } else {
                winner = 0;
                combination = 1;
                break;
            }
        }
        for (int x = 0; x < cube.length - 1; x++) {
            if (cube[x][cube.length - x - 1][x] != 0 && cube[x + 1][cube.length - x - 2][x + 1] != 0 && cube[x][cube.length - x - 1][x] == cube[x + 1][cube.length - x - 2][x + 1]) {
                combination++;
                winner = cube[x][cube.length - x - 1][x];
                if (combination == cube.length) {
                    return winner;
                }
            } else {
                winner = 0;
                break;
            }
        }
        //</editor-fold>
        return winner;
    }

    public int[][][] getCube() {
        return cube;
    }

    public int[][] getOccupied() {
        return occupied;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getCube().length; i++) {
            for (int j = 0; j < getCube()[0].length; j++) {
                for (int k = 0; k < getCube()[0][0].length; k++) {
                    if (getCube()[k][j][i] == 0) {
                        sb.append(" 0 ");
                    } else {
                        sb.append(" ").append(getCube()[k][j][i]).append(" ");
                    }

                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    //<editor-fold desc="deepCopyFunctions">
    public static int[][] deepCopyOccupied(int[][] occupied) {
        int[][] newOccupied = new int[occupied.length][occupied.length];
        for (int i = 0; i < occupied.length; i++) {
            for (int j = 0; j < occupied.length; j++) {
                newOccupied[i][j] = occupied[i][j];
            }
        }
        return newOccupied;
    }

    public static int[][][] deepCopyCube(int[][][] cube) {
        int[][][] newCube = new int[cube.length][cube.length][cube.length];
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length; j++) {
                for (int k = 0; k < cube.length; k++) {
                    newCube[i][j][k] = cube[i][j][k];
                }
            }
        }
        return newCube;
    }
    //</editor-fold>
}
