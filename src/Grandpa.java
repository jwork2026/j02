/**
 * 指挥七个葫芦娃排队的老爷爷。
 */
public class Grandpa {
    private SortKnowledge sortKnowledge;

    public void learn(SortKnowledge sortKnowledge) {
        this.sortKnowledge = sortKnowledge;
    }

    public void sort(HuluwaLine line) {
        if (sortKnowledge == null) {
            throw new IllegalStateException("老爷爷还没有学会排序知识。");
        }
        sortKnowledge.sort(line, this);
    }

    public void commandExchange(HuluwaLine line, Huluwa left, Huluwa right) {
        System.out.printf("老爷爷说：%s和%s交换位置。%n", left.getName(), right.getName());
        left.exchangeWith(line, right);
    }
}
