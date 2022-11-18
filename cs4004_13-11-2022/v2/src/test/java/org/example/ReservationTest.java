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
    public void ReservationTest() {
        String str = String.format("Reservation: %s, edition: %s, to: %s, removed on %tB %<te, %<tY, due: %tB %<te, %<tY, still out on loan"
                , b1.getTitle(), b1.getEdition(), p1.getName(), r1.getReservationDate(), r1.getDueDate());
        assertEquals(r1.toString(), str);
        assertFalse(r1.getReturnStatus());
        assertEquals(r1.getDueDate(), LocalDate.now());
        assertFalse(r1.getIfLate());
        r1.returnBook();

        str = String.format("Reservation: %s, edition: %s, to: %s, removed on %tB %<te, %<tY, due: %tB %<te, %<tY, returned: %tB %<te, %<tY"
                , b1.getTitle(), b1.getEdition(), p1.getName(), r1.getReservationDate(), r1.getDueDate(), r1.getDateReturned());
        assertEquals(r1.toString(), str);
        assertTrue(r1.getReturnStatus());
        assertEquals(r1.getDueDate(), LocalDate.now());
        assertFalse(r1.getIfLate());

        Reservation r2 = new Reservation(b2, p1, java.time.LocalDate.now());
        r2.returnBook(LocalDate.now().plusWeeks(5));

        String lateRet = String.format("Reservation: %s, edition: %s, to: %s, removed on %tB %<te, %<tY, due: %tB %<te, %<tY, returned: %tB %<te, %<tY, late"
                , b2.getTitle(), b2.getEdition(), p1.getName(), r2.getDateTaken(), r2.getDueDate(), r2.getDateReturned());
        assertEquals(r2.toString(), lateRet);
        assertTrue(r2.getReturnStatus());
        assertEquals(r2.getDueDate(), LocalDate.now());
        assertTrue(r2.getIfLate());
    }

    public static class DueDate {
        public static void main(String[] args) {
            LocalDate offset = LocalDate.now();
            LocalDate value = offset.plusDays(10);
            System.out.println(value);
        }


        @Test
        public void testLoanAllowed() {

        }
    }
}