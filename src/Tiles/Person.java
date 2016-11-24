package Tiles;

import Screen.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdkdk on 24.11.16.
 */
public class Person extends Tile {
    List<Item> items;

    public Person(char displayChar, int positionX, int positionY){
        super(displayChar, positionX, positionY, true, Color.BLUE, Color.NOBACKGROUND);
    }

    public Person(char displayChar, int positionX, int positionY, boolean isStatic){
        super(displayChar, positionX, positionY, isStatic, Color.BLUE, Color.NOBACKGROUND);
    }

    public Person(char displayChar, int positionX, int positionY, Color stringColor, Color backgroundColor){
        super(displayChar, positionX, positionY, true, stringColor, backgroundColor);
    }

    public Person(char displayChar, int positionX, int positionY, boolean isStatic, Color stringColor, Color backgroundColor) {
        super(displayChar, positionX, positionY, true, stringColor, backgroundColor);
    }

}
