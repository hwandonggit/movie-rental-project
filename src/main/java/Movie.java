public class Movie {

//    public static final int  CHILDRENS = 2;
//    public static final int  REGULAR = 0;
//    public static final int  NEW_RELEASE = 1;

    private String _title;
    private Price _price;

    public Movie(String title, Category priceCode) {
        _title = title;
        setPriceCode (priceCode);
    }

    public Category getPriceCode() {
        return _price.getPriceCode();
    }

    public void setPriceCode (Category arg) {
        switch (arg) {
            case REGULAR:
                _price = new RegularPrice();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return _title;
    }

    double getCharge(int daysRented) {
        return _price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return _price.getFrequentRenterPoints(daysRented);
    }


}

abstract class Price {

    abstract Category getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

}

class ChildrensPrice extends Price {
    Category getPriceCode() {
        return Category.CHILDRENS;
    }
    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) result += (daysRented - 3) * 1.5;
        return result;
    }
}

class NewReleasePrice extends Price {
    Category getPriceCode() {
        return Category.NEW_RELEASE;
    }
    double getCharge(int daysRented) {
        return daysRented*3;
    }
    int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1 ;
    }
}

class RegularPrice extends Price {
    Category getPriceCode() {
        return Category.REGULAR;
    }
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) result += (daysRented - 2) * 1.5;
        return result;
    }
}