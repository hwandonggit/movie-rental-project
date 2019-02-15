package rental;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

class Customer {

    private String cName;
    private List<Rental> rentalsList = new ArrayList<Rental>();

    private Statement statementRental;

    public Customer(String name) {
        cName = name;
        setPlainStatement();
    }

    public void setPlainStatement () {
        statementRental = new PlainStatement();
    }


    public void addRental(Rental rItem) {
        rentalsList.add(rItem);
    }

    public String getName() {
        return cName;
    }

    public List<Rental> getRentals() {
        return rentalsList;
    }

    public String doStatement() {
        return statementRental.value(this);
    }

    public int getTotalFrequentRenterPoints() {
        int result = 0;
        for (Rental oneRental: rentalsList) {
            result += oneRental.getFrequentRenterPoints();
        }
        // Stream<Rental> rStream = rentalsList.stream();
        return result;

    }

    public double getTotalCharge() {
        double result = 0;
        for (Rental oneRental: rentalsList) {
            result += oneRental.getCharge();
        }
        return result;
    }

}

abstract class Statement {
    public String value(Customer aCustomer) {
        List<Rental> rentals = aCustomer.getRentals();
        ListIterator<Rental> iteratorRental = rentals.listIterator();

        String result = headerString(aCustomer);

        System.out.println("rentals.size: " + rentals.size());

        while (iteratorRental.hasNext()) {
            Rental each = (Rental) iteratorRental.next();
            //show figures for this rental
            result += eachRentalString(each);
        }

        //add footer lines
        result += footerString(aCustomer);

        System.out.println("result: " + result);
        return result;
    }

    abstract String headerString (Customer aCustomer);
    abstract String footerString (Customer aCustomer);
    abstract String eachRentalString (Rental aRental);

}

class PlainStatement extends Statement {

    String headerString(Customer aCustomer) {
        return "Rental Record for " + aCustomer.getName() + "\n";
    }

    String eachRentalString(Rental aRental) {
        return "\t" + aRental.getMovie().getTitle()+ "\t" +
                String.valueOf(aRental.getCharge()) + "\n" ;
    }

    String footerString(Customer aCustomer) {
        return "Amount owed is " +
                String.valueOf(aCustomer.getTotalCharge()) + "\n" +
                "You earned " +
                String.valueOf(aCustomer.getTotalFrequentRenterPoints()) +
                " frequent renter points"
                ;
    }
}
