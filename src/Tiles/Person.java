package Tiles;

import Screen.Color;
import Screen.Display;
import Screen.Map;
import java.util.ArrayList;

/**
 * Created by kdkdk on 24.11.16.
 */
public class Person extends Tile {
    protected ArrayList<Item> holdingItems, shoppingList;
    //klasse
    /*

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

     */

    public Person(Person a) {
        super(a.displayChar, a.positionX, a.positionY, a.isStatic, a.stringColor, a.backgroundColor);
        this.holdingItems = a.holdingItems;
        this.shoppingList = a.shoppingList;
    }
    public Person(char displayChar, int positionX, int positionY, boolean isStatic, Color stringColor, Color backgroundColor) {
        super(displayChar, positionX, positionY, isStatic, stringColor, backgroundColor);

        // Generate int positionX, int positionY;
        //Map.asciiMap();
        //for(int )
        //Map.canPopulate()

    }

    @Override
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

    public ArrayList<Item> getItems() {
        return holdingItems;
    }
    public int getPositionX() {
        return this.positionX;
    }
    public int getPositionY() {
        return this.positionY;
    }
}
