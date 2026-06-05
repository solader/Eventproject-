package com.projetFe.Event.dto;

import com.projetFe.Event.Typeevent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventdtoAcc {

    private String titre_event;
    private LocalDate date_event;
    private LocalTime heure_event;
    private String ville_event ;
    private String adresse_salle ;
    private Typeevent typeevent ;
}
