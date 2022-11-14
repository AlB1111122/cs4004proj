package org.example;

import java.time.LocalDate;

public class Reservation extends Loan{
   
   
    private LocalDate reservationDate;

    Reservation(Book book, Person loanedTo, String reservationString)throws RuntimeException{
        super(book, loanedTo);
        reservationDate = LocalDate.parse(reservationString); // converts string to LocalDate
        super.setDateTaken(reservationDate);
        super.setDueDate(reservationDate.plusWeeks(3));//3 week loan)
    }

    public LocalDate getReservationDate() {
        return reservationDate;

    } 

    public void setReservationDate(LocalDate reservationDate){
        this.reservationDate = reservationDate;

    }   

}

  
