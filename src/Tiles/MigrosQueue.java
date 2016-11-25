package Tiles;

import java.util.*;

import LLinkedList.LLinkedList;
import LLinkedList.Node;
import Screen.*;
import Screen.Map;

public class MigrosQueue extends MultiTile {
    // People
    private LLinkedList<Person> queue;
    private double prob;

    private int queueOffset = 0;

    public MigrosQueue(double prob, int x, int y) {
        this.queue = new LLinkedList();
        this.prob  = prob;
        this.x = x;
        this.y = y;
        this.tiles = getArt();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Map.populateTile(tiles[i][j], Map.asciiMap, tiles[i][j].positionX, tiles[i][j].positionY);
            }
        }
    }

    public int getSize() {
        return this.queue.getSize();
    }

    public void addPerson(Person p) {
        this.queue.enqueue(p);
        int index = 0;

        for(Node currentNode:queue) {
            if(currentNode.getItem() == null) break;

            Person c = (Person) currentNode.getItem();

            c.stopMovement = true;
            Map.populateTile(c, Map.nextFrameMap, x+3, y+queueOffset-index);

            index++;
        }
    }

    public void transferTo(MigrosQueue goal) {
        int diff = (this.getSize() - goal.getSize()) / 2;
        for (int i = 0; i < diff; i++) {
            goal.addPerson(this.queue.pop());
        }
    }

    @Override
    public void update() {
        if (Math.random() <= prob) {
            /*if(this.queue.peek() != null && this.queue.peek().getItems() != null){
                this.queue.peek().getItems().remove(0);
                if (this.queue.peek().getItems().size() <= 0) {
                    this.queue.dequeue();
                }
            }*/

        }
        this.tiles = getArt();
        super.update();
    }

    public Tile[][] getArt() {
        ArrayList<Tile[]> til = new ArrayList<>();

        height = 0;

        char[] line;
        String[] s = { "│ │", "│ │", "│ │", "│ │" };

        for (int i=0;i<s.length;i++) {
            char[] c = s[i].toCharArray();
            Tile[] t = new Tile[c.length+1];
            for (int j = 0; j < c.length; j++) {
                t[j] = new Tile(c[j], x+j, y+i, false, Color.RED, Color.NOBACKGROUND);
            }
            t[c.length] = new Tile(' ', x+c.length, y+i);
            til.add(t);
            height++;
        }

        if(queueOffset == 0){
            queueOffset = height-1;
        }

        if(!queue.isEmpty()) {
            int index = 0;

            for(Node currentNode:queue) {
                if(currentNode.getItem() == null) break;

                Person p = (Person) currentNode.getItem();
                ArrayList<Item> items = p.getItems();

                int len = items!=null?Math.max(4, items.size() + 1):0;

                p.stopMovement = true;

                Map.populateTile(p, Map.nextFrameMap, x+3, y+queueOffset-index);

                for (int i = 0; i < len; i++) {
                    char[] item = ("  |" + (i < items.size() ? items.get(i).getRepresentation() : " ") + "|").toCharArray();
                    Tile[] t = new Tile[item.length];
                    for (int j = 0; j < t.length; j++)
                        t[i] = new Tile(item[i], i + y, x + height);
                    til.add(t);
                    height++;
                }
                index++;
            }
        }

        Tile[][] res = til.toArray(new Tile[til.size()][til.get(0).length]);

        this.width = res[0].length;

        return res;
    }

}
