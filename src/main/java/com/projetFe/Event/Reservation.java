package com.projetFe.Event;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private long id ;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private LocalDate date_reservation ;
        private double totalPrixR ;
        // private String modePaiment ;


        @ManyToOne
        @JoinColumn(name ="user_R")
        @JsonIgnoreProperties
        private Appuser user_R ;
        @ManyToOne
        @JoinColumn(name="event_R")
        @JsonIgnoreProperties
        private Event evvent;
        @OneToMany(mappedBy = "reservationcat", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
        @JsonIgnoreProperties
        private List<ReservationCategorie> details = new ArrayList<>() ;


        @OneToMany (mappedBy = "reservation")
        @JsonIgnore
        private List<Participant> participantList = new ArrayList<>()  ;

    }
