import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

class Customer {

    private String _name;
    private List<Integer> _rentals = new ArrayList<Integer>();

    private Statement _statement ;

    public Customer(String name) {
        _name = name;
        setPlainStatement();
    }

    public void setPlainStatement () {
        _statement = new PlainStatement();
    }


    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public Enumeration getRentals() {
        return _rentals.;
    }

    public String doStatement() {
        return _statement.value(this);
    }

    public int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;

    }

    public double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

}

abstract class Statement {
    public String value(Customer aCustomer) {
        Enumeration rentals = aCustomer.getRentals();

        String result = headerString(aCustomer);

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //show figures for this rental
            result += eachRentalString(each);
        }

        //add footer lines
        result += footerString(aCustomer);

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
