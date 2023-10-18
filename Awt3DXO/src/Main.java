import UI.UserInterface;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserInterface.run(90,10,2,3);
        int[][][] a={{{1,2},{3,4}},{{5,6},{7,8}}};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = 0; k < a[0][0].length; k++) {
                    if (a[i][j][k] == 0) {
                        sb.append(" 0 ");
                    } else {
                        sb.append(" ").append(a[i][j][k]).append(" ");
                    }

                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        /*System.out.println(a[0][0][0]);
        System.out.println(a[0][0][1]);
        System.out.println(a[0][1][0]);
        System.out.println(a[0][1][1]);
        System.out.println(a[1][0][1]);
        System.out.println(a[1][0][1]);
        System.out.println(a[1][1][0]);
        System.out.println(a[1][1][1]);*/
       // System.out.println(sb.toString());
        //System.out.println(game.findWinner());

    }
}
