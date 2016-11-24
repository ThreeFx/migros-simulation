package Tiles;

import Screen.Map;

import java.io.*;

public abstract class MultiTile {
    protected Tile[][] tiles;

    // dimensions of this tiles-construct
    protected int width;
    protected int height;

    protected int x;
    protected int y;


    /**
     * reads out ASCII art from a file and stores each line of characters in an array, resulting in an 2-dimensional return array
     */
    public abstract char[][] getAsciiArt() throws IOException;

    public abstract void update();

    /**
     * @return true if the whole construct can be printed out at position x and y and false otherwise
     */
    final public boolean canPrintAt(Tile[][] map){
        // check if any of the required spaces are already in use and return false if so
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                if (!Map.canPopulate(x+j, y+i, map) || y+i >= map.length-1 || x+j >= map[0].length-1) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Inserts the whole construct to a map
     * @param x horizontal coordinate at which the construct should be inserted
     * @param y vertical coordinate at which the construct should be inserted
     * @param map a map in which the tiles should be saved
     */
    final public void insertAt(Tile[][] map){
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                Map.populateTile(tiles[i][j], map, x+j, y+i);
            }
        }
    }

}
