package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    Person p1 = new Person(true, "name", "email@gmail.com", "a", "password", 1);
    Book b1 = new Book("a1", "01/02/0003", "title1", "1", "pub1", "a");
    Book b2 = new Book("a1", "06/12/2035", "title1", "1", "pub1", "a");
    Reservation r1 = new Reservation(b1, p1, LocalDate.now().plusWeeks(3));


    @Test
    public void testLoan() {
        String str = String.format("Loaned: %s: edition: %s, to: %s, removed on %tB %<te, %<tY, still out on loan"
                ,b1.getTitle(),b1.getEdition(),p1.getName(),r1.getReservationDate());
    }


}