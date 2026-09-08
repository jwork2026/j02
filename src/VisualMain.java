import javax.swing.SwingUtilities;

/**
 * 可视化程序入口。
 */
public class VisualMain {
    public static void main(String[] args) {
        HuluwaLine line = new HuluwaLine(
                Huluwa.FOURTH,
                Huluwa.FIRST,
                Huluwa.SEVENTH,
                Huluwa.THIRD,
                Huluwa.SIXTH,
                Huluwa.SECOND,
                Huluwa.FIFTH
        );
        SortPlan plan = new BubbleSortAlgorithm().sort(line);
        SceneTimeline timeline = SceneTimeline.from(line, plan);
        SwingUtilities.invokeLater(() -> new MiniEngine(timeline).start());
    }
}
