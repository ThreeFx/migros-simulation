package Tiles;

import Screen.Color;
import Screen.Display;
import Screen.Map;

public class Tile extends IPrintable {

    // the string that will be printed out to the map
    protected char displayChar;

    protected int positionX;
    protected int positionY;

    protected Color backgroundColor;
    protected Color stringColor;

    // space between the individual tiles
    protected int tilePadding = 0;

    // whether the tile moves or not
    protected boolean isStatic = false;

    public Tile() {

    }
    public Tile(Tile a) {
        this(a.displayChar, a.positionX, a.positionY, a.isStatic, a.stringColor, a.backgroundColor);
    }

    public Tile(char displayChar, int positionX, int positionY) {
        this(displayChar, positionX, positionY,true,Color.NOBACKGROUND, Color.NOBACKGROUND);
    }
    public Tile(char displayChar, int positionX, int positionY, boolean isStatic, Color stringColor, Color backgroundColor){
        this.backgroundColor = backgroundColor;
        this.displayChar = displayChar;
        this.stringColor = stringColor;
        this.positionX = positionX;
        this.positionY = positionY;
        this.isStatic = isStatic;
	    //this.type = type;

    }

    // IPrintable implementation
    public void print() {
        printTile();
    }

    public void update() {
        System.out.println("tile update");
        getNextFrame();
    }

    public boolean isStatic() {
        return isStatic;
    }

    public char getPlaceholder() {
        return this.displayChar;
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


		/*switch(type) {
			case ITEM:
				
			break;
			case COUNTER:
			break;
			case WALL:
			break;
			case PERSON:
            x = new Random().nextInt(3) - 1;
            y = new Random().nextInt(3) - 1;

            if (Map.canPopulate(positionX + x, positionY + y, Map.nextFrameMap)) {
               	newX = newX + x;
               	newY = newY + y;
           	}
        	
			default:        	
		*/

        Map.populateTile(this, Map.nextFrameMap, newX, newY);
        this.positionX = newX;
        this.positionY = newY;
    }


    public void printTile(){
        String formatString = tilePadding>0?"%-"+tilePadding+"s":"%s";
        System.out.print(String.format(formatString, Display.makeColor(""+displayChar, stringColor, backgroundColor) + ""));
    }

    final public char getDisplayCharacter() { return displayChar; }

    final public boolean setPosition(int X, int Y) {
        this.positionX = X;
        this.positionY = Y;
        return true;
    }
}

