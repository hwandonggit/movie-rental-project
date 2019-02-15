

public class Main {

    public static void main(String[] args) {
        Movie fievelGoesWest = new Movie("Fievel Goes West", Category.CHILDREN);
        Customer wylie = new Customer("Wylie Burp");
        wylie.addRental(new Rental(fievelGoesWest, 2));
        System.out.print(wylie.statement());
    }
}
