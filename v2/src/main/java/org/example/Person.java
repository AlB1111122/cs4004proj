package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Person{
    private String name;
    private String email;
    private ArrayList<Loan> loans;
    private ArrayList<String> departments = new ArrayList<>();

    Person(String name, String email, String departments){//departmets the person can acess
        this.name = name;
        if(!email.contains("@") || !email.contains(".") || !email.matches(".*[a-zA-Z].*")){//makes sure email is an email
            throw new RuntimeException("Cannot add this email. That's not an email");
        }
        this.email = email;
        loans = new ArrayList<>();
        String[] arr = departments.split(", ");//splits up departments string and adds to arraylist
        this.departments.addAll(Arrays.asList(arr));
    }

    public void addLoan(Loan loan){
        for(Loan l:loans){
            if(l.toString().matches(loan.toString())){//cant take out 2 loans on the same book
                return;
            }
        }
        String loanNameEmail = loan.getLoanedTo().getName() + loan.getLoanedTo().getEmail();
        String perNameEmail = name + email;
        if(!loanNameEmail.equals(perNameEmail)){
            throw new RuntimeException("Cannot add this loan; was not taken out by this person");//cant take out a loan by another person(name + email togrther are unique)
        }
        loans.add(loan);
    }

    public void removeLoan(Loan loan){
        loans.remove(loan);
        
        
         public void addReservation(Loan reservation){
        for(Loan l:loans){
            if(l.toString().matches(loan.toString())){//cant take out 2 reserves on the same book
                return;
            }
        }
        String loanNameEmail = loan.getLoanedTo().getName() + loan.getLoanedTo().getEmail();
        String perNameEmail = name + email;
        Date dateTaken;
        Date dateReturned;
        if(!loanNameEmail.equals(perNameEmail ){
            throw new RuntimeException("Cannot add this loan; was not taken out by this person");//cant take out a loan by another person(name + email togrther are unique)
        }
        loans.add(reservation);

        //reservations are treated as loans with an offset date
    }

    public void removeReservation(Loan reservation){
        loans.remove(reservation);
    }
    

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        if(!email.contains("@") || !email.contains(".") || !email.matches(".*[a-zA-Z].*")){
            throw new RuntimeException("Cannot add this email. That's not an email");
        }
        this.email = email;
    }

    public ArrayList<Loan> getLoans(){
        return loans;
    }

    public String toString() {
        return String.format("Name: %s, Email: %s", name,email);
    }

    public void addDepartments(String dep){
        String[] arr = dep.split(", ");
        this.departments.addAll(Arrays.asList(arr));
    }

    public ArrayList<String> getDepartments() {
        return departments;
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
        return str.toString();//string just of departmets
    }
}
