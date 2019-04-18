package week2;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created by longxingyu on 2019/4/6.
 *
 * 首先这个类需要实现Iterable这个接口
 * 然后 iterator() 方法使得到ListIterator<Item>
 *
 * 一定要注意<Item>不要忘记
 */
public class StackIterator<Item> implements Iterable<Item> {

    private Node first;
    private int n;

    /**
     * 这里的node是不用写成Node<Item>
     */
    private class Node {
        private Item item;
        private Node next;
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
        Node oldFirst = first;
        first = new Node();
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
        return new ListIterator(first);
    }


    private class ListIterator implements Iterator<Item> {
        private Node current;

        ListIterator(Node first) {
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
