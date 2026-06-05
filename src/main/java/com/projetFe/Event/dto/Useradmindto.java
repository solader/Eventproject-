package com.projetFe.Event.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Useradmindto {

    private String nom ;
    private String prenom ;
    private int age ; // Ou on faire int
    private String ville ;
    private String adresse ;
    private String phone ;
    private String email ;
    private String code_acces ;
}
