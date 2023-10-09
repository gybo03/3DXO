package GameEngine;

public class Chip {

    private int owner;

    private int posX;
    private int posY;
    private int posZ;

    public Chip(int ID, int posX, int posY) {
        this.owner = ID;
        this.posX = posX;
        this.posY = posY;
    }

    public int getOwner() {
        return owner;
    }



    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }

}
