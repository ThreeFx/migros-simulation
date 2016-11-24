package Screen;

import Tiles.Checkout;
import Tiles.Tile;
import Tiles.ItemSpawner;
//import Tiles.TileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class Map {

    public static Tile[][] asciiMap;

    // layer where all static objects reside
    public static Tile[][] staticLayer;
    // layer where all movable objects reside
    public static Tile[][] movableLayer;

    public static Tile[][] nextFrameMap;

    private static int height;
    private static int width;

    private static TreeSet<Character> itemList;

    /**
     * Initializes the map to a given width and height. The population of the map will be optimized later.
     *
     * @param width
     * @param height
     */
    public static void initializeMap(String location) {
        Checkout mt = new Checkout();
        char[][] mapperoni = new char[1][1];
        try {
            mapperoni = Checkout.getAsciiArt(location);
            Map.height = mapperoni.length;
            Map.width = mapperoni[0].length;

            System.out.println(width + " " + height);
        asciiMap = new Tile[height][width];

        itemList = new TreeSet<Character>();
        itemList.add('◷');
        itemList.add('◸');
        itemList.add('◐');



        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0 || i == 0 || j == width - 1 || i == height - 1) {

                    asciiMap[i][j] = new Tile(Map.getSideChar(i, j), j, i, true, Color.BLUE, Color.NOBACKGROUND);
                } else {
                    char cChar = mapperoni[i][j];

                    if(cChar == '◷') {
                        asciiMap[i][j] = new ItemSpawner(cChar, j, i, true, Color.GREEN, Color.NOBACKGROUND);
                    } else if(cChar == '◸') {
                        asciiMap[i][j] = new ItemSpawner(cChar, j, i, true, Color.RED, Color.NOBACKGROUND);
                    } else if(cChar == '◐') {
                        asciiMap[i][j] = new ItemSpawner(cChar, j, i, true, Color.YELLOW, Color.NOBACKGROUND);
                    } else if (cChar != ' '){
                        asciiMap[i][j] = new Tile(cChar, j, i, true, Color.BLUE, Color.NOBACKGROUND);
                    }
                    //int rand = new Random().nextInt(1000);
                    //if (rand > 995)
                    //    new Checkout(j, 1);
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            asciiMap[height / (i + 2)][width / (i + 2)] = new Tile('@', width / (i + 2), height / (i + 2), false, Color.YELLOW, Color.NOBACKGROUND);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns whether the desired position in the map is still free or is already in use by another tile
     *
     * @param x Coordinate X
     * @param y Coordinate Y
     * @return true if the position is free, false otherwise
     */
    public static boolean canPopulate(int x, int y, Tile[][] map) {
        if (x < 0 || x >= width || y < 0 || y >= height || map[y][x] != null)
            return false;
        else {
            return true;
        }
    }

    public static void populateTile(Tile tile, Tile[][] map, int x, int y) {
        if (tile.getDisplayCharacter() == ' ') return;
        if (x < 0 || x >= width || y < 0 || y >= height) return;

        map[y][x] = tile;
    }

    public static void getNextFrame() {
        nextFrameMap = new Tile[height][width];

        ArrayList<Tile> toProcess = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // if there is no tile at this position we don't have to update anything
                if (asciiMap[i][j] == null) continue;
                // if the tile is is not movable, we can copy it without doing any calculations
                if (asciiMap[i][j].isStatic())
                    nextFrameMap[i][j] = asciiMap[i][j];
                    // if it's movable we have to wait until all static objects have been copied
                else
                    toProcess.add(asciiMap[i][j]);
            }
        }

        // randomise the order in which the movable tiles should be processed
        Collections.shuffle(toProcess);

        // process all remaining tiles that are not static
        for (int i = 0; i < toProcess.size(); i++) {
            toProcess.get(i).getNextFrame();
        }

        asciiMap = nextFrameMap;

    }

    public static char getSideChar(int i, int j) {
        char displayChar;
        if (i == 0 && j == 0)
            displayChar = '╔';
        else if (i == height - 1 && j == width - 1)
            displayChar = '╝';
        else if (i == height - 1 && j == 0)
            displayChar = '╚';
        else if (i == 0 && j == width - 1)
            displayChar = '╗';
        else if (j == 0 || j == width - 1)
            displayChar = '║';
        else
            displayChar = '═';

        return displayChar;
    }

}
