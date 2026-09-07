/**
 * 葫芦娃站成的一条线。
 *
 * <p>线只负责保存位置，不负责决定谁该怎么换位。</p>
 */
public class HuluwaLine {
    private final Huluwa[] members;

    public HuluwaLine(Huluwa... members) {
        this.members = members.clone();
    }

    public int size() {
        return members.length;
    }

    public Huluwa get(int index) {
        return members[index];
    }

    public int indexOf(Huluwa brother) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] == brother) {
                return i;
            }
        }
        return -1;
    }

    public void set(int index, Huluwa brother) {
        members[index] = brother;
    }

    public void print() {
        for (int i = 0; i < members.length; i++) {
            Huluwa brother = members[i];
            System.out.printf("位置%d：%d. %s%n", i + 1, brother.getRank(), brother.introduce());
        }
    }
}
