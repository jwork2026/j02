/**
 * 将排序计划展开为可播放的场景时间线。
 *
 * <p>时间线只在内存中模拟计划，不修改真实队伍。</p>
 */
public final class SceneTimeline {
    private final SceneFrame[] frames;

    private SceneTimeline(SceneFrame[] frames) {
        this.frames = frames.clone();
    }

    public static SceneTimeline from(HuluwaLine line, SortPlan plan) {
        if (line == null || plan == null) {
            throw new IllegalArgumentException("队伍和排序计划不能为空。");
        }

        Huluwa[] current = line.membersInPositionOrder();
        SceneFrame[] frames = new SceneFrame[plan.size() + 1];
        frames[0] = new SceneFrame(0, plan.size(), current, null);

        for (int i = 0; i < plan.size(); i++) {
            SortPlan.ExchangeAction action = plan.actionAt(i);
            int leftIndex = action.getFirstPosition() - 1;
            int rightIndex = action.getSecondPosition() - 1;
            checkIndex(leftIndex, current.length);
            checkIndex(rightIndex, current.length);

            Huluwa temporary = current[leftIndex];
            current[leftIndex] = current[rightIndex];
            current[rightIndex] = temporary;
            frames[i + 1] = new SceneFrame(i + 1, plan.size(), current, action);
        }
        return new SceneTimeline(frames);
    }

    public int size() {
        return frames.length;
    }

    public SceneFrame frameAt(int index) {
        return frames[index];
    }

    private static void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("排序计划中的位置超出队伍范围：" + (index + 1));
        }
    }
}
