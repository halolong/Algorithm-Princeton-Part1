package week2.stack_queue;

public class GenericStack<Item> {

    //一开始的时候
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    GenericStack(){

    }

    public void push(Item item) {
        Node oldFirst = first;
        // 这里是共用的private里面的first而不是new 对象出来的
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
