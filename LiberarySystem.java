package org.example;

import java.util.ArrayList;

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

    public ArrayList<Book> getBookList(String departments){
        BookList ret = new BookList(departments);
        for(String retDep:ret.getDepartments()){
            for(Book b: masterList.getBookList()){
                if(b.getDepartments().isEmpty()){
                    ret.addBook(b);
                }else{
                    for(String bookDep : b.getDepartments()) {
                        if (retDep.matches(bookDep)) {
                            ret.addBook(b);
                            break;
                        }
                    }
                }
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


}
