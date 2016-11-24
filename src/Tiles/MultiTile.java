package Tiles;

import Screen.Map;

import java.io.*;
import java.util.ArrayList;

public class MultiTile {

    protected ArrayList<ArrayList<Tile>> tiles;

    // dimensions of this tiles-construct

    protected int width;
    protected int height;
    public MultiTile(String filePath, int x, int y){
        char[][] displayCharacters = null;

        try {
            displayCharacters = getAsciiArt(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            displayCharacters = new char[][]{{'⚠'}};
        }

        tiles = new ArrayList<ArrayList<Tile>>();
        // find the dimensions of this construct
        height = displayCharacters.length;

        // initializes the tiles array with tiles made of the characters passed to the constructor
        for(int i=0;i<displayCharacters.length;i++){
            tiles.add(new ArrayList<Tile>());
            width = Math.max(displayCharacters[i].length, width);

            for(int j=0;j<displayCharacters[i].length;j++){
                tiles.get(i).add(new Tile(displayCharacters[i][j], x+j, y+i));
            }
        }

        // only print the construct to the map if it fits
        if(canPrintAt(x, y, Map.asciiMap)){
            insertAt(x, y, Map.asciiMap);
        }
    }

    /**
     * reads out ASCII art from a file and stores each line of characters in an array, resulting in an 2-dimensional return array
     * @param String path of the file containing the artwork
     */
    protected char[][] getAsciiArt(String filePath) throws IOException {
        FileInputStream fstream = null;
        BufferedReader scanner = null;

        try {
            fstream = new FileInputStream(System.getProperty("user.dir") +  "/" + filePath);
            scanner = new BufferedReader(new InputStreamReader(fstream));
        } catch (FileNotFoundException er) {
            try {
                fstream = new FileInputStream(System.getProperty("user.dir") +  "/src/" + filePath);
                scanner = new BufferedReader(new InputStreamReader(fstream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new char[][]{{'⚠'}};
            }
        }


        ArrayList<char[]> characters = new ArrayList<char[]>();
        String line = null;

        // copy each character to the array
        while((line = scanner.readLine()) != null) {

            char[] charLine = line.toCharArray();
            characters.add(charLine);
        }

        char[][] output = new char[characters.size()][characters.get(0).length];
        for(int i = 0; i < characters.size(); ++i) {
            for(int j = 0; j < characters.get(i).length; ++j) {
                output[i][j] = characters.get(i)[j];
            }
        }
        return output;
    }

    /**
     * @return true if the whole construct can be printed out at position x and y and false otherwise
     */
    final public boolean canPrintAt(int x, int y, Tile[][] map){
        // check if a9ny of the required spaces are already in use and return false if so
        for(int i=0;i<tiles.size();i++){
            for(int j=0;j<tiles.get(i).size();j++){
                if(!Map.canPopulate(x+j, y+i, map) || y+i==map.length-1 || x+j==map[0].length-1){
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
    final public void insertAt(int x, int y, Tile[][] map){
        for(int i=0;i<tiles.size();i++){
            for(int j=0;j<tiles.get(i).size();j++){
                Map.populateTile(tiles.get(i).get(j), map, x+j, y+i);
            }
        }
    }

}
