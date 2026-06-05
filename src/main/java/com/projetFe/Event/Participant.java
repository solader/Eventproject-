package com.projetFe.Event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom ;
    private String prenom ;
    private int age ;
    private String email ;
    private String phone ;

    @ManyToOne
    Categorie_billet_prix categoriepar ;

    @ManyToOne
    @JoinColumn (name="reservation")
    private Reservation reservation ;

}
