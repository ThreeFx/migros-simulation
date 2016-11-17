import java.util.ArrayList;
import java.util.Random;

public class Map {

    public static Tile[][] asciiMap;
    public static Tile[][] nextFrameMap;

    private static int width;
    private static int height;

    /**
     * Initializes the map to a given width and height. The population of the map will be optimized later.
     * @param width
     * @param height
     */
    public static void initializeMap(int width, int height){
        Map.width = width;
        Map.height = height;
        asciiMap = new Tile[height][width];

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(j == 0 || i == 0 || j == width-1 || i == height-1)
                    asciiMap[i][j] = new Tile((char)(new Random().nextInt(42)+48), j, i, Color.YELLOW, Color.NORMAL);
            }
        }

        asciiMap[height/2][width/2] = new Tile('Ⓐ', height/2, width/2, false);
        asciiMap[height/3][width/2] = new Tile('Ⓑ', height/3, width/2, false);
        asciiMap[height/2][width/3] = new Tile('Ⓒ', height/2, width/3, false);

    }

    /**
     * Returns whether the desired position in the map is still free or is already in use by another tile
     * @param x Coordinate X
     * @param y Coordinate Y
     * @return true if the position is free, false otherwise
     */
    public static boolean canPopulate(int x, int y){
        if(x<0 || x>= width || y<0 || y>= height || nextFrameMap[y][x] != null)
            return false;
        else{
            return true;}
    }

    public static void getNextFrame(){
        nextFrameMap = new Tile[asciiMap.length][asciiMap[0].length];

        ArrayList<Tile> toProcess = new ArrayList<>();

        for(int i=0;i<asciiMap.length;i++){
            for(int j=0;j<asciiMap[i].length;j++){
                // if there is no tile at this position we don't have to update anything
                if(asciiMap[i][j] == null) continue;
                // if the tile is is not movable, we can copy it without doing any calculations
                if(asciiMap[i][j].isStatic())
                    nextFrameMap[i][j] = asciiMap[i][j];
                // if it's movable we have to wait until all static objects have been copied
                else
                    toProcess.add(asciiMap[i][j]);
            }
        }

        // process all remaining tiles that are not static
        for(int i=0;i<toProcess.size();i++){
            toProcess.get(i).getNextFrame();
        }

        asciiMap = nextFrameMap;

    }

    public static void populateTile(Tile tile, int x, int y) {nextFrameMap[y][x] = tile;}

}
