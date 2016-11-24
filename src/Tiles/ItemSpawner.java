package Tiles;

import Screen.Color;
import Screen.Display;
import Screen.Map;
import java.util.ArrayList;

public class ItemSpawner extends Tile {
    public ItemSpawner(ItemSpawner a) {
        super(a.displayChar, a.positionX, a.positionY, a.isStatic, a.stringColor, a.backgroundColor);
    }
    public ItemSpawner(char displayChar, int positionX, int positionY, boolean isStatic, Color stringColor, Color backgroundColor) {
        super(displayChar, positionX, positionY, true, stringColor, backgroundColor);
    }
}
