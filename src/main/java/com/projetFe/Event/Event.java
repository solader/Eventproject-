package com.projetFe.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre_event;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate date_event;
    private LocalTime heure_event;
    private String ville_event;
    private String adresse_salle;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Statuevent statuevent = Statuevent.EN_ATTENTE;
    @Enumerated(EnumType.ORDINAL)
    private Typeevent typeevent = Typeevent.THEATRE ;
    @OneToMany (mappedBy = "event" , cascade=CascadeType.ALL , fetch = FetchType.EAGER)
    // @JsonIgnoreProperties("event")
    private List<Categorie_billet_prix> listCategories = new ArrayList<>() ;
    @OneToMany (mappedBy = "eventI")
    @JsonIgnore
    private List <Image> listimages = new ArrayList<>() ;
    @ManyToOne
    @JoinColumn(name = "id_organiseur")
    private Appuser user ;
    @OneToMany (mappedBy = "evvent")
    @JsonIgnore
    private List<Reservation> listeReservation = new ArrayList<>() ;

}
