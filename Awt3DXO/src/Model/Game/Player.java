package Model.Game;

import java.awt.*;

public class Player {
    private int id;
    private boolean isItAi;
    private Player nextPlayer;
    private Color color;

    public Player(int id, boolean isItAi, Player nextPlayer, Color color) {
        this.id = id;
        this.isItAi = isItAi;
        this.nextPlayer = nextPlayer;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Player findPlayerWithId(int id){
        if(id==0){
            return null;
        }else{
            if (this.id==id){
                return this;
            }
            else {
                return nextPlayer.findPlayerWithId(id);
            }
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isItAi() {
        return isItAi;
    }

    public void setItAi(boolean itAi) {
        isItAi = itAi;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public Player setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
        return this;
    }
}
