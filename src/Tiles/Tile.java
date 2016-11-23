package Tiles;

import Screen.Color;
import Screen.Display;
import Screen.Map;


import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Tile {

    // the string that will be printed out to the map
    protected char displayChar;

    protected int positionX;
    protected int positionY;

    private Color backgroundColor;
    private Color stringColor;

    // space between the individual tiles
    private int tilePadding = 0;

    // whether the tile moves or not
    private boolean isStatic = false;

	//private TileType type;

	List<Item> items;	

    public Tile(char displayChar, int positionX, int positionY){
        this(displayChar, positionX, positionY, true, Color.BLUE, Color.NOBACKGROUND);
    }

    public Tile(char displayChar, int positionX, int positionY, boolean isStatic){
        this(displayChar, positionX, positionY, isStatic, Color.BLUE, Color.NOBACKGROUND);
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
	    //this.type = type;
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

    final public boolean isStatic() { return isStatic; }

    final public char getDisplayCharacter() { return displayChar; }

}
