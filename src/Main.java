public class Main {
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
        Grandpa grandpa = new Grandpa();
        grandpa.learn(new BubbleSortKnowledge());

        System.out.println("老爷爷看见七个葫芦娃站成一排：");
        line.print();

        System.out.println("老爷爷学会了冒泡排序，开始指挥：");
        grandpa.sort(line);

        System.out.println("排序完成后：");
        line.print();
    }
}
