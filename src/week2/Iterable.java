package week2;

/**
 * Created by longxingyu on 2019/4/6.
 * 让后面的class 实现（implements）该接口
 */
public interface Iterable<Item> {
    Iterator<Item> iterator();
}
