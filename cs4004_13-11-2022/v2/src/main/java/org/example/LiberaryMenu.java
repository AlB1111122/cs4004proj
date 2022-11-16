package org.example;
import java.io.IOException;
import java.time.LocalDate;
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
        }
    }
    public void signInPage(LiberarySystem sys){
        System.out.println("Please sign in to your UWON Library account, or press '0' to create a new account. 9)Exit\nEnter ID:");
        boolean blocker = true;
        while(blocker){
            String singInU = in.nextLine();
            if(singInU.equals("9")){
                System.exit(1);
            }
            if(singInU.equals("")){
                return;
            }
            if(singInU.equals("0")) {
                System.out.println("Are you a staff member? Y/N");
                String newIsStaff = in.nextLine();
                System.out.println("Enter name:");
                String newName = in.nextLine();
                System.out.println("Enter email:");
                String newEmail = in.nextLine();
                System.out.println("Enter departments: (Separate each department with a comma and space e.g. a, b, c)");
                String newDepartments = in.nextLine();
                System.out.println("Enter password:");
                String newPassword = in.nextLine();
                System.out.println("Enter new ID:");
                int newID = Integer.parseInt(in.nextLine());

                sys.registerUser(newIsStaff, newName, newEmail, newDepartments, newPassword, newID);
            }
            try {
                sys.getPerson(singInU);
                blocker = false;
            }catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                return;
                //start the loop again insted of completly closing
            }
            System.out.println("Enter password:");
            while(!sys.signIn(in.nextLine())){
                System.out.println("That password is incorrect, please re-enter");
            }
        }
        homePage(sys);
    }

    public void homePage(LiberarySystem sys){
        boolean blocker = true;
        boolean more = true;
        String menuMov = "";
        while(more) {
            while (blocker) {
                System.out.print("1)Search for a book. 2)See staff. 3)See my loans. 9)Exit");
                if (sys.getSignedIn().isStaff()) {
                    System.out.print(". 0)Open staff menu");
                }
                System.out.println("");
                menuMov = in.nextLine();
                if(menuMov.equals("9")){
                    System.exit(1);
                }
                if (menuMov.equals("0") && sys.getSignedIn().isStaff()) {
                    staffOnlyPage(sys);
                }else if (menuMov.equals("1")) {
                    searchPage(sys);
                }else if(menuMov.equals("2")){
                    listStaffPage(sys);
                }else if(menuMov.equals("3")){
                    loanPage(sys);
                }else{
                    System.out.println("invalid input");
                }
            }
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
                    homePage(sys);
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
            ArrayList<Book> removing = new ArrayList<>();
            if(!dateMin.equals("")) {
                for(Book b:results){
                    if(sys.dateHander(dateMin,b.getReleaseDate()) < 0){
                        more = false;
                    }else if(sys.dateHander(dateMin,b.getReleaseDate()) > 0){
                        removing.add(b);
                    }
                }
                for(Book b:removing){
                    results.remove(b);
                }
                removing.clear();
                if(results.isEmpty()){
                    System.out.println("No relevant books found");
                    break;
                }
            }
            System.out.println("Enter the maximum date you want results for in the format DD/MM/YYYY: (if you enter nothing there will be no maximum date)");
            String dateMax = in.nextLine();
            if(!dateMax.equals("")) {
                for(Book b:results){
                    if(sys.dateHander(dateMax,b.getReleaseDate()) < 0){
                        more = false;
                    }else if(sys.dateHander(b.getReleaseDate(),dateMax) > 0){
                        removing.add(b);
                    }
                }
            }
            for(Book b:removing){
                results.remove(b);
            }
            removing.clear();
            if(results.isEmpty()){
                System.out.println("No relevant books found");
                break;
            }
            System.out.println("Enter the books title: (if you enter nothing all titles will be included in results)");
            String inputTitle = in.nextLine();
            if(!inputTitle.equals("")) {
                results.removeIf(b -> !b.getTitle().toLowerCase().contains(inputTitle.toLowerCase()));
                if(results.isEmpty()){
                    System.out.println("No relevant books found");
                    break;
                }
            }
            System.out.println("Enter the books Edition: (if you enter nothing all editions will be included in results)");
            String inputEdition = in.nextLine();
            if(!inputEdition.equals("")) {
                results.removeIf(b -> !b.getEdition().toLowerCase().contains(inputEdition.toLowerCase()));
                if(results.isEmpty()){
                    System.out.println("No relevant books found");
                    break;
                }
            }
            System.out.println("Enter the a specific department to search within: (if you enter nothing all departments will be included in results)");
            String inputDep = in.nextLine();
            if(!inputDep.equals("")) {
                for(Book b: results){
                    if(!b.getDepartments().contains(inputDep)){
                        removing.add(b);
                    }
                }
            }
            for(Book b:removing){
                results.remove(b);
            }
            removing.clear();
            if(results.isEmpty()){
                System.out.println("No relevant books found");
                break;
            }
            searchResultsPage(results,sys);
        }


    }

    public void searchResultsPage(ArrayList<Book> results, LiberarySystem sys){
        int i = 1;
        boolean more = true;
        boolean blocker = true;
        Book selected = null;
        while(more) {
            while (blocker) {
                System.out.println("0)Return to search page. Please select a book");
                for (Book b : results) {
                    System.out.println(i + ") " + b.toString());
                    i++;
                }
                i = 1;
                String input = in.nextLine();
                if(input.equals("0")){
                    return;
                }
                try {
                    selected = results.get(Integer.parseInt(input) - 1);
                    blocker = false;
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    System.out.println("not a valid input, please try again");
                }
            }
            blocker = true;
            while(blocker) {
                System.out.println(selected);
                String mode = sys.getBookOps(selected);
                System.out.printf("0)Return to results page. %s\n",mode);
                String bookOpSel = in.nextLine();
                if(bookOpSel.equals("0")){
                    return;
                }
                if(bookOpSel.equals("1")){
                    if(selected instanceof Ebook){
                        System.out.println("~~use your imagination for the books content, wow so educational~~~");
                        break;
                    }else if(mode.contains("1)Loan book.")){
                        sys.getSignedIn().addLoan(new Loan(selected,sys.getSignedIn()));
                        System.out.println("You now may take this book");
                        break;
                    } else if(mode.contains("This book is unavailable")){
                        sys.getSignedIn().addLoan(new Reservation(selected,sys.getSignedIn(),selected.getUnavalibleUntil()));
                        System.out.printf("Collect this book on %s\n",selected.getUnavalibleUntil());
                        break;
                    }else{
                        break;
                    }
                }else if(bookOpSel.equals("2") && selected instanceof Ebook){
                    System.out.println("~~wow look how downloaded that book is~~~");
                    break;
                }else{
                    System.out.println("invalid input");
                    return;
                }
            }
        }
    }

    public void listStaffPage(LiberarySystem sys){
        boolean more = true;
        while(more) {
            String input = "";
                System.out.println("0)Return home. 1)Staff in my department. 2)Search by name. 3)Search by department");
                input = in.nextLine();
            if(input.equals("0")){
                homePage(sys);
            }else if(input.equals("1")){
                for(Person p:sys.getPeople()){
                    for(String s:sys.getSignedIn().getDepartments()) {
                        if (p.getDepartments().contains(s) && p.isStaff()) {
                            System.out.println(p);
                            break;
                        }
                    }
                }
            }else if(input.equals("2")){
                System.out.println("Enter name or part of the name");
                String nameSearch = in.nextLine();
                for(Person p:sys.getPeople()){
                    if (p.getName().contains(nameSearch) && p.isStaff()) {
                        System.out.println(p);
                        break;
                    }
                }
            }else if(input.equals("3")){
                System.out.println("Enter the name of the department");
                String depSearch = in.nextLine();
                for(Person p:sys.getPeople()){
                    for(String s:p.getDepartments()) {
                        if (s.contains(depSearch) && p.isStaff()){
                            System.out.println(p);
                            break;
                        }
                    }
                }
            }else{
                System.out.println("invalid input");
                break;
            }
        }
    }

    public void loanPage(LiberarySystem sys){
        ArrayList<Loan> loans = sys.getSignedIn().getLoans();
        String actions = "1)Return current loan";
        if(!loans.isEmpty()){
            for(Loan l:loans){
                System.out.println(l);
            }
            Loan lastL = loans.get(loans.size() - 1);
            if(!lastL.getReturnStatus()) {
                if (lastL instanceof Reservation) {
                    LocalDate temp = ((Reservation) lastL).getReservationDate().minusDays(1);
                    if (!LocalDate.now().isAfter(temp)) {
                        actions = "1)Cancel reservation";
                    }
                }
            }else{
                actions = "";
            }
            boolean blocker = true;
            String input = "";
            while(blocker){
                System.out.printf("0)Return home. %s\n",actions);
                input = in.nextLine();
                if(input.equals("1") && actions.contains("1)")){
                    lastL.returnBook();
                    System.out.println("Book returned");
                    blocker = false;
                }else if(input.equals(("0"))){
                    blocker = false;
                }else{
                    System.out.println("invalid input");
                }
            }
        }else{
            System.out.println("You have no loan history");
        }
        homePage(sys);
    }

    public void staffOnlyPage(LiberarySystem sys){
        boolean more = true;
        boolean blocker = true;
        while(more){
            while(blocker){
                System.out.println("0)Return home. 1)Add book. 2)Remove book. 3)Remove person");
                String input = in.nextLine();
                if(input.equals("0")){
                    more = false;
                }else if(input.equals("1")){
                    System.out.println("Enter the book information in the format: \n" +
                            "AUTHOR, DD/MM/YYYY(release date), TITLE, EDITION, PUBLISHER, DEPARTMENT(S) SEPARATED BY ', '");
                    String bookInfo = in.nextLine();
                    System.out.println("Is this book an Ebook? Y/N");
                    String ebook = in.nextLine();
                    if(ebook.equalsIgnoreCase("Y")){
                        sys.processNewBook(bookInfo,true);
                    }else if(ebook.equalsIgnoreCase("N")){
                        sys.processNewBook(bookInfo,false);
                    }else{
                        System.out.println("invalid input");
                        break;
                    }
                }else if(input.equals("2")){
                    //book search
                }else if(input.equals("3")){
                    System.out.println("Enter the ID number of the person you want to remove");
                    String inputID = in.nextLine();
                    if(inputID.matches(".*[a-zA-Z].*") || !inputID.matches(".*[0-9].*")){
                        System.out.println("invalid input");
                    }else{
                        for(Person p: sys.getPeople()){
                            if(p.getID() - Integer.parseInt(inputID) == 0){
                                sys.getPeople().remove(p);
                                System.out.println(p.getName() + " removed");
                                break;
                            }
                        }
                    }
                }else{
                    System.out.println("invalid input");
                }
            }
        }
    }
}
