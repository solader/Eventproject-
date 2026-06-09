package com.projetFe.Event.dto;

import com.projetFe.Event.Image;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserReserveurdto {
    private String nom ;
    private String prenom ;
    private int age ;
    private String ville_residence ;
    // private String region ;
    private String phone ;
    private String email ;
    private String password ;



}
