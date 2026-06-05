package com.projetFe.Event.dto;

import com.projetFe.Event.Categorie_billet_prix;

public class Resermap {
    public static ReservationcatDto retourne (Categorie_billet_prix cat){
        ReservationcatDto categorie = new ReservationcatDto() ;
        categorie.setCategorie(cat.getCategorie());
        categorie.setPrix(cat.getPrix());
        return categorie ;

    }
}
