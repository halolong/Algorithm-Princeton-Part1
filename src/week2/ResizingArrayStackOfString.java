package week2;

public class ResizingArrayStackOfString {

    private String[] s;
    private int N = 0;
    public ResizingArrayStackOfString() {
        s = new String[1];
    }

    // 有优化处理，即当n只有原来的1/4的时候就把capacity减一半
    public String pop() {
        // 因为从0开始计数
        String item  = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }
    public void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public int size() {
        return N;
    }
    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
