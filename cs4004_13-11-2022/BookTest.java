package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest{
    @Test
    public void BookTestString(){
        String releaseDate = "29/10/2022";
        String author = "author";
        String title = "title";
        String edition = "1";
        String publisher = "publisher";
        Book a = new Book(author, releaseDate, title, edition, publisher, "a");
        String str = String.format("%s, %s, %s, edition: %s, %s, departments: a",author,releaseDate,title,edition,publisher);
        assertTrue(a.toString().matches(str));
        author = "new title";
        title = "title";
        edition = "2";
        publisher = "new publisher";
        releaseDate = "1/11/2022";

        a.setAuthor(author);
        a.setReleaseDate(releaseDate);
        a.setTitle(title);
        a.setEdition(edition);
        a.setPublisher(publisher);
        String str2 = String.format("%s, %s, %s, edition: %s, %s",author,releaseDate,title,edition,publisher);
        String str3 = String.format("%s, %s, %s, edition: %s, %s"
                ,a.getAuthor(),a.getReleaseDate(),a.getTitle(),a.getEdition(),a.getPublisher());
        assertTrue(str3.matches(str2));
    }

    @Test
    public void departmentsTest(){//tests
        Book a = new Book("author", "01/02/0003", "title", "edition", "publisher", "a");
        Book b = new Book("author", "01/02/0003", "title", "edition", "publisher");
        assertEquals("all", b.getDepartmentString());
        assertEquals("a", a.getDepartmentString());
        a.addDepartments("b");
        String[] arr = {"a","b"};
        assertEquals(a.getDepartmentString(), "a, b");
        a.addDepartments("c, d");
        assertEquals(a.getDepartmentString(), "a, b, c, d");
    }

    @Test
    public void testBookAvailability(){//book cant be taken out on loan if its alredy on loan, becomes avalible when returned
        Person p1 = new Person(true, "name", "email@gmail.com","a","password",1);
        Book b2 = new Book("a1", "01/02/0003","title1","1","pub1", "a");
        assertTrue(b2.getAvailble());
        Loan l1 = new Loan(b2,p1);
        assertFalse(b2.getAvailble());
        l1.returnBook();
        assertTrue(b2.getAvailble());
    }

    @Test
    public void badDate(){
        assertThrows(RuntimeException.class, () -> new Book("a","ds","book","ed","pub"));
        assertThrows(RuntimeException.class, () -> new Book("a","///","book","ed","pub"));
        assertThrows(RuntimeException.class, () -> new Book("a","34324453","book","ed","pub"));
        assertThrows(RuntimeException.class, () -> new Book("a","12/425","book","ed","pub"));
        assertThrows(RuntimeException.class, () -> new Book("a","12/4/25","book","ed","pub"));
    }
}
