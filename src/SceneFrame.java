/**
 * 时间线中的一个完整场景状态。
 */
public final class SceneFrame {
    private final int step;
    private final int totalSteps;
    private final HuluwaSnapshot[] members;
    private final SortPlan.ExchangeAction action;

    SceneFrame(int step, int totalSteps, Huluwa[] members, SortPlan.ExchangeAction action) {
        this.step = step;
        this.totalSteps = totalSteps;
        this.members = HuluwaSnapshot.from(members);
        this.action = action;
    }

    public int getStep() {
        return step;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public HuluwaSnapshot[] getMembers() {
        return members.clone();
    }

    public SortPlan.ExchangeAction getAction() {
        return action;
    }

    public String getActionDescription() {
        if (action == null) {
            return "准备开始";
        }
        return "交换位置 " + action.getFirstPosition() + " 和 " + action.getSecondPosition();
    }
}
