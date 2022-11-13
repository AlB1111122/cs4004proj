package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class LiberarySimulation{


    static Person p1 = new Person(true, "Jeff Summers", "01@gmail.com", "Computer Science, Electronic & Computer Engineering", "jSum123", 1);
    static Person p2 = new Person(true, "Anne Harlow", "02@gmail.com", "Computer Science, Electronic & Computer Engineering, Mathematics & Statistics", "jSum123", 1);
    static Person p3 = new Person(false, "Student one", "03@gmail.com", "Computer Science, Electronic & Computer Engineering", "password123", 3);
    static Person p4 = new Person(false, "Student two", "04@gmail.com", "History", "password123", 4);

    static Book b1 = new Book("a0", "11/12/1984", "Java101", "20th", "pub1", "Computer Science");
    static Book b2 = new Book("a1", "01/10/2003", "Irish history", "1", "pub1", "History");
    static Book b3 = new Book("a2", "19/02/0003", "Essay Writing Guide", "2", "pub2");
    static Book b4 = new Book("a2", "01/05/1803", "Electrical engineering", "2", "pub2", "Electronic & Computer Engineering, Computer Science");
    static Book b5 = new Book("a2", "01/03/2013", "Caluclus", "2", "pub1", "Mathematics & Statistics");
    static Book b6 = new Book("a2", "32/02/2004", "title5", "2", "pub2", "UL loan, Mathematics & Statistics");


    public static void main(String[] args)throws IOException{
        LiberarySystem sys = new LiberarySystem();
        sys.addPerson(p1);
        sys.addPerson(p2);
        sys.addPerson(p3);
        sys.addPerson(p4);

        sys.addBook(b1);
        sys.addBook(b2);
        sys.addBook(b3);
        sys.addBook(b4);
        sys.addBook(b4);
        sys.addBook(b5);
        sys.addBook(b6);

        LiberaryMenu menu = new LiberaryMenu();
        menu.run(sys);
    }
}
