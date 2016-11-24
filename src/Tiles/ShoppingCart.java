package Tiles;

import Screen.Color;
import Screen.Display;
import Screen.Map;
import java.util.ArrayList;

public class ShoppingCart extends Tile {

    public ShoppingCart(char displayChar, int positionX, int positionY, boolean isStatic, Color stringColor, Color backgroundColor) {
        super(displayChar, positionX, positionY, true, stringColor, backgroundColor);
    }
}
