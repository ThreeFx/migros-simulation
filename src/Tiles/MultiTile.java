package Tiles;

import Screen.Map;

import java.io.*;
import java.util.ArrayList;

public class MultiTile implements IPrintable {
    protected Tile[][] tiles;

    // dimensions of this tiles-construct
    protected int width;
    protected int height;

    protected int x;
    protected int y;

    public MultiTile() {
    }

    public void print() {
        System.out.println(tiles[0][0].getPlaceholder());
    }

    public void update() {
        // Do nothing by default
        // Draw same map again on next tile.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Map.populateTile(tiles[i][j], Map.nextFrameMap, tiles[i][j].positionX, tiles[i][j].positionY);
            }
        }
    }

    public boolean isStatic() {
        return false;
    }

    public char getPlaceholder() {
        return 'M';
    }
}
