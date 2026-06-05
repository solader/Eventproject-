package com.projetFe.Event.dto;

import com.projetFe.Event.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrgdto {

    private String nom ;
    private String prenom ;
    private int age ;
    private String ville ;
    // private String region ;
    private  String adresse ;
    private String phone ;
    private String email ;
    private String password ;
    private String Cne ;
    //private Image imagecarte ;
    // private Image photo ;


}
