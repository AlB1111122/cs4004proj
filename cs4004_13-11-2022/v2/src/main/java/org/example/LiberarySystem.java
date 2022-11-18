package org.example;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class LiberarySystem{
    public static String in;

    private Person attemptingIn;
    private Person signedIn;
    private BookList masterList = new BookList();
    private ArrayList<Person> people = new ArrayList<>();

    public void addBook(Book book){
        masterList.addBook(book);
    }

    public ArrayList<Book> getBookList(){
        return masterList.getBookList();
    }

    public ArrayList<Person> getPeople(){
        return people;
    }

    public ArrayList<Book> getBookList(String departments) {//changed bc the other was broken
        BookList ret = new BookList(departments);
        for (Book b : masterList.getBookList()) {
            for (String s : b.getDepartments()) {
               if(ret.getDepartments().contains(s)){
                   ret.addBook(b);
                   break;
               }
            }
            if(b.getDepartments().isEmpty()){
             ret.addBook(b);
            }
        }
        return ret.getBookList();
    }

    public Person getPerson(String userID){
        if(userID.matches(".*[a-zA-Z].*") || !(userID.matches(".*[0-9].*"))){
            throw new RuntimeException("That user does not exist, please double check the ID you entered and try again");
        }
        int id = Integer.parseInt(userID);
        for(Person p:people){
            if(id - p.getID() == 0){
                attemptingIn = p;
                return p;
            }
        }
        throw new RuntimeException("That user does not exist, please double check the ID you entered and try again");
    }

    public boolean signIn(String password){
        if(attemptingIn.getPassword().matches(password)){
            signedIn = attemptingIn;
            return true;
        }
        return false;
    }

    public Person getSignedIn() {
        return signedIn;
    }

    public void addPerson(Person person){
        people.add(person);
    }

    public void registerUser(String newIsStaff, String newName, String newEmail, String newDepartments, String newPassword, int newID){
        boolean isStaff = false;

        if(!(newIsStaff.matches("Y") || (newIsStaff.matches("N")))){
            throw new RuntimeException("That input is invalid. Please enter 'Y/N'.");
        }
        if (newIsStaff.equals("Y")) {
            isStaff = true;
        }
        if (newIsStaff.equals("N")) {
            isStaff = false;
        }

        if(!newEmail.contains("@") || !newEmail.contains(".") || !newEmail.matches(".*[a-zA-Z].*")){//makes sure email is an email
                throw new RuntimeException("Cannot add this email. That's not an email");
            }


        for(Person p:people){
                if (p.getEmail().equals(newEmail)) {
                    System.out.println(p);
                    break;
                }

        }

        Person p = new Person(isStaff, newName, newEmail, newDepartments, newPassword, newID);
        people.add(p);
    }



    public int dateHander(String date1, String date2){
        String[] d2 = date2.split("/");
        if(date1.matches("[a-zA-Z]") || !date1.matches(".*[0-9].*")){
            return -1;
        }
        String[] in = date1.split("/");
        if(!(in.length - 3 == 0)){
            return -1;
        }
        if(!(in[0].length() - 2 == 0) || !(in[1].length() - 2 == 0) || !(in[2].length() - 4 == 0)){
            return -1;
        }
        if(Integer.parseInt(in[0] + in[1] + in[2]) >= Integer.parseInt(d2[0] + d2[1] + d2[2])){
            return 1;
        }else{
            return 0;
        }
    }

    public String getBookOps(Book book){//need to add ebook boolean
        boolean av = book.getAvailble();
        boolean depatmentCompatible = false;
        boolean userEligable = true;
        if(book.getDepartments().isEmpty()){
            depatmentCompatible = true;
        }else{
            for(String s: book.getDepartments()){
                if(signedIn.getDepartments().contains(s)){
                    depatmentCompatible = true;
                    break;
                }
            }
        }
        if(!signedIn.getLoans().isEmpty()){
            userEligable = signedIn.getLoans().get(signedIn.getLoans().size() - 1).getReturnStatus();
        }
        //returning parts
        if(depatmentCompatible){
            if(book instanceof Ebook){
                return "1)Read ebook online. 2)Download ebook";
            }
            if(!userEligable){
                return String.format("You must return %s, to take out another loan", signedIn.getLoans().get(signedIn.getLoans().size() - 1).getBook().getTitle());
            }
            if(av){
                return "1)Loan book.";
            }else{
                return String.format("This book is unavailable for loan untill %1$td/%1$tm/%1$ty, 1)Reserve book.",book.getUnavalibleUntil());
            }
        }else{
            return "This book is not in your department";
        }
    }

    public String processNewBook(String bookInfo, boolean ebook)throws RuntimeException {
        int i = 0;
        for (char c : bookInfo.toCharArray()) {
            if (Objects.equals(c, ',')) {
                i++;
            }
        }
        if (i < 4) {
            throw new RuntimeException("invalid input");
        }
        if (i - 4 == 0) {
            String[] arr = new String[5];
            arr = bookInfo.split(", ", 5);
            if (ebook) {
                try {
                    Ebook book = new Ebook(arr[0], arr[1], arr[2], arr[3], arr[4]);
                    masterList.addBook(book);
                    return "Book added";
                } catch (RuntimeException ex) {
                    return ex.getMessage();
                }
            } else {
                try {
                    Book book = new Book(arr[0], arr[1], arr[2], arr[3], arr[4]);
                    masterList.addBook(book);
                    return "Book added";
                } catch (RuntimeException ex) {
                    return ex.getMessage();
                }
            }
        }
        String[] arr = new String[6];
        arr = bookInfo.split(", ", 6);
        if (ebook) {
            try {
                Ebook book = new Ebook(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
                masterList.addBook(book);
                return "Book added";
            } catch (RuntimeException ex) {
                return ex.getMessage();
            }
        } else {
            try {
                Book book = new Book(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
                masterList.addBook(book);
                return "Book added";
            } catch (RuntimeException ex) {
                return ex.getMessage();
            }
        }
    }
}
