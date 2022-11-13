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

    public ArrayList<Book> getBookList(String departments) {//changed bc the other was broken
        BookList ret = new BookList(departments);
        for (Book b : masterList.getBookList()) {
            if (ret.getBookList().contains(b)) {
                break;
            }
            for (String s : b.getDepartments()) {
                for (String retS : ret.getDepartments()) {
                    if (s.equalsIgnoreCase(retS)) {
                        ret.addBook(b);
                    }
                }
            }
        }
        return ret.getBookList();
    }
            /*
        for(String retDep:ret.getDepartments()){
            for(Book b: masterList.getBookList()){
                if(ret.getBookList().contains(b)){
                    break;
                }
                if(b.getDepartments().isEmpty()){
                    ret.addBook(b);
                }else{
                    for(String bookDep : b.getDepartments()) {
                        if (retDep.equalsIgnoreCase(bookDep)) {
                            ret.addBook(b);
                            break;
                        }
                    }
                }
            }*/
        //}
        //return ret.getBookList();
    //}

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

    public int dateHander(String date){
        if(date.matches("[a-zA-Z]") || !date.matches(".*[0-9].*")){
            System.out.println("Bad search: not a valid date");
            return -1;
        }
        String[] dateChop = date.split("/");
        if(!(dateChop.length - 3 == 0)){
            System.out.println("Bad search: not a valid date");
            return -1;
        }
        if(!(dateChop[0].length() - 2 == 0) || !(dateChop[1].length() - 2 == 0) || !(dateChop[2].length() - 4 == 0)){
            System.out.println("Bad search: not a valid date");
            return -1;
        }
        int[] daysOfMonth = {13,28,31,30,31,30,31,31,30,31,30,31};
        return Integer.parseInt(dateChop[0]) + daysOfMonth[Integer.parseInt(dateChop[1]) - 1] + (365 * Integer.parseInt(dateChop[2]));
    }



}
