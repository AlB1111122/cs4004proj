package org.example;

public class Ebook extends Book{
    //String to provide user with a link to the ebook

    public Ebook(String author, String releaseDate, String title, String edition, String publisher, String departments) {
        super(author, releaseDate, title, edition, publisher, departments);
    }

    public Ebook(String author, String releaseDate, String title, String edition, String publisher) {
        super(author, releaseDate, title, edition, publisher);}
}