package org.example;

import java.time.LocalDate;

public class Reservation extends Loan{
    private LocalDate reservationDate;

    Reservation(Book book, Person loanedTo, LocalDate reservationDate)throws RuntimeException{
        super(book, loanedTo);
        this.reservationDate = reservationDate;
        super.setDateTaken(reservationDate);
        super.setDueDate(reservationDate.plusWeeks(3));//3 week loan)
    }

    public LocalDate getReservationDate() {
        return reservationDate;

    }

    public void setReservationDate(LocalDate reservationDate){
        this.reservationDate = reservationDate;
    }

    public String toString(){
        return String.format("Reservation: %s",super.toString());
    }
}

  