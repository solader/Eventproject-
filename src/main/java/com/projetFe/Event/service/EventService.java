package com.projetFe.Event.service;

import com.projetFe.Event.Categorie_billet_prix;
import com.projetFe.Event.Event;
import com.projetFe.Event.Reservation;
import com.projetFe.Event.ReservationCategorie;
import com.projetFe.Event.dto.Categorie_qteDto;
import com.projetFe.Event.dto.Categoriemapper;
import com.projetFe.Event.dto.EventStatistiquedto;
import com.projetFe.Event.dto.Statcategoriedto;
import com.projetFe.Event.exception.ResourceNotFoundException;
import com.projetFe.Event.repository.Eventrepository;
import com.projetFe.Event.repository.Reservationrepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventService {
    @Autowired
    private Eventrepository eventrepository ;
    @Autowired
    private Reservationrepository reservationrepository ;

public  List<Statcategoriedto> calculeStatistique (Long id ){
    Event event = eventrepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    List<Reservation> reservations = reservationrepository.findByEvventId(id);

    List<String> categories = event.getListCategories().stream()
            .map(Categorie_billet_prix::getCategorie)
            .collect(Collectors.toList());

    Map<String, Statcategoriedto> statsMap = new HashMap<>();
    for (String cat : categories) {
        statsMap.put(cat, new Statcategoriedto(cat, 0, 0.0));
    }


    for (Reservation reservation : reservations) {
        List<Categorie_qteDto> liste = reservation.getDetails().stream()
                .map(Categoriemapper::retournecat)
                .collect(Collectors.toList());

        for (Categorie_qteDto catDto : liste) {
            String nomCategorie = catDto.getCat().getCategorie();
            int quantite = catDto.getQuantite();
            double prix = catDto.getCat().getPrix() ;

            if (statsMap.containsKey(nomCategorie)) {
                Statcategoriedto stat = statsMap.get(nomCategorie);
                stat.setTotalQuantite(stat.getTotalQuantite() + quantite);
                stat.setTotalPrix(stat.getTotalPrix() + (quantite * prix));
            }
        }
    }

    return new ArrayList<>(statsMap.values());
}
}
