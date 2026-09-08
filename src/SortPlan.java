/**
 * 排序知识生成的排序计划。
 *
 * <p>计划只描述位置上的动作，不直接修改队伍。位置从 1 开始。</p>
 */
public final class SortPlan {
    private final ExchangeAction[] actions;

    private SortPlan(ExchangeAction[] actions) {
        this.actions = actions.clone();
    }

    public static Builder builder() {
        return new Builder();
    }

    public int size() {
        return actions.length;
    }

    public ExchangeAction actionAt(int index) {
        return actions[index];
    }

    /**
     * 返回计划的字符表示，例如 {@code X1-2;X3-4}。
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < actions.length; i++) {
            if (i > 0) {
                description.append(';');
            }
            description.append(actions[i]);
        }
        return description.toString();
    }

    public static final class ExchangeAction {
        private final int firstPosition;
        private final int secondPosition;

        private ExchangeAction(int firstPosition, int secondPosition) {
            if (firstPosition < 1 || secondPosition < 1) {
                throw new IllegalArgumentException("计划位置必须从 1 开始。");
            }
            this.firstPosition = firstPosition;
            this.secondPosition = secondPosition;
        }

        public int getFirstPosition() {
            return firstPosition;
        }

        public int getSecondPosition() {
            return secondPosition;
        }

        @Override
        public String toString() {
            return "X" + firstPosition + "-" + secondPosition;
        }
    }

    /**
     * 计划构造器，构造完成后的计划不可变。
     */
    public static final class Builder {
        private ExchangeAction[] actions = new ExchangeAction[8];
        private int size;

        public Builder exchange(int leftPosition, int rightPosition) {
            add(new ExchangeAction(leftPosition, rightPosition));
            return this;
        }

        public SortPlan build() {
            ExchangeAction[] result = new ExchangeAction[size];
            System.arraycopy(actions, 0, result, 0, size);
            return new SortPlan(result);
        }

        private void add(ExchangeAction action) {
            if (size == actions.length) {
                ExchangeAction[] expanded = new ExchangeAction[actions.length * 2];
                System.arraycopy(actions, 0, expanded, 0, actions.length);
                actions = expanded;
            }
            actions[size++] = action;
        }
    }
}
