package rental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void testCustomer() {
        Customer c = new CustomerBuilder().build();
        assertNotNull(c);
    }

    @Test
    public void testAddRental() {
        Customer customer2 = new CustomerBuilder().withName("Grace").build();
        Movie movie1 = new Movie("Movie3", Category.REGULAR);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        customer2.addRental(rental1);
    }

    @Test
    public void testGetName() {
        Customer c = new Customer("David");
        assertEquals("David", c.getName());
    }

    @Test
    public void statementForRegularMovie() {
        Movie movie1 = new Movie("Movie3", Category.REGULAR);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Grace")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Grace\n" +
                "\tMovie3\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.doStatement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForNewReleaseMovie() {
        Movie movie1 = new Movie("Movie2", Category.NEW_RELEASE);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Grace")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Grace\n" +
                "\tMovie2\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        String statement = customer2.doStatement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForChildrensMovie() {
        Movie movie1 = new Movie("Movie1", Category.CHILDRENS);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2
                = new CustomerBuilder()
                .withName("Grace")
                .withRentals(rental1)
                .build();
        String expected = "Rental Record for Grace\n" +
                "\tMovie1\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.doStatement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForManyMovies() {
        Movie movie1 = new Movie("Movie1", Category.CHILDRENS);
        Rental rental1 = new Rental(movie1, 6); // 6 day rental
        Movie movie2 = new Movie("Movie2", Category.NEW_RELEASE);
        Rental rental2 = new Rental(movie2, 2); // 2 day rental
        Movie movie3 = new Movie("Movie3", Category.REGULAR);
        Rental rental3 = new Rental(movie3, 8); // 8 day rental
        Customer customer1
                = new CustomerBuilder()
                .withName("David")
                .withRentals(rental1, rental2, rental3)
                .build();
        String expected = "Rental Record for David\n" +
                "\tMovie1\t6.0\n" +
                "\tMovie2\t6.0\n" +
                "\tMovie3\t11.0\n" +
                "Amount owed is 23.0\n" +
                "You earned 4 frequent renter points";
        String statement = customer1.doStatement();
        assertEquals(expected, statement);
    }
}