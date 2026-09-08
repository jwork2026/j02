/**
 * 排序知识可以读取的对象。
 *
 * <p>排序知识只能读取长度和每个位置的排序键，不能直接修改对象。</p>
 */
public interface Sortable {
    int size();

    int sortKeyAt(int index);
}
