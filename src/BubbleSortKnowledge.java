/**
 * 冒泡排序知识。
 */
public class BubbleSortKnowledge implements SortKnowledge {
    @Override
    public void sort(HuluwaLine line, Grandpa grandpa) {
        for (int i = 0; i < line.size() - 1; i++) {
            for (int j = 0; j < line.size() - 1 - i; j++) {
                Huluwa left = line.get(j);
                Huluwa right = line.get(j + 1);
                if (left.getRank() > right.getRank()) {
                    grandpa.commandExchange(line, left, right);
                }
            }
        }
    }
}
