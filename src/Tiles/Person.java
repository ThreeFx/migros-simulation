package Tiles;

import Screen.Color;
import Screen.Display;
import Screen.Map;
import java.util.ArrayList;
import java.util.Random;
import LLinkedList.QQueue;
import LLinkedList.LLinkedList;
import LLinkedList.SStack;
/**
 * Created by kdkdk on 24.11.16.
 */
public class Person extends Tile {
    protected ArrayList<Item> holdingItems, shoppingList;
    //klasse
    /*

    // the string that will be printed out to the map
    protected char displayChar;

    protected int positionX;
    protected int positionY;

    private Color backgroundColor;
    private Color stringColor;

    // space between the individual tiles
    private int tilePadding = 0;

    // whether the tile moves or not
    private boolean isStatic = false;

     */

    public Person(Person a) {
        super(a.displayChar, a.positionX, a.positionY, a.isStatic, a.stringColor, a.backgroundColor);
        this.holdingItems = a.holdingItems;
        this.shoppingList = a.shoppingList;
    }
    public Person(char displayChar, int positionX, int positionY, boolean isStatic, Color stringColor, Color backgroundColor) {
        super(displayChar, positionX, positionY, isStatic, stringColor, backgroundColor);

        // Generate int positionX, int positionY;
        //Map.asciiMap();
        //for(int )
        //Map.canPopulate()

    }

    private class PFPair {
        int y, x;
        PFPair(int y, int x) {
            this.x = x;
            this.y = y;
        }
    };
    private class Path {
        SStack<PFPair> path;
        Path(SStack<PFPair> path) {
            this.path = path;
        }
        Path(char to, int fromY, int fromX) {
            path = new LLinkedList<PFPair>();
            QQueue<PFPair> q = new LLinkedList<PFPair>();
            PFPair start = new PFPair(fromY, fromX);
            q.enqueue(start);
            PFPair[][] visited = new PFPair[Map.height][Map.width];
            for(PFPair[] index : visited) {
                for(int i = 0; i < index.length; ++i) {
                    index[i] = new PFPair(-1,-1);
                }
            }
            visited[fromY][fromX] = new PFPair(-2,-2);
            while(!q.isEmpty()) {
                PFPair t = q.dequeue();
                if(Map.asciiMap[t.y][t.x].getPlaceholder() == to) { //!TODO Maybe change to staticLayer
                    PFPair c = new PFPair(t.x,t.y);
                    while(visited[c.y][c.x].x != -2) {
                        path.push(c);
                        c = visited[c.y][c.x];
                    }
                    while(!q.isEmpty()) q.dequeue();
                }
                    if(t.y > 0 && Map.staticLayer[t.y-1][t.x] == null) {
                        if(visited[t.y-1][t.x].x == -1) {
                            visited[t.y - 1][t.x] = t;
                            PFPair np = new PFPair(t.y - 1, t.x);
                            q.enqueue(np);
                        }
                    }
                    if(t.x > 0 && Map.staticLayer[t.y][t.x-1] == null) {
                        if(visited[t.y][t.x-1].x == -1) {
                            visited[t.y][t.x-1] = t;
                            PFPair np = new PFPair(t.y, t.x-1);
                            q.enqueue(np);
                        }
                    }
                    if(t.y < Map.height - 1 && Map.staticLayer[t.y+1][t.x] == null) {
                        if(visited[t.y+1][t.x].x == -1) {
                            visited[t.y+1][t.x] = t;
                            PFPair np = new PFPair(t.y+1, t.x);
                            q.enqueue(np);
                        }
                    }
                    if(t.x < Map.width - 1 && Map.staticLayer[t.y][t.x+1] == null) {
                        if(visited[t.y][t.x+1].x == -1) {
                            visited[t.y][t.x+1] = t;
                            PFPair np = new PFPair(t.y, t.x+1);
                            q.enqueue(np);
                        }
                    }

            }
        }
    };

    @Override
    public void getNextFrame(){
        int newX = positionX, newY = positionY;
        int x;
        int y;


        x = new Random().nextInt(3) - 1;
        y = new Random().nextInt(3) - 1;

        if (Map.canPopulate(positionX + x, positionY + y, Map.nextFrameMap)) {
            newX = newX + x;
            newY = newY + y;
        }


        Map.populateTile(this, Map.nextFrameMap, newX, newY);
        this.positionX = newX;
        this.positionY = newY;
    }

    public ArrayList<Item> getItems() {
        return holdingItems;
    }
    public int getPositionX() {
        return this.positionX;
    }
    public int getPositionY() {
        return this.positionY;
    }
}
