package com.projetFe.Event.dto;

import com.projetFe.Event.Appuser;
import com.projetFe.Event.Event;
import com.projetFe.Event.ReservationCategorie;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class Reservationdto {
    private LocalDate date_reservation ;
    private Event event_R;
    private Appuser user_R;
    private List<ReservationCategorie> details = new ArrayList<>();

}
