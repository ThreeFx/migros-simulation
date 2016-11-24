package Tiles;

import java.io.*;
import java.util.ArrayList;

public class Checkout {

    static final public char[][] getAsciiArt(String filePath) throws IOException {
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

        char[][] output = characters.toArray(new char[characters.size()][characters.get(0).length]);

        return output;
    }
}
