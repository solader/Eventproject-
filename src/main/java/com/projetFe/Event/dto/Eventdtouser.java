package com.projetFe.Event.dto;

import com.projetFe.Event.Appuser;
import com.projetFe.Event.Categorie_billet_prix;
import com.projetFe.Event.Typeevent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Eventdtouser {
    private String titre_event;
    private LocalDate date_event;
    private LocalTime heure_event;
    private String ville_event;
    private String adresse_salle;
    private String description;
    private Typeevent typeevent;
    private List<Categorie_billet_prix> listCategories = new ArrayList<>();
    private Appuser user ;
}
