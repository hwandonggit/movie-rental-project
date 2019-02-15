package rental;

import rental.Movie;

public class Rental {

    private Movie movieRental;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        movieRental = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movieRental;
    }

    public double getCharge() {
        return getMovie().getCharge(getDaysRented());
    }

    public int getFrequentRenterPoints() {
        return getMovie().getFrequentRenter(getDaysRented());
    }

}
