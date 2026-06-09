package com.projetFe.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Appuser")
public class Appuser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String nom ;
    private String prenom ;
    private int age ; // Ou on faire int
    private String ville_residence ;
    private String adresse_local ;
    private String phone ;
    private String email ;
    private String password ;
    private String confirm_password ;

    private String Cne ;
    @OneToOne( cascade = CascadeType.ALL ,  fetch = FetchType.EAGER)
    @JoinColumn(name = "imagecarte_id")
    // seulement pour l’organisateur
    private Image imagecarte ;
    /*
    @OneToOne( cascade = CascadeType.ALL  ,  fetch = FetchType.EAGER )
    @JoinColumn(name = "imagephoto_id")
    private Image photo ;

     */

    private String code_acces ;

    @Enumerated(EnumType.ORDINAL)
    private Role role = Role.ORGANISATEUR;
    public Appuser (){}

    public Appuser(String nom, String prenom, int age,String ville , String phone, String email, String password ,String confirm_password , Role role ) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville_residence  = ville ;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        //if(role.equals(Role.ORGANISATEUR) || role.equals(Role.RESERVEUR))
        this.role = role ;
    }


    @OneToMany (mappedBy="user")
    @JsonIgnore
    private  List<Event> listevent= new ArrayList<>() ;



    @OneToMany (mappedBy = "user_R")
    @JsonIgnore
    private List<Reservation> listR= new ArrayList <>();
}
