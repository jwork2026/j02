public class Main {
    public static void main(String[] args) {
        for (Huluwa brother : Huluwa.values()) {
            System.out.printf("%d. %s%n", brother.getRank(), brother.introduce());
        }
    }
}
