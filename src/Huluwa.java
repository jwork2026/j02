/**
 * 七个葫芦娃。
 *
 * <p>排行、称呼和颜色是葫芦娃固有的身份信息；
 * 他在队伍中的位置不属于这个类型。</p>
 */
public enum Huluwa {
    FIRST(1, "大娃", "红色"),
    SECOND(2, "二娃", "橙色"),
    THIRD(3, "三娃", "黄色"),
    FOURTH(4, "四娃", "绿色"),
    FIFTH(5, "五娃", "青色"),
    SIXTH(6, "六娃", "蓝色"),
    SEVENTH(7, "七娃", "紫色");

    private final int rank;
    private final String name;
    private final String color;

    Huluwa(int rank, String name, String color) {
        this.rank = rank;
        this.name = name;
        this.color = color;
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
}

