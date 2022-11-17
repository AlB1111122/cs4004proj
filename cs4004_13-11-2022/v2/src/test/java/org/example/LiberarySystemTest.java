package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class LiberarySystemTest{
    ArrayList<Book> masterListTest = new ArrayList<>();
    ArrayList<Book> aListTest = new ArrayList<>();

    Person p1 = new Person(true, "name1", "email1@gmail.com","a","password",1);
    Person p2 = new Person(false,"name2", "email2@gmail.com","a","password",2);

    Book b1 = new Book("author","01/01/0001","title1","ed","pub");
    Book b2 = new Book("author","01/01/0001","title2","ed","pub","a");
    Book b3 = new Book("author","01/01/0001","title3","ed","pub","a, b");
    Book b4 = new Book("author","01/01/0001","title4","ed","pub","b, c");

    Ebook e1 = new Ebook("author","01/01/0001","title4","ed","pub","a, b, c");

    @Test
    public void departmentBookLists(){
        LiberarySystem sys = new LiberarySystem();

        sys.addBook(b1);
        sys.addBook(b2);
        sys.addBook(b3);
        sys.addBook(b4);

        masterListTest.add(b1);
        masterListTest.add(b2);
        masterListTest.add(b3);
        masterListTest.add(b4);

        aListTest.add(b1);
        aListTest.add(b2);
        aListTest.add(b3);

        assertEquals(masterListTest, sys.getBookList());
        assertEquals(aListTest, sys.getBookList("a"));
    }

    @Test
    public void getUserTest(){
        LiberarySystem sys = new LiberarySystem();
        sys.addPerson(p1);
        sys.addPerson(p2);

        sys.getPerson("1");

        assertFalse(sys.signIn("notPassword"));
        assertTrue(sys.signIn("password"));

        assertEquals(p1, sys.getSignedIn());
        assertThrows(RuntimeException.class, () -> sys.getPerson("3"));
        assertThrows(RuntimeException.class, () -> sys.getPerson("a"));
        assertThrows(RuntimeException.class, () -> sys.getPerson("a1"));
    }

    @Test
    public void testDateHader(){
        LiberarySystem sys = new LiberarySystem();
        assertEquals(1,sys.dateHander("20/06/2022","20/06/2021"));
        assertEquals(1,sys.dateHander("20/06/2022","01/05/2022"));
        assertEquals(1,sys.dateHander("20/06/2022","19/06/2022"));

        assertEquals(1,sys.dateHander("20/06/2022","20/06/2022"));

        assertEquals(0,sys.dateHander("20/06/2022","20/06/2023"));
        assertEquals(0,sys.dateHander("20/06/2022","20/07/2022"));
        assertEquals(0,sys.dateHander("20/06/2022","21/06/2022"));
    }

    @Test
    public void getBookOpsTest(){
        LiberarySystem sys = new LiberarySystem();
        sys.addPerson(p1);
        sys.addPerson(p2);

        sys.addBook(b1);
        sys.addBook(b2);
        sys.addBook(b3);
        sys.addBook(b4);
        sys.addBook(e1);

        sys.getPerson("1");
        sys.signIn("password");

        assertEquals("1)Loan book.",sys.getBookOps(b1));
        Loan l1 = new Loan(b1,p1);
        Loan l2 = new Loan(b2,p2);
        sys.getSignedIn().addLoan(l1);
        String str = String.format("You must return %s, to take out another loan",b1.getTitle());
        assertEquals(str,sys.getBookOps(b1));
        assertEquals("This book is not in your department",sys.getBookOps(b4));
        l1.returnBook();
        str = String.format("This book is unavailable for loan untill %1$td/%1$tm/%1$ty, 1)Reserve book.",b2.getUnavalibleUntil());
        assertEquals(str,sys.getBookOps(b2));
        assertEquals("1)Read ebook online. 2)Download ebook",sys.getBookOps(e1));
    }

    @Test
    public void testProcessBook(){
        LiberarySystem sys = new LiberarySystem();
        sys.processNewBook("author, 01/02/2003, title, edition, publisher, a, b", true);
        String str = "author, 01/02/2003, title, edition: edition, publisher, departments: a, b";
        assertEquals(str,sys.getBookList().get(sys.getBookList().size() - 1).toString());
        assertThrows(RuntimeException.class, () -> sys.processNewBook("d,ee,gty,fdsfdsf", false));

        sys.processNewBook("author, 01/02/0003, title, edition, publisher", false);
        str = "author, 01/02/0003, title, edition: edition, publisher, departments: all";
        assertEquals(str,sys.getBookList().get(sys.getBookList().size() - 1).toString());
    }


}


















