package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Person{
    private boolean staff;
    private int ID;
    private String password;
    private String name;
    private String email;
    private ArrayList<Loan> loans;
    private ArrayList<String> departments = new ArrayList<>();

    Person(boolean staff, String name, String email, String departments, String password, int ID){//departmets the person can acess
        this.staff = staff;
        this.ID = ID;
        this.password = password;
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
    }

    public boolean isStaff(){
        return staff;
    }

    public void setStaff(boolean newb){
        staff = newb;
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

    public void addDepartments(String dep){
        String[] arr = dep.split(", ");
        this.departments.addAll(Arrays.asList(arr));
    }

    public ArrayList<String> getDepartments() {
        return departments;
    }

    public int getID() {
        return ID;
    }

    public String getPassword() {
        return password;
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

    public String toString(){
        String str = "";
        if(staff){
            str = "Staff Member";
        }else{
            str = "Student";
        }
        return String.format("%s:\nID: %d, Name: %s, Email: %s, departments: %s",str,ID,name,email,getDepartmentString());
    }
}
