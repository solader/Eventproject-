package com.projetFe.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorie_billet_prix {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;

     private String categorie;
     private int nbrplace ;
     private int reste ;
     private Double prix ;
     public Categorie_billet_prix(Double prix,int nbrplace, String categorie) {
        this.prix = prix;
        this.nbrplace = nbrplace ;
        this.categorie = categorie;
        this.reste = nbrplace;

    }


     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="id_event")
     @JsonIgnore
     private Event event ;


    @JsonIgnore

    //@JsonIgnoreProperties
    @OneToMany (mappedBy = "categoriebilletprix")
    private List<ReservationCategorie> reservationCategorieList = new ArrayList<>();


    @OneToMany (mappedBy = "categoriepar")
    @JsonIgnore
    private  List<Participant> participantList = new ArrayList<>();
}
