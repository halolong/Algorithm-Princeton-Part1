package week2.assignment;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

import java.util.NoSuchElementException;

/**
 * Created by longxingyu on 2019/4/18.
 * 由于这个是random读取的原因 所以使用"array"实现更加适合
 */
public class RandomizedQueue<Item> implements Iterable<Item>{

    private int size;
    private Item[] s;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == s.length) resize(2 * s.length);
        s[size++] = item;
    }

    // remove and return a "random" item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        int position = StdRandom.uniform(size);
        Item item = s[position];
        s[position] = s[size - 1];
        s[--size] = null; // 将最后一个置空
        if (size > 0 && size == s.length / 4) resize(s.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        int position = StdRandom.uniform(size);
        return s[position];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int i = size;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for (int j = 0; j < i; j++) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }
        public void remove() {
            throw new UnsupportedOperationException();
         }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[order[--i]];
        }

    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("1");
        randomizedQueue.enqueue("2");
        randomizedQueue.enqueue("3");
        randomizedQueue.enqueue("4");
        randomizedQueue.enqueue("5");

        System.out.println("dequeue = "+randomizedQueue.dequeue());
        System.out.println("dequeue = "+randomizedQueue.dequeue());
        Iterator<String> iterator = randomizedQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.sample());
    }
}
