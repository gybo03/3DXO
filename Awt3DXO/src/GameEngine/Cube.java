package GameEngine;



public class Cube {

    private final int cycle;
    private int turn = 0;
    private int winner=0;
    private final Chip[][][] cube;
    public final int[][] occupied;

    public Cube(int dim, int cycle) {
        this.cycle = cycle;
        cube = new Chip[dim][dim][dim];
        occupied = new int[dim][dim];
    }

    private void chipFall(Chip chip) {
        if (occupied[chip.getPosY()][chip.getPosX()] != cube.length) {
            chip.setPosZ(occupied[chip.getPosY()][chip.getPosX()]);
            occupied[chip.getPosY()][chip.getPosX()]++;
            cube[chip.getPosZ()][chip.getPosY()][chip.getPosX()] = chip;
        }
    }

    public int findWinner() {
        int combination = 1;

        //<editor-fold desc="rows">

        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length; j++) {
                for (int k = 0; k < cube.length - 1; k++) {
                    if (cube[j][i][k] != null && cube[j][i][k + 1] != null && cube[j][i][k].getOwner() == cube[j][i][k + 1].getOwner()) {
                        combination++;
                        winner = cube[j][i][k].getOwner();
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
                    if (cube[i][k][j] != null && cube[i][k + 1][j] != null && cube[i][k][j].getOwner() == cube[i][k + 1][j].getOwner()) {
                        combination++;
                        winner = cube[i][k][j].getOwner();
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
                    if (cube[k][i][j] != null && cube[k + 1][i][j] != null && cube[k][i][j].getOwner() == cube[k + 1][i][j].getOwner()) {
                        combination++;
                        winner = cube[k][i][j].getOwner();
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
        for (Chip[][] chips : cube) {
            for (int x = 0; x < cube.length - 1; x++) {
                if (chips[x][x] != null && chips[x + 1][x + 1] != null && chips[x][x].getOwner() == chips[x + 1][x + 1].getOwner()) {
                    combination++;
                    winner = chips[x][x].getOwner();
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
                        if (chips[x - 1][y + 1] != null && chips[y][x] != null && chips[x - 1][y + 1].getOwner() == chips[y][x].getOwner()) {
                            combination++;
                            winner = chips[y][x].getOwner();
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
                if (cube[y][y][x] != null && cube[y + 1][y + 1][x] != null && cube[y][y][x].getOwner() == cube[y + 1][y + 1][x].getOwner()) {
                    combination++;
                    winner = cube[y][y][x].getOwner();
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
                        if (cube[z][y][x] != null && cube[z + 1][y - 1][x] != null && cube[z][y][x].getOwner() == cube[z + 1][y - 1][x].getOwner()) {
                            combination++;
                            winner = cube[z][y][x].getOwner();
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
                if (cube[x][y][x] != null && cube[x + 1][y][x + 1] != null && cube[x][y][x].getOwner() == cube[x + 1][y][x + 1].getOwner()) {
                    combination++;
                    winner = cube[x][y][x].getOwner();
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
                        if (cube[z][y][x] != null && cube[z + 1][y][x - 1] != null && cube[z][y][x].getOwner() == cube[z + 1][y][x - 1].getOwner()) {
                            combination++;
                            winner = cube[z][y][x].getOwner();
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
            if (cube[x][x][x] != null && cube[x + 1][x + 1][x + 1] != null && cube[x][x][x].getOwner() == cube[x + 1][x + 1][x + 1].getOwner()) {
                combination++;
                winner = cube[x][x][x].getOwner();
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
            if (cube[cube.length - x - 1][x][x] != null && cube[cube.length - x - 2][x + 1][x + 1] != null && cube[cube.length - x - 1][x][x].getOwner() == cube[cube.length - x - 2][x + 1][x + 1].getOwner()) {
                combination++;
                winner = cube[cube.length - x - 1][x][x].getOwner();
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
            if (cube[x][x][cube.length - x - 1] != null && cube[x + 1][x + 1][cube.length - x - 2] != null && cube[x][x][cube.length - x - 1].getOwner() == cube[x + 1][x + 1][cube.length - x - 2].getOwner()) {
                combination++;
                winner = cube[x][x][cube.length - x - 1].getOwner();
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
            if (cube[x][cube.length - x - 1][x] != null && cube[x + 1][cube.length - x - 2][x + 1] != null && cube[x][cube.length - x - 1][x].getOwner() == cube[x + 1][cube.length - x - 2][x + 1].getOwner()) {
                combination++;
                winner = cube[x][cube.length - x - 1][x].getOwner();
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

    public void addChip(int posX, int posY) {
        Chip chip = new Chip((turn++ % cycle) + 1, posX, posY);
        chipFall(chip);
        if (turn>= cube.length*2-1){
            findWinner();
        }
    }

    public int getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Chip[][] chips : cube) {
            for (int j = 0; j < cube[0].length; j++) {
                for (int k = 0; k < cube[0][0].length; k++) {
                    if (chips[j][k] == null) {
                        sb.append(" 0 ");
                    } else {
                        sb.append(" ").append(chips[j][k].getOwner()).append(" ");
                    }

                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
