/**
 * 快速排序知识。
 */
public class QuickSortAlgorithm implements SortKnowledge {
    @Override
    public SortPlan sort(Sortable sortable) {
        int[] keys = new int[sortable.size()];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = sortable.sortKeyAt(i);
        }

        SortPlan.Builder plan = SortPlan.builder();
        quickSort(keys, 0, keys.length - 1, plan);
        return plan.build();
    }

    private void quickSort(int[] keys, int low, int high, SortPlan.Builder plan) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(keys, low, high, plan);
        quickSort(keys, low, pivotIndex - 1, plan);
        quickSort(keys, pivotIndex + 1, high, plan);
    }

    /**
     * Lomuto 分区：以末尾元素为基准，返回基准最终所在的下标。
     */
    private int partition(int[] keys, int low, int high, SortPlan.Builder plan) {
        int pivot = keys[high];
        int boundary = low;
        for (int i = low; i < high; i++) {
            if (keys[i] < pivot) {
                exchange(keys, boundary, i, plan);
                boundary++;
            }
        }
        exchange(keys, boundary, high, plan);
        return boundary;
    }

    private void exchange(int[] keys, int first, int second, SortPlan.Builder plan) {
        if (first == second) {
            return;
        }
        plan.exchange(first + 1, second + 1);

        int temporary = keys[first];
        keys[first] = keys[second];
        keys[second] = temporary;
    }
}
