package Screen;

import Tiles.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class Map {

    public static IPrintable[][] asciiMap;

    // layer where all static objects reside
    public static IPrintable[][] staticLayer;
    // layer where all movable objects reside
    //public static Tile[][] movableLayer;

    public static IPrintable[][] nextFrameMap;

    public static int height;
    public static int width;

    public static ArrayList<MigrosQueue> queues;
    public static ArrayList<ItemSpawner> items;
    public static ArrayList<Person> customers;

    /**
     * Initializes the map to a given width and height. The population of the map will be optimized later.
     */
    public static void initializeMap(String location) {
        Checkout checkout = new Checkout();
        queues = new ArrayList<>();
        items = new ArrayList<>();
        customers = new ArrayList<>();

        char[][] mapperoni;
        try {
            mapperoni = checkout.getAsciiArt(location);

            int maxWidth = 0;

            // map can be uneven -> get max dimensions
            for(char[] row:mapperoni) {
                maxWidth = Math.max(maxWidth, row.length);
            }

            Map.height = mapperoni.length;
            Map.width = maxWidth;

            System.out.println(width + " " + height);
            asciiMap = new IPrintable[height][width];
            nextFrameMap = new IPrintable[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (j == 0 || i == 0 || j == width - 1 || i == height - 1) {
                        asciiMap[i][j] = new Tile(Map.getSideChar(i, j), j, i, true, Color.BLUE, Color.NOBACKGROUND);
                    } else if(j<mapperoni[i].length){
                        char cChar = mapperoni[i][j];

                        if (cChar == '◷') {
                            ItemSpawner item = new ItemSpawner(cChar, j, i, true, Color.GREEN, Color.NOBACKGROUND);
                            asciiMap[i][j] = item;
                            items.add(item);
                        } else if (cChar == '◸') {
                            ItemSpawner item = new ItemSpawner(cChar, j, i, true, Color.RED, Color.NOBACKGROUND);
                            asciiMap[i][j] = item;
                            items.add(item);
                        } else if (cChar == '◐') {
                            ItemSpawner item = new ItemSpawner(cChar, j, i, true, Color.YELLOW, Color.NOBACKGROUND);
                            asciiMap[i][j] = item;
                            items.add(item);
                        } else if (cChar == 'S') {
                            MigrosQueue queue = new MigrosQueue(0.7, j, i);
                            if(Math.random()*100>50){
                                queue.addPerson(new Person('#', j, i, false, Color.YELLOW, Color.NOBACKGROUND));
                            }
                            if(Math.random()*100>50) {
                                queue.addPerson(new Person('%', j, i, false, Color.YELLOW, Color.NOBACKGROUND));
                            }
                            if(Math.random()*100>50) {
                                queue.addPerson(new Person('&', j, i, false, Color.YELLOW, Color.NOBACKGROUND));
                            }
                            asciiMap[i][j] = queue;
                            queues.add(queue);
                        }  else if (cChar != ' ') {
                            asciiMap[i][j] = new Tile(cChar, j, i, true, Color.BLUE, Color.NOBACKGROUND);
                        }
                    }
                }
            }
            staticLayer = new IPrintable[height][width];
            for(int i = 0; i < asciiMap.length; ++i) {
                for(int j = 0; j < asciiMap[i].length; ++j) {
                    if(asciiMap[i][j] instanceof Person) staticLayer[i][j] = new Person((Person)asciiMap[i][j]);
                    else if(asciiMap[i][j] instanceof ItemSpawner) staticLayer[i][j] = new ItemSpawner((ItemSpawner)asciiMap[i][j]);
                    else if(asciiMap[i][j] instanceof Tile) staticLayer[i][j] = new Tile((Tile)asciiMap[i][j]);
                }
            }

        }catch (Exception e) {
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
    public static boolean canPopulate(int x, int y, IPrintable[][] map) {
        if (x < 0 || x >= width || y < 0 || y >= height || map[y][x] != null)
            return false;
        else {
            return true;
        }
    }

    public static void populateTile(Tile tile, IPrintable[][] map, int x, int y) {
        if (tile.getPlaceholder() == ' ') return;
        if (x < 0 || x >= width || y < 0 || y >= height) return;

        map[y][x] = tile;
    }

    public static void getNextFrame() {
        ArrayList<IPrintable> toProcess = new ArrayList<IPrintable>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(asciiMap[i][j] != null && asciiMap[i][j].isStatic() == false && !(asciiMap[i][j] instanceof Person)) {
                    //if (asciiMap[i][j] instanceof MigrosQueue) throw new RuntimeException("huzzah");
                    toProcess.add(asciiMap[i][j]);
                }
            }
        }
        nextFrameMap = new IPrintable[staticLayer.length][staticLayer[0].length];
        for(int i = 0; i < staticLayer.length; ++i) {
            for(int j = 0; j < staticLayer[i].length; ++j) {
                if(asciiMap[i][j] instanceof Person) {
                }//nextFrameMap[i][j] = asciiMap[i][j];
                else if(asciiMap[i][j] instanceof ItemSpawner) nextFrameMap[i][j] = new ItemSpawner((ItemSpawner)staticLayer[i][j]);
                else if (asciiMap[i][j] instanceof Tile) {
                    if(asciiMap[i][j].isStatic() && asciiMap[i][j].getPlaceholder() == 'Q' && (new Random()).nextInt(500)==42) {
                        Person p = new Person('@', i, j, false, Color.YELLOW, Color.NOBACKGROUND);
                        nextFrameMap[i][j] = p;
                        customers.add(p);
                    }
                    else if(staticLayer[i][j] != null)
                        nextFrameMap[i][j] = new Tile((Tile) staticLayer[i][j]);
                }
                else if(asciiMap[i][j] == null && staticLayer[i][j] != null) {
                    if(staticLayer[i][j] instanceof Person) nextFrameMap[i][j] = new Person((Person)staticLayer[i][j]);
                    else if(staticLayer[i][j] instanceof ItemSpawner) nextFrameMap[i][j] = new ItemSpawner((ItemSpawner)staticLayer[i][j]);
                    else if(staticLayer[i][j] instanceof Tile) nextFrameMap[i][j] = new Tile((Tile)staticLayer[i][j]);
                }
            }
        }

        queues.forEach(MigrosQueue::update);

        // randomise the order in which the movable tiles should be processed
        Collections.shuffle(customers);

        // process all remaining tiles that are not static
        for (Person c:customers) {
            if(Math.random()*1000<10 && !c.stopMovement){
                queues.get(0).addPerson(c);
            }else{
                c.update();
            }
        }

        for (IPrintable p:toProcess){
            p.update();
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
