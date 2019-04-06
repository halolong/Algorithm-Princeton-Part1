package week2;

public class QueueOfString {

    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    QueueOfString() {
        first = null;
        last = null;
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        // 注意到第一个插入的时候，这时候就一个元素 first和last在一个位置
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    // 出队（跟stack操作一致）
    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }


}
