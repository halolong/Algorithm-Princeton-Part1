package week4;


public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    // implement by array
    private Key[] pq;
    private int N;

    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[N++] = x;
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, N-1);
        return pq[--N];
    }

    public static void main(String[] args) {
        UnorderedMaxPQ maxPQ = new UnorderedMaxPQ(5);
        maxPQ.insert(1);
        maxPQ.insert(10);
        maxPQ.insert(5);
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());

    }
}
