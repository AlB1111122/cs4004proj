package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest{

    String name = "Alex Butler";
    String email = "alex@gmail.com";
    ArrayList<Loan> goalLoans = new ArrayList<Loan>();
    Person p1 = new Person(true, name, email,"a","password",1);
    Person p2 = new Person(false,name, email,"a","password",2);

    Book b1 = new Book("a1", "06/12/2035","title1","1","pub1", "a");
    Book b2 = new Book("a2", "06/12/2035","title2","1","pub2", "a");
    Loan l1 = new Loan(b1,p1);
    Loan l2 = new Loan(b2,p2);

    @Test
    public void testPerson() {

        goalLoans.add(l1);

        p1.addLoan(l1);
        p1.addLoan(l1);
        assertEquals(goalLoans, p1.getLoans());

        String str = String.format("Staff Member:\nID: 1, Name: %s, Email: %s, departments: a", name, email);
        assertEquals(p1.toString(), str);
        String str2 = String.format("Student:\nID: 2, Name: %s, Email: %s, departments: a", name, email);
    }

    @Test
    public void testThrowEmail(){
        assertThrows(RuntimeException.class, () -> new Person(true,"Eric Delano", "edel@gmailcom","a","password",1));
        assertThrows(RuntimeException.class, () -> new Person(true,"Eric Delano", "edelgmail.com","a","password",1));
        assertThrows(RuntimeException.class, () -> new Person(true,"Eric Delano", "@.","a","password",1));
        assertThrows(RuntimeException.class, () -> new Person(true,"Eric Delano", "vhfudsbavi","a","password",1));
    }

    @Test
    public void testThrowLoan(){
        assertThrows(RuntimeException.class, () -> p1.addLoan(l2));
    }
}
