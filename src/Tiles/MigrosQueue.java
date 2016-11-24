package Tiles;

import java.util.*;
import java.io.*;

import LLinkedList.LLinkedList;

public class MigrosQueue extends MultiTile {
    // People
    private LLinkedList<Person> queue;
    private double prob;

    public MigrosQueue(double prob) {
        this.queue = new LLinkedList();
        this.prob  = prob;
        this.tiles = getArt();
    }

    public int getSize() {
        return this.queue.getSize();
    }

    public void addPerson(Person p) {
        this.queue.enqueue(p);
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
            // beep
            System.out.println("\007");
            this.queue.peek().getItems().remove(0);
            if (this.queue.peek().getItems().size() <= 0) {
                this.queue.dequeue();
            }
        }
        this.tiles = getArt();
        super.update();
    }

    public Tile[][] getArt() {
        ArrayList<Tile[]> til = new ArrayList<>();

        height = 0;
        for (Person p : queue) {
            ArrayList<Item> items = p.getItems();
            int len = Math.max(4, items.size() + 1);
            p.setPosition(x + height, y + 4);

            for (int i = 0; i < len; i++) {
                char[] item = ("  |" + (i < items.size() ? items.get(i).getRepresentation() : " ") + "|").toCharArray();
                Tile[] t = new Tile[item.length];
                for (int j = 0; j < t.length; j++)
                    t[i] = new Tile(item[i], i + y, x + height);
                til.add(t);
                height++;
            }
        }

        char[] line;
        String[] s = { "__| |", " o| |", "  | |", "   ― " };
        for (String ss : s) {
            char[] c = ss.toCharArray();
            Tile[] t = new Tile[c.length];
            for (int i = 0; i < c.length; i++) {
                t[i] = new Tile(c[i], y + i, x + height);
            }
            til.add(t);
            height++;
        }

        this.width  = 6;

        Tile[][] res = (Tile[][])(til.toArray());
        System.out.println(res.length);
        System.out.println(res[0].length);
        return res;
    }
}
