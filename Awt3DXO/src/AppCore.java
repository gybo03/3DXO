import Model.AppModel;

public class AppCore {
    private static int func(int[][][] cube) {
        int winner = 0;
        int combination = 1;
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
        return winner;
    }

    public static void main(String[] args) {
        AppModel appModel = AppModel.getInstance();
        /*int[][][] cube =
                {{{2, 0, 0},
                  {2, 0, 0},
                  {1, 1, 1}},
                        {{0, 0, 0},
                         {0, 0, 0},
                         {0, 0, 0}},
                                {{0, 0, 0},
                                 {0, 0, 0},
                                 {0, 0, 0}}};
        System.out.println(func(cube));*/
    }
}
