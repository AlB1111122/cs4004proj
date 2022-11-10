package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookListTest{

    Book z = new Book("a1", "2035-06-15", "title1", "1", "pub1","a, b");
    Book x = new Book("a2", "2003-12-15", "title2", "2", "pub2","a");
    Book c = new Book("a2", "2003-12-15", "title2", "2", "pub2","a, b");
    Book v = new Book("a2", "2003-12-15", "title2", "2", "pub2","c, b");
    Book b = new Book("a2", "2003-12-15", "title2", "2", "pub2","c");

    BookList bl = new BookList("test","test");
    ArrayList<Book> list = new ArrayList<Book>();

    @Test
    public void bookListContents(){
        list.add(z);
        list.add(x);
        list.add(c);
        list.add(v);
        list.add(b);

        bl.addBook(z);
        bl.addBook("a2", "2003-12-15","title2","2","pub2","a");
        bl.addBook(c);
        bl.addBook(v);
        bl.addBook("a2", "2003-12-15","title2","2","pub2","c");

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
}
