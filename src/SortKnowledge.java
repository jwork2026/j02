/**
 * 排序知识。
 */
public interface SortKnowledge {
    /**
     * 根据可排序对象生成排序计划。
     */
    SortPlan sort(Sortable sortable);
}
