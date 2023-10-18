package GameEngine;


public class Cube extends CubeCore {
    //num of players is realPlayers+ AIs
    public static int numOfPlayers = 2;

    public static int numOfAIs = 1;

    private int winner = 0;


    public Cube(int numOfColumns) {
        super(numOfColumns);
    }


    public void makeAMove(int i, int j) {
        playAMove(i, j);
    }


    public String occupiedOutput() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getOccupied().length; i++) {
            for (int j = 0; j < getOccupied().length; j++) {
                stringBuilder.append(getOccupied()[j][i]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


    public int getWinner() {
        return winner;
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
}
