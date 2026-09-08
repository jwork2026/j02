/**
 * 葫芦娃站成的一条线。
 *
 * <p>线负责保存成员并按成员自身的位置提供访问；位置变化由葫芦娃完成。</p>
 */
public class HuluwaLine implements Sortable {
    private final Huluwa[] members;

    public HuluwaLine(Huluwa... members) {
        this.members = members.clone();
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                throw new IllegalArgumentException("队伍中不能有空位置。");
            }
            if (indexOf(members[i]) != i) {
                throw new IllegalArgumentException("同一个葫芦娃不能占据多个位置。");
            }
        }
        for (int i = 0; i < members.length; i++) {
            members[i].join(this, i + 1);
        }
    }

    public int size() {
        return members.length;
    }

    public Huluwa get(int index) {
        return atPosition(index + 1);
    }

    public Huluwa atPosition(int position) {
        checkPosition(position);
        for (Huluwa member : members) {
            if (member.getPosition() == position) {
                return member;
            }
        }
        throw new IllegalStateException("队伍位置没有对应的葫芦娃：" + position);
    }

    @Override
    public int sortKeyAt(int index) {
        return get(index).getRank();
    }

    Huluwa[] membersInPositionOrder() {
        Huluwa[] ordered = new Huluwa[members.length];
        for (int i = 0; i < ordered.length; i++) {
            ordered[i] = atPosition(i + 1);
        }
        return ordered;
    }

    Huluwa memberAt(int index) {
        return members[index];
    }

    public void print() {
        for (int i = 0; i < members.length; i++) {
            Huluwa brother = atPosition(i + 1);
            System.out.printf("位置%d：%d. %s%n", i + 1, brother.getRank(), brother.introduce());
        }
    }

    private int indexOf(Huluwa brother) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] == brother) {
                return i;
            }
        }
        return -1;
    }

    private void checkPosition(int position) {
        if (position < 1 || position > members.length) {
            throw new IndexOutOfBoundsException("队伍位置超出范围：" + position);
        }
    }
}
