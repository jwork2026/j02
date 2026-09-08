/**
 * 冒泡排序知识。
 */
public class BubbleSortAlgorithm implements SortKnowledge {
    @Override
    public SortPlan sort(Sortable sortable) {
        int[] keys = new int[sortable.size()];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = sortable.sortKeyAt(i);
        }

        SortPlan.Builder plan = SortPlan.builder();
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = 0; j < keys.length - 1 - i; j++) {
                if (keys[j] > keys[j + 1]) {
                    plan.exchange(j + 1, j + 2);

                    int temporary = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = temporary;
                }
            }
        }
        return plan.build();
    }
}
