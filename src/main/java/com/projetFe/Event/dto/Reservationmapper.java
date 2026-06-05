package com.projetFe.Event.dto;

import com.projetFe.Event.Reservation;

public class Reservationmapper {
    public static Reservationdto retourneR (Reservation res) {
        Reservationdto reservationdto = new Reservationdto();
        reservationdto.setDate_reservation(res.getDate_reservation());



        reservationdto.setDetails(res.getDetails());
        return reservationdto;


    }
}