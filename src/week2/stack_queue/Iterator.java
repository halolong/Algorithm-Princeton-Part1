package week2.stack_queue;

/**
 * Created by longxingyu on 2019/4/6.
 * 这个接口是给某个的iterator需要实现的接口以及方法
 */
public interface Iterator<Item> {

    boolean hasNext();
    Item next();
}
