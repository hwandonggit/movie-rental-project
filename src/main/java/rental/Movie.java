package rental;

import rental.Movie;
import rental.Rental;
import rental.Customer;
import rental.Category;


public class Movie {

//    public static final int  CHILDRENS = 2;
//    public static final int  REGULAR = 0;
//    public static final int  NEW_RELEASE = 1;

    private String titleMovie;
    private Price priceMovie;

    public Movie(String title, Category priceCode) {
        titleMovie = title;
        setPriceCode (priceCode);
    }

    public Category getPriceCode() {
        return priceMovie.getPriceCode();
    }

    public void setPriceCode (Category arg) {
        switch (arg) {
            case REGULAR:
                priceMovie = new RegularMovie();
                break;
            case CHILDRENS:
                priceMovie = new ChildrensMovie();
                break;
            case NEW_RELEASE:
                priceMovie = new NewReleaseMovie();
                break;
            default:
                throw new IllegalArgumentException("Wrong type of code");
        }
    }

    public String getTitle() {
        return titleMovie;
    }

    double getCharge(int daysRented) {
        return priceMovie.getCharge(daysRented);
    }

    public int getFrequentRenter(int daysRented) {
        return priceMovie.getFrequentPoints(daysRented);
    }


}

abstract class Price {

    abstract Category getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentPoints(int daysRented) {
        return 1;
    }

}

class ChildrensMovie extends Price {
    Category getPriceCode() {
        return Category.CHILDRENS;
    }
    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) result += (daysRented - 3) * 1.5;
        return result;
    }
}

class NewReleaseMovie extends Price {
    Category getPriceCode() {
        return Category.NEW_RELEASE;
    }
    double getCharge(int daysRented) {
        return daysRented*3;
    }
    int getFrequentPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1 ;
    }
}

class RegularMovie extends Price {
    Category getPriceCode() {
        return Category.REGULAR;
    }
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) result += (daysRented - 2) * 1.5;
        return result;
    }
}