package com.projetFe.Event.service;

import com.projetFe.Event.Categorie_billet_prix;
import com.projetFe.Event.Event;
import com.projetFe.Event.ReservationCategorie;
import com.projetFe.Event.Typeevent;
import com.projetFe.Event.exception.ResourceNotFoundException;
import com.projetFe.Event.repository.Eventrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    Eventrepository eventrepository;

    public void desincrementerplace(Event ev, List<ReservationCategorie> listcat) throws ResourceNotFoundException {
        for (ReservationCategorie categorie : listcat) {
            for (Categorie_billet_prix cat : ev.getListCategories()) {
                if (categorie.getCategoriebilletprix().equals(cat)) ;
                {
                    int n = cat.getReste();
                    int m = categorie.getQte();
                    if (n < m) throw new ResourceNotFoundException("");
                    n = n - m;
                    cat.setReste(n);
                    break;
                }

            }
        }


    }
    public Double calculefraisapp(long id, int qte, double prix) {
        Event event = eventrepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        if (event.getTypeevent().equals(Typeevent.THEATRE) || event.getTypeevent().equals(Typeevent.SPECTACLE)) {
            return (prix * qte) * 0.1;
        } else {
            if (event.getTypeevent().equals(Typeevent.FESTIVAL)) {
                return (prix * qte) * 0.12;
            } else {
                if (event.getTypeevent().equals(Typeevent.CONFERENCE) || event.getTypeevent().equals(Typeevent.SPORT) || event.getTypeevent().equals(Typeevent.FORMATION)) {
                    return (prix * qte) * 0.08;
                } else // (event.getTypeevent().equals(Typeevent.SOIREE))
                    return (prix * qte) * 0.09;

            }
        }


    }
}
