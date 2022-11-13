package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LiberaryMenu{
    private Scanner in;

    public LiberaryMenu(){
        in = new Scanner(System.in);
    }

    //this is the runner
    //for the avoidence of plagerism accusations this class is based off the cs4013 lab 6 vending machine menu
    public void run(LiberarySystem sys) throws IOException{
        boolean more = true;
        while(more) {
            signInPage(sys);
            homePage(sys);
        }
    }
    public void signInPage(LiberarySystem sys){
        System.out.println("Please sign in to your UWON Library account, or press '0' to create a new account\nEnter ID:");
        boolean blocker = true;
        while(blocker){
            String singInU = in.nextLine();
            if(singInU.equals("0")) {
                //createNewUserPage(sys);
            }
            try {
                sys.getPerson(singInU);
                blocker = false;
            }catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                //start the loop again insted of completly closing
            }
            System.out.println("Enter password:");
            while(!sys.signIn(in.nextLine())){
                System.out.println("That password is incorrect, please re-enter");
            }
        }
    }

    public void homePage(LiberarySystem sys){
        boolean blocker = true;
        String menuMov = "";
        while(blocker){
            System.out.print("1)Search for a book. 2)See staff. 3)See my loans");
            if (sys.getSignedIn().isStaff()) {
                System.out.print(". 0)Open staff menu");
            }
            System.out.println("");
            menuMov = in.nextLine();
            if(menuMov.equals("0") && sys.getSignedIn().isStaff()){
                //staffOnlyPage(sys);
            }
            if(Integer.parseInt(menuMov) < 0 || Integer.parseInt(menuMov) > 3){
                System.out.println("Thats not a valid input");
            }else{
                blocker = false;
            }
        }
        if(menuMov.equals("1")){
            searchPage(sys);
        }
    }



    public void searchPage(LiberarySystem sys){
        ArrayList<Book> results = new ArrayList<>();
        boolean more = true;
        while(more){
            boolean blocker = true;
            while(blocker) {
                System.out.println("0)Back to homepage. 1)Search books in my department. 2)Serch all books");
                String input = in.nextLine();
                if (input.equals("0")) {
                    blocker = false;
                    more = false;
                    results.clear();
                }else if (input.equals("1")) {
                    results = sys.getBookList(sys.getSignedIn().getDepartmentString());
                    if(results.isEmpty()){
                        System.out.println("No relevant books found");
                        break;
                    }
                    blocker = false;
                }else if (input.equals("2")) {
                    results = sys.getBookList();
                    if(results.isEmpty()){
                        System.out.println("No relevant books found");
                        break;
                    }
                    blocker = false;
                } else{
                    System.out.println("not a valid input, please try again");
                }
            }
            System.out.println("Enter author name: (if you enter nothing all authors will be included in results)");
            String inputAuth = in.nextLine();
            if(!inputAuth.equals("")) {
                results.removeIf(b -> !b.getAuthor().contains(inputAuth));
                if(results.isEmpty()){
                    System.out.println("No relevant books found");
                    break;
                }
            }
            System.out.println("Enter the minimum date you want results for in the format DD/MM/YYYY: (if you enter nothing there will be no minimum date)");
            String dateMin = in.nextLine();
            if(!dateMin.equals("")) {
                for(Book b:results){
                    if(dateHander(dateMin) < 0){
                        more = false;
                    }if(dateHander(b.getReleaseDate()) < dateHander(dateMin)){
                        results.remove(b);
                        if(results.isEmpty()){
                            System.out.println("No relevant books found");
                            break;
                        }
                    }
                }
            }
            System.out.println("Enter the maximum date you want results for in the format DD/MM/YYYY: (if you enter nothing there will be no maximum date)");
            String dateMax = in.nextLine();
            if(!dateMax.equals("")) {
                for(Book b:results){
                    if(dateHander(dateMax) < 0){
                        more = false;
                    }if(dateHander(b.getReleaseDate()) > dateHander(dateMax)){
                        results.remove(b);
                        if(results.isEmpty()){
                            System.out.println("No relevant books found");
                            break;
                        }
                    }
                }
            }
            for(Book b:results){
                System.out.println(b.toString() + " Avalible for reservation/loan:" + b.getAvailble());
            }
        }

        //put in loan/reserve/read method
        homePage(sys);
    }

    private int dateHander(String date){
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
        return Integer.parseInt(dateChop[0]) + daysOfMonth[Integer.parseInt(dateChop[1])] + (365 * Integer.parseInt(dateChop[2]));
    }


}


    //public void createNewUserPage(LiberarySystem sys){//this only lets you make student accounts staff accounts are added in the staff only page
        //System.out.println();
    //}


