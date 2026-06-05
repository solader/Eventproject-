package com.projetFe.Event;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCategorie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    private int qte ;
    private double totalprix ;
    private double fraisApplication;
    @ManyToOne
    private Categorie_billet_prix categoriebilletprix ;
    @ManyToOne
    @JoinColumn(name="reservationcat")
    @JsonIgnore
    @JsonIgnoreProperties
    private Reservation reservationcat ;


}
