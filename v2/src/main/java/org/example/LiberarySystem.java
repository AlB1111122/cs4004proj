package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class LiberarySystem{
    public static String in;
    private BookList masterList = new BookList();

    
    
    public void registerUser{

public static void main(String[] args) 
    {
    Person p = new Person();

  public static string emailCheck(String email) {
    if(!email.contains("@") || !email.contains(".") || !email.matches(".*[a-zA-Z].*")){//makes sure email is an email
        throw new emailException("Cannot add this email. That's not an email");
    return emailCheck;

  }


  //scanner starts
  public static void main(String[] args) { 
    Scanner input = new Scanner(System.in);

    //enter name, email, department(s)
    System.out.print("Enter Name and Email: ");
    String name = input.next();
    String email = input.next();
    String departments = input.next(); 


    try {
      this.name = p.name;
      this.email = p.email;
      String[] arr = p.departments.split(", ");//splits up departments string and adds to arraylist
      this.departments.addAll(Arrays.asList(arr));



    }
    catch (emailException ex) {
      System.out.println("Please try again.");
    }

    System.out.println("Execution continues ...");
  }
}
}
}
    
    
    
    
    
    
    
    
    public void addBook(Book book){
        masterList.addBook(book);
    }

    public ArrayList<Book> getBookList(){
        return masterList.getBookList();
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

}
