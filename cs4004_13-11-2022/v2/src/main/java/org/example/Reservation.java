package org.example;

import java.time.LocalDate;

public class Reservation extends Loan{
   
    private int reservationDateOffset;
    private LocalDate reservationDate;
    

    Reservation(Book book, Person loanedTo, LocalDate reservationDate)throws RuntimeException{


        super(book, loanedTo);
        reservationDate = LocalDate.now();
        reservationDate =  reservationDate.plusDays(reservationDateOffset);
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

  