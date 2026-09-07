/**
 * 七个葫芦娃。
 *
 * <p>排行、称呼和颜色是葫芦娃固有的身份信息；
 * 他在队伍中的位置不属于这个类型。</p>
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

    public void exchangeWith(HuluwaLine line, Huluwa other) {
        int myIndex = line.indexOf(this);
        int otherIndex = line.indexOf(other);
        if (myIndex < 0 || otherIndex < 0) {
            throw new IllegalArgumentException("葫芦娃不在这条线里。");
        }

        line.set(myIndex, other);
        line.set(otherIndex, this);
    }
}
