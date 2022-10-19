package GameEngine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cube game = new Cube(4, 2);
        /*
        //prvi
        game.addChip(0,0);
        //drugi
        game.addChip(2,0);
        //prvi
        game.addChip(1,0);
        //drugi
        game.addChip(2,2);
        //prvi
        game.addChip(3,0);
        //drugi
        game.addChip(2,1);
        //prvi
        game.addChip(2,0);
        //drugi
        game.addChip(1,2);
        //prvi
        game.addChip(2,0);
        //drugi
        game.addChip(2,3);

        System.out.println(game);
        */
        Scanner scanner = new Scanner(System.in);
        int x, y;
        while (game.getWinner() == 0) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            game.addChip(x, y);
            System.out.println(game);
        }
        System.out.println(game.getWinner());


        //System.out.println(game.findWinner());
    }
}
