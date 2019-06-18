import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        autoEnlarge();
        queue[size++] = item;
    }

    private void autoEnlarge() {
        if (size == queue.length) resize(2* queue.length);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = randomIndex();
        Item item = queue[index];
        queue[index] = queue[size -1];
        queue[size -1] = null;
        size--;
        autoShrink();
        return item;
    }

    private void autoShrink() {
        if (size > 0 && size == queue.length/4) resize(queue.length/2);
    }

    private int randomIndex()
    {
        return StdRandom.uniform(0, size);
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return queue[randomIndex()];
    }

    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private Item[] randomizedQueue;
        private int current;

        public RandomArrayIterator() {
            copyQueue();
            StdRandom.shuffle(randomizedQueue);
        }

        private void copyQueue() {
            randomizedQueue = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomizedQueue[i] = queue[i];
            }
        }

        public boolean hasNext() {
            return current < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomizedQueue[current++];
        }
    }
}