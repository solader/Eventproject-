package com.projetFe.Event.dto;

import com.projetFe.Event.Categorie_billet_prix;
import com.projetFe.Event.ReservationCategorie;

import static com.projetFe.Event.dto.Resermap.retourne;

public class Categoriemapper {


     public static Categorie_qteDto retournecat (ReservationCategorie cat){
         Categorie_qteDto categorie = new Categorie_qteDto() ;
         categorie.setQuantite(cat.getQte());
         ReservationcatDto var =  Resermap.retourne(cat.getCategoriebilletprix()) ;
         categorie.setCat(var);
         return  categorie ;
     }

}
