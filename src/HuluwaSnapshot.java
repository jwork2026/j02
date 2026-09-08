/**
 * 用于显示的葫芦娃不可变快照。
 */
public final class HuluwaSnapshot {
    private final int rank;
    private final String name;
    private final String color;
    private final int position;

    private HuluwaSnapshot(int rank, String name, String color, int position) {
        this.rank = rank;
        this.name = name;
        this.color = color;
        this.position = position;
    }

    static HuluwaSnapshot[] from(Huluwa[] members) {
        HuluwaSnapshot[] snapshots = new HuluwaSnapshot[members.length];
        for (int i = 0; i < members.length; i++) {
            Huluwa member = members[i];
            snapshots[i] = new HuluwaSnapshot(
                    member.getRank(), member.getName(), member.getColor(), i + 1);
        }
        return snapshots;
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

    public int getPosition() {
        return position;
    }
}
