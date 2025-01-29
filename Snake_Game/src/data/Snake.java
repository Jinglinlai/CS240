package data;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<Cell> body;
    private Cell head;

    public Snake(Cell initial) {
        body = new LinkedList<>();
        head = initial;
        body.add(head);
        head.setFill("snake");
    }

    public Cell getHead() {
        return head;
    }

    public LinkedList<Cell> getBody() {
        return body;
    }

    public void move(Cell nextCell) {
        head = nextCell;
        body.addFirst(head);
        head.setFill("snake");

        // Remove tail unless growing
        Cell tail = body.removeLast();
        tail.setFill("empty");
    }

    public void grow() {
        // The tail remains in place, so we grow the snake by adding the tail back to the body
        Cell tail = body.getLast();
        body.addLast(tail); // Add tail back to body, without moving it
    }
}
