package Tiles;

import java.util.*;
import java.io.*;

import LLinkedList.LLinkedList;

class MigrosQueue extends MultiTile {
    // People
    private LLinkedList<Person> queue;
    private double prob;

    public MigrosQueue(String filePath, int x, int y, double prob) {
        super();
        this.queue = new LLinkedList();
        this.prob  = prob;
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

    public void update() {
        if (Math.random() <= prob) {
            // beep
            System.out.println("\007");
            this.queue.peek().getItems().remove(0);
            if (this.queue.peek().getItems().size() <= 0) {
                this.queue.dequeue();
            }
        }
    }

    public char[][] getAsciiArt() throws IOException {
        // Fuck error handling
        FileInputStream fstream = new FileInputStream(System.getProperty("user.dir") +  "/Art/MigrosQueue.ASCIIART");
        BufferedReader scanner = new BufferedReader(new InputStreamReader(fstream));

        ArrayList<char[]> chars = new ArrayList<>();
        String line;

        int addToHeight = 0;
        for (Person p : queue) {
            ArrayList<Item> items = p.getItems();
            int len = Math.max(3, items.size());
            p.setPosition(x + addToHeight, y + 4);

            for (int i = 0; i < len; i++) {
                char[] item = ("  |" + (i < items.size() ? items.get(i).getRepresentation() : " ") + "|").toCharArray();
                chars.add(item);
            }
            addToHeight += len;
        }

        while ((line = scanner.readLine()) != null) {
            chars.add(line.toCharArray());
        }

        this.height = 4 + addToHeight;
        this.width  = 6;

        return (char[][])(chars.toArray());
    }
}
