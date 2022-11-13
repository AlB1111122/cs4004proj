package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest{

    Person p1 = new Person(true, "name", "email@gmail.com","a","password",1);
    Book b1 = new Book("a1", "01/02/0003","title1","1","pub1", "a");
    Book b2 = new Book("a1", "06/12/2035","title1","1","pub1", "a");
    Loan l1 = new Loan(b1,p1);

    @Test
    public void testLoan(){

        String str = String.format("Loaned: %s: edition: %s, to: %s, removed on %tB %<te, %<tY, still out on loan"
                ,b1.getTitle(),b1.getEdition(),p1.getName(),l1.getDateTaken());
        assertTrue(l1.toString().matches(str));
        assertFalse(l1.getReturnStatus());
        assertFalse(l1.getIfLate());

        l1.returnBook();
        str = String.format("Loaned: %s: edition: %s, to: %s, removed on %tB %<te, %<tY, returned: %tB %<te, %<tY"
                ,b1.getTitle(),b1.getEdition(),p1.getName(),l1.getDateTaken(), l1.getDateReturned());
        assertTrue(l1.toString().matches(str));
        assertTrue(l1.getReturnStatus());
        assertFalse(l1.getIfLate());

        Loan l2 = new Loan(b2,p1);
        l2.returnBook(LocalDate.now().plusWeeks(5));
        String lateRet = String.format("Loaned: %s: edition: %s, to: %s, removed on %tB %<te, %<tY, returned: %tB %<te, %<tY, late"
                ,b1.getTitle(),b1.getEdition(),p1.getName(),l2.getDateTaken(), l2.getDateReturned());
        assertTrue(l2.toString().matches(lateRet));
        assertTrue(l2.getReturnStatus());
        assertTrue(l2.getIfLate());
    }

    @Test
    public void testLoanAlowed(){
        assertThrows(RuntimeException.class, () ->  new Loan(b1,p1));
    }
}
