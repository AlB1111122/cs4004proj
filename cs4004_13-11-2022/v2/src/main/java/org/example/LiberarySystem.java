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
        //if(book.isEbook()){
         //   return "1)Read ebook online. 2)Download ebook";
        //}
        if(!book.getAvailble()){
            return String.format("This book is unavailable for loan untill %s",book);
        }
        return "placeholder";
    }



}
