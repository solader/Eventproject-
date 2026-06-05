package com.projetFe.Event.dto;


import com.projetFe.Event.Categorie_billet_prix;
import lombok.Data;

@Data
public class Categorie_qteDto {
    private ReservationcatDto cat ;
    private int quantite ;
}
