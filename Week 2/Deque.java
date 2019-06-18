import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Item val;
        Node next;
        Node prev;

        Node(Item value) {
            this.val = value;
            this.prev = null;
            this.next = null;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current==null) throw new NoSuchElementException();

            Item item = current.val;
            current = current.next;
            return item;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node temp = first;
        first = new Node(item);
        first.next = temp;

        if (size > 0) temp.prev = first;
        else last = first;

        size++;
    }


    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node temp = last;
        last = new Node(item);
        last.prev = temp;
        if (size > 0) temp.next = last;
        else first = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Item item = first.val;

        if (size > 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }

        size--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if(first == null) throw new NoSuchElementException();

        Item item = last.val;
        if (size > 1) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }

        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> dq = new Deque<>();
        dq.addFirst("abc");
        dq.addLast("def");
        dq.addFirst("ghi");
        dq.addLast("jkl");
        System.out.println(dq.removeFirst());
        System.out.println(dq.removeFirst());
        System.out.println(dq.removeFirst());
        System.out.println(dq.removeFirst());
    }

}
