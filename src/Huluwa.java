/**
 * 七个葫芦娃。
 *
 * <p>排行、称呼和颜色是葫芦娃固有的身份信息；
 * 位置行为由葫芦娃响应老爷爷的指令完成，具体位置关系由队伍维护。</p>
 */
public class Huluwa {
    public static final Huluwa FIRST = new Huluwa(1, "大娃", "红色");
    public static final Huluwa SECOND = new Huluwa(2, "二娃", "橙色");
    public static final Huluwa THIRD = new Huluwa(3, "三娃", "黄色");
    public static final Huluwa FOURTH = new Huluwa(4, "四娃", "绿色");
    public static final Huluwa FIFTH = new Huluwa(5, "五娃", "青色");
    public static final Huluwa SIXTH = new Huluwa(6, "六娃", "蓝色");
    public static final Huluwa SEVENTH = new Huluwa(7, "七娃", "紫色");

    private static final Huluwa[] VALUES = {
            FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH
    };

    private final int rank;
    private final String name;
    private final String color;
    private HuluwaLine line;
    private int position;

    private Huluwa(int rank, String name, String color) {
        this.rank = rank;
        this.name = name;
        this.color = color;
    }

    public static Huluwa[] values() {
        return VALUES.clone();
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String introduce() {
        return "我是" + name + "，代表色是" + color + "。";
    }

    public int getPosition() {
        return position;
    }

    /**
     * 与另一位葫芦娃交换位置。
     */
    public void exchangePositionWith(Huluwa other) {
        checkLine(other);
        int temporary = position;
        position = other.position;
        other.position = temporary;
    }

    void join(HuluwaLine line, int position) {
        if (this.line != null && this.line != line) {
            throw new IllegalStateException(name + "已经站在另一条队伍里。");
        }
        this.line = line;
        this.position = position;
    }

    private void checkLine(Huluwa other) {
        if (other == null || line == null || other.line != line) {
            throw new IllegalArgumentException("两位葫芦娃不在同一条队伍里。");
        }
    }
}
