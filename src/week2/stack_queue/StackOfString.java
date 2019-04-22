package week2.stack_queue;

public class StackOfString {

    private Node first = null;
    //inner class
    private class Node {
        String item;
        Node next;
    }

    // push
    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    //pop
    public String pop() {
        String res = first.item;
        first = first.next;
        return res;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int sum = 0;
        if (first == null) return sum;
        while (first.next != null) {
            sum++;
            first = first.next;
        }
        return (sum + 1);
    }
}

