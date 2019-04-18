package week2.assignment;

import week2.Iterable;
import week2.Iterator;

import java.util.NoSuchElementException;

/**
 * Created by longxingyu on 2019/4/6.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last; //双向链表一开始也是有两个点
    private int size;


    // 使用链表实现其结构 定义Node
    private class Node {
        private Node pre;
        private Node next;
        private Item item;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("input cannot be null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.pre = null; // 这里有点疑惑，就是插入的方向
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.pre = first;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("input cannot be null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.pre = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack Overflow");
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = first;
        } else {
            first.pre = null;
        }
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack Overflow");
        Item item  = last.item;
        last = last.pre;
        size--;
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node cur = first;
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public boolean hasNext() {
            return cur != null;
        }
        public Item next() {
            /**
             * 这个是把它弹出来
                Item item = first.item;
                first = first.next;
                size--;
                if (isEmpty()) {
                    last = first;
                } else {
                    first.pre = null;
                }
                return item;
             */
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
    }

        // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("1");
        deque.addFirst("2");
        Iterator<String> iterator = deque.iterator();
        System.out.println(deque.size());

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
