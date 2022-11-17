package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookListTest{

    Book z = new Book("a1", "01/02/0003", "title1", "1", "pub1","a, b");
    Book x = new Book("a2", "01/02/0003", "title2", "2", "pub2","a");
    Book c = new Book("a2", "01/02/0003", "title2", "2", "pub2","a, b");
    Book v = new Book("a2", "01/02/0003", "title2", "2", "pub2","c, b");
    Book b = new Book("a2", "01/02/0003", "title2", "2", "pub2","c");

    BookList bl = new BookList("test");
    ArrayList<Book> list = new ArrayList<Book>();

    @Test
    public void bookListContents(){
        list.add(z);
        list.add(x);
        list.add(c);
        list.add(v);
        list.add(b);

        bl.addBook(z);
        bl.addBook("a2", "01/02/0003","title2","2","pub2","a");
        bl.addBook(c);
        bl.addBook(v);
        bl.addBook("a2", "01/02/0003","title2","2","pub2","c");

        StringBuilder s = new StringBuilder();
        for(Book boo: bl.getBookList()){
            s.append(boo.toString());
            s.append("\n");
        }
        String str = s.toString();
        assertTrue(str.matches(bl.toString()));

        list.remove(x);
        list.remove(v);

        bl.removeBook(x);
        bl.removeBook(v);

        StringBuilder removedStr = new StringBuilder();
        for(Book boo: bl.getBookList()){
            removedStr.append(boo.toString());
            removedStr.append("\n");
        }
        String str2 = removedStr.toString();
        assertTrue(str2.matches(bl.toString()));
    }

    @Test
    public void getDepatmentStrTest(){
        String str = "a, b, c, z";
        BookList testerWDep = new BookList(str);
        BookList testerNoDep = new BookList();
        assertEquals(str,testerWDep.getDepartmentString());
        assertEquals("all",testerNoDep.getDepartmentString());
    }
}
