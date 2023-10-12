package GameEngine;


import java.util.Arrays;

public class Cube {

    private final int numOfPlayers;
    private int turn = 0;
    private int winner=0;
    private final int[][][] cube;
    private final int[][] occupied;

    public Cube(int dim, int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        cube = new int[dim][dim][dim];
        occupied = new int[dim][dim];
    }

    public int[][] getOccupied() {
        return occupied;
    }

    public int getTurn() {
        return turn;
    }

    public void addChip(int posX, int posY) {
        chipFall(posX, posY);
        if (turn>= cube.length*2-1){
            findWinner();
        }
    }

    private void chipFall(int posX, int posY) {
        if (occupied[posY][posX] != cube.length) {
            int posZ=occupied[posY][posX];
            occupied[posY][posX]++;
            cube[posY][posX][posZ] = (turn++ % numOfPlayers) + 1;
        }
    }


    public String occupiedOutput() {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < occupied.length; i++) {
            for (int j = 0; j < occupied.length; j++) {
                stringBuilder.append(occupied[j][i]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int findWinner() {
        int combination = 1;

        //<editor-fold desc="rows">

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
        for (int[][] chips : cube) {
            for (int x = 0; x < cube.length - 1; x++) {
                if (chips[x][x] != 0 && chips[x + 1][x + 1] != 0 && chips[x][x] == chips[x + 1][x + 1]) {
                    combination++;
                    winner = chips[x][x];
                    if (combination == cube.length) {
                        return winner;
                    }
                } else {
                    winner = 0;
                    combination = 1;
                    break;
                }
            }
            for (int x = cube.length; x > 0; x--) {
                for (int y = 0; y < cube.length - 1; y++) {
                    if (x + y == cube.length - 1) {
                        if (chips[x - 1][y + 1] != 0 && chips[y][x] != 0 && chips[x - 1][y + 1] == chips[y][x]) {
                            combination++;
                            winner = chips[y][x];
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
        }
        //</editor-fold>

        //<editor-fold desc="yz">
        for (int x = 0; x < cube.length; x++) {
            for (int y = 0; y < cube.length - 1; y++) {
                if (cube[y][y][x] != 0 && cube[y + 1][y + 1][x] != 0 && cube[y][y][x] == cube[y + 1][y + 1][x]) {
                    combination++;
                    winner = cube[y][y][x];
                    if (combination == cube.length) {
                        return winner;
                    }
                } else {
                    winner = 0;
                    combination = 1;
                    break;
                }
            }
            for (int y = cube.length; y > 0; y--) {
                for (int z = 0; z < cube.length - 1; z++) {
                    if (y + z == cube.length - 1) {
                        if (cube[z][y][x] != 0 && cube[z + 1][y - 1][x] != 0 && cube[z][y][x] == cube[z + 1][y - 1][x]) {
                            combination++;
                            winner = cube[z][y][x];
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
        }
        //</editor-fold>

        //<editor-fold desc="zx">
        for (int y = 0; y < cube.length; y++) {
            for (int x = 0; x < cube.length - 1; x++) {
                if (cube[x][y][x] != 0 && cube[x + 1][y][x + 1] != 0 && cube[x][y][x] == cube[x + 1][y][x + 1]) {
                    combination++;
                    winner = cube[x][y][x];
                    if (combination == cube.length) {
                        return winner;
                    }
                } else {
                    winner = 0;
                    combination = 1;
                    break;
                }
            }
            for (int x = cube.length; x > 0; x--) {
                for (int z = 0; z < cube.length - 1; z++) {
                    if (x + z == cube.length - 1) {
                        if (cube[z][y][x] != 0 && cube[z + 1][y][x - 1] != 0 && cube[z][y][x] == cube[z + 1][y][x - 1]) {
                            combination++;
                            winner = cube[z][y][x];
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

    

    public int getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[][] chips : cube) {
            for (int j = 0; j < cube[0].length; j++) {
                for (int k = 0; k < cube[0][0].length; k++) {
                    if (chips[j][k] == 0) {
                        sb.append(" 0 ");
                    } else {
                        sb.append(" ").append(chips[j][k]).append(" ");
                    }

                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
