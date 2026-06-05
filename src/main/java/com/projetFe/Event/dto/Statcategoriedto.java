package com.projetFe.Event.dto;

import lombok.Data;

@Data
public class Statcategoriedto {

    private String categorie;
    private int totalQuantite;
    private double totalPrix;

    public Statcategoriedto (String categorie, int totalQuantite, double totalPrix) {
        this.categorie = categorie;
        this.totalQuantite = totalQuantite;
        this.totalPrix = totalPrix;
    }
}
