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
        SortPlan plan = sortKnowledge.sort(line);
        if (plan == null) {
            throw new IllegalStateException("排序知识没有生成排序计划。");
        }

        for (int i = 0; i < plan.size(); i++) {
            SortPlan.ExchangeAction action = plan.actionAt(i);
            Huluwa first = line.atPosition(action.getFirstPosition());
            Huluwa second = line.atPosition(action.getSecondPosition());
            commandExchange(first, second);
        }
    }

    private void commandExchange(Huluwa left, Huluwa right) {
        System.out.printf("老爷爷说：%s和%s交换位置。%n", left.getName(), right.getName());
        left.exchangePositionWith(right);
    }

}
