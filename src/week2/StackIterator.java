package week2;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created by longxingyu on 2019/4/6.
 * 一定要注意<Item>不要忘记
 */
public class StackIterator<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public StackIterator(){
        first = null;
        n = 0;
    }

    public boolean isEmpty(){
        return first != null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("stack Overflow");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }


    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
