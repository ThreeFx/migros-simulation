import java.util.Random;

public class Tile {

    // the string that will be printed out to the map
    private char displayChar;

    private int positionX;
    private int positionY;

    private Color backgroundColor;
    private Color stringColor;

    private int tilePadding = 10;

    private boolean isStatic = false;

    public Tile(char displayChar, int positionX, int positionY){
        this(displayChar, positionX, positionY, true, Color.BLUE, Color.NORMAL);
    }

    public Tile(char displayChar, int positionX, int positionY, boolean isStatic){
        this(displayChar, positionX, positionY, isStatic, Color.BLUE, Color.NORMAL);
    }

    public Tile(char displayChar, int positionX, int positionY, Color stringColor, Color backgroundColor){
        this(displayChar, positionX, positionY, true, stringColor, backgroundColor);
    }

    public Tile(char displayChar, int positionX, int positionY, boolean isStatic, Color stringColor, Color backgroundColor){
        this.backgroundColor = backgroundColor;
        this.displayChar = displayChar;
        this.stringColor = stringColor;
        this.positionX = positionX;
        this.positionY = positionY;
        this.isStatic = isStatic;
    }

    final public void printTile(){
        String formatString = tilePadding>0?"%-"+tilePadding+"s":"%s";
        System.out.print(String.format(formatString, Display.makeColor(""+displayChar, stringColor, backgroundColor) + " "));
    }

    /**
     * Calculate this tile's position in the next frame in this function.
     * This would for example be where you implement a path finding algorithm.
     *
     * This function must be overridden. Right now the movement is random and mainly for debugging
     */
    public void getNextFrame(){
        int newX = positionX, newY = positionY;

        int x;
        int y;

        boolean movementPossible = false;

        while(!movementPossible) {
            x = new Random().nextInt(3)-1;
            y = new Random().nextInt(3)-1;
            if (Map.canPopulate(positionX + x, positionY + y)) {
                newX = newX + x;
                newY = newY + y;
                movementPossible = true;
            }
        }

        Map.populateTile(this, newX, newY);
        this.positionX = newX;
        this.positionY = newY;
    }

    final public boolean isStatic() {return isStatic;}

}
