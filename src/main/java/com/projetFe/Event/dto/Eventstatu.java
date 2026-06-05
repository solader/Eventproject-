package com.projetFe.Event.dto;

import com.projetFe.Event.Statuevent;
import com.projetFe.Event.Typeevent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Eventstatu {
    private String titre_event;
    private LocalDate date_event;
    private LocalTime heure_event;
    private String ville_event ;
    private String adresse_salle ;
    private Typeevent typeevent ;
    private Statuevent statuevent ;
}
