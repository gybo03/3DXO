package GameEngine;

public class CubeCore {

    private  int[][][] cube;
    private  int[][] occupied;

    public CubeCore(int numOfColumns) {
        cube = new int[numOfColumns][numOfColumns][numOfColumns];
        occupied = new int[numOfColumns][numOfColumns];
    }

    public void playAMove(int i, int j, int player) {
        gravityMechanic(i,j,player);
    }

    private void gravityMechanic(int posX, int posY, int player) {
        if (occupied[posY][posX] < cube.length) {
            int posZ = occupied[posY][posX];
            occupied[posY][posX]++;
            //player =(turn++ % numOfPlayers) + 1
            cube[posY][posX][posZ] = player;
        }
    }

    public void setCube(int[][][] cube) {
        this.cube = cube;
    }

    public void setOccupied(int[][] occupied) {
        this.occupied = occupied;
    }

    public static int[] whatMoveWasPlayed(CubeCore cubeCore1, CubeCore cubeCore2){
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
        int winner=0;

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

    public int[][][] getCube() {
        return cube;
    }

    public int[][] getOccupied() {
        return occupied;
    }
}
