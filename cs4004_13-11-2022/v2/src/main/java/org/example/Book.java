package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Book{
    private String author;
    private String releaseDate;
    private String title;
    private String edition;
    private String publisher;

    private boolean availble;

    private LocalDate unavalibleUntil;
    private ArrayList<String> departments = new ArrayList<>();

    Book(String author, String releaseDate, String title, String edition, String publisher){
        checkDateCorrect(releaseDate);
        this.author = author;
        this.releaseDate = releaseDate;
        this.title = title;
        this.edition = edition;
        this.publisher = publisher;//regular add data
        availble = true;//stop ppl from taking out books that r alredy out
    }
    Book(String author, String releaseDate, String title, String edition, String publisher, String departments){
        checkDateCorrect(releaseDate);
        this.author = author;
        this.releaseDate = releaseDate;
        this.title = title;
        this.edition = edition;
        this.publisher = publisher;//regular add data
        String[] arr = departments.split(", ");//split up depatments list so it can add to arry
        this.departments.addAll(Arrays.asList(arr));
        availble = true;//stop ppl from taking out books that r alredy out
    }

    //start auth
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    //start date
    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    //start title
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    //start edition
    public String getEdition(){
        return edition;
    }

    public void setEdition(String edition){
        this.edition = edition;
    }

    //start publisher
    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public ArrayList<String> getDepartments(){
        return departments;
    }

    public void addDepartments(String dep){
        String[] arr = dep.split(", ");
        this.departments.addAll(Arrays.asList(arr));
    }

    public String getDepartmentString(){
        StringBuilder str = new StringBuilder();
        if(departments.isEmpty()){
            return "all";
        }
        for(String s:departments){
            str.append(s).append(", ");
        }
        str.delete(str.length() - 2, str.length());
        return str.toString();//string just for departments cuz its awkward
    }

    private void checkDateCorrect(String date)throws RuntimeException{
        if(date.matches("[a-zA-Z]") || !date.matches(".*[0-9].*")){
            throw new RuntimeException("not a valid date");
        }
        String[] in = date.split("/");
        if(!(in.length - 3 == 0)){
            throw new RuntimeException("not a valid date");
        }
        if(!(in[0].length() - 2 == 0) || !(in[1].length() - 2 == 0) || !(in[2].length() - 4 == 0)){
            throw new RuntimeException("not a valid date");
        }
    }

    public boolean getAvailble(){
        return availble;
    }

    public void setAvailble(boolean boo){
        availble = boo;
    }

    public LocalDate getUnavalibleUntil() {
        return unavalibleUntil;
    }

    public void setUnavalibleUntil(LocalDate unavalibleUntil) {
        this.unavalibleUntil = unavalibleUntil;
    }

    public String toString(){
        return String.format("%s, %s, %s, edition: %s, %s, departments: %s",author,releaseDate,title,edition,publisher,getDepartmentString());
    }

}
