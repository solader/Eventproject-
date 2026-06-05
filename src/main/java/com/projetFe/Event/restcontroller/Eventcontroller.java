package com.projetFe.Event.restcontroller;


//import com.projetFe.Event.Categorie_billet_prix;
import com.projetFe.Event.*;
import com.projetFe.Event.dto.*;
import com.projetFe.Event.exception.ResourceNotFoundException;
import com.projetFe.Event.repository.Appuser_repository;
import com.projetFe.Event.repository.Eventrepository;
import com.projetFe.Event.repository.Reservationrepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class Eventcontroller {
    @Autowired
    Eventrepository eventrepository;
    @Autowired
    Appuser_repository appuserRepository;
    @Autowired
    Reservationrepository reservationrepository;

    //Methode ne besion pas
    @PostMapping("/post")
    public ResponseEntity<Event> postEvent(@RequestBody Event ev) {
        Event eventsave = eventrepository.save(ev);
        for (Categorie_billet_prix categorie : eventsave.getListCategories()) {
            categorie.setEvent(eventsave);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(eventrepository.save(eventsave));
    }

    // retourne event avec tous ses informations
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> retourneEvent(@PathVariable("id") long n) {
        eventrepository.findById(n).orElseThrow(() -> new ResourceNotFoundException("Event introuvable"));

        return eventrepository.findById(n)
                .map(event -> ResponseEntity.ok(Eventmapper.retourneDto(event)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter event et affecter à leur organiseur
    @PostMapping("/addevent")
    public ResponseEntity<Eventdtouser> posteevent(@RequestBody Event ev) {
        Event eventsave = eventrepository.save(ev);

        long n = ev.getUser().getId();
        Appuser user = appuserRepository.findById(n).orElseThrow(() -> new ResourceNotFoundException("user avec cette ID " + n + " introuvable")); // Le n existe deja !
        ev.setUser(user);
        for (Categorie_billet_prix categorie : ev.getListCategories()) {
            categorie.setReste(categorie.getNbrplace());
            categorie.setEvent(ev);
        }
        Event savedEvent = eventrepository.save(ev);
        return eventrepository.findById(ev.getId())
                .map(event -> ResponseEntity.ok(Eventmapper.retourneEvuser(event)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("{id}")
    public ResponseEntity<Eventdtouser> modifierEvent(@PathVariable("id") long n, @RequestBody Event ev) {
        eventrepository.findById(n).orElseThrow(() -> new ResourceNotFoundException("Event introuvable"));
        ev.setId(n);
        Event savedEvent = eventrepository.save(ev);
        return ResponseEntity.ok(Eventmapper.retourneEvuser(savedEvent));
    }

    // retourne allevent for chaque iduser

    @GetMapping("/all")
    public ResponseEntity<List<EventdtoAcc>> getAllEvents() {
        List<Event> events = eventrepository.findByStatuevent(Statuevent.VALIDE_PUBLIE);
        // Convertir la liste d'events en liste de DTOs
        List<EventdtoAcc> dtos = events.stream()
                .map(Eventmapper::retourneallDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/all/enattente")
    public ResponseEntity<List<Eventstatu>> AllEventstatu() {
        List<Event> events = eventrepository.findByStatuevent(Statuevent.EN_ATTENTE);
        // Convertir la liste d'events en liste de DTOs
        List<Eventstatu> dtos = events.stream()
                .map(Eventmapper::retourneallstatu)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/all/enrefuse")
    public ResponseEntity<List<Eventstatu>> AllEventstatuR() {
        List<Event> events = eventrepository.findByStatuevent(Statuevent.REFUSE);
        // Convertir la liste d'events en liste de DTOs
        List<Eventstatu> dtos = events.stream()
                .map(Eventmapper::retourneallstatu)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/sta/{id}")
    public ResponseEntity<EventSta> eventstatistique(@PathVariable("id") Long id) {
        Event event = eventrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event introuvable"));
        List<Reservation> liste = new ArrayList<>();
        liste = reservationrepository.findByEvventId(id);
        List<EventStatistiquedto> lists = new ArrayList<>();
        for (Categorie_billet_prix cat : event.getListCategories()) {
            EventStatistiquedto ev = new EventStatistiquedto();
            ev.setCategorie(cat.getCategorie());
            ev.setFrais(0);
            ev.setQuantitetotal(0);
            ev.setFrais(0);
            lists.add(ev);
        }
        for (Reservation res : liste) {
            for (ReservationCategorie categorie : res.getDetails()) {
                for (EventStatistiquedto eve : lists) {


                    if (categorie.getCategoriebilletprix().getCategorie().equals(eve.getCategorie())) {

                        eve.setQuantitetotal(eve.getQuantitetotal() + categorie.getQte());
                        eve.setFrais(eve.getFrais() + categorie.getFraisApplication());
                        eve.setNbrplace(categorie.getCategoriebilletprix().getNbrplace());
                        eve.setTotalcat(eve.getTotalcat() + categorie.getTotalprix());
                        eve.setTaux((eve.getQuantitetotal()*100) / eve.getNbrplace());
                    }
                }
            }
        }
            int quantitetotalev = 0;
            double totalev = 0;
            double totalfrais = 0;
            EventSta eventSta = new EventSta();
            for (EventStatistiquedto eventStatistiquedto : lists) {
                quantitetotalev = quantitetotalev + eventStatistiquedto.getQuantitetotal();
                totalev = totalev + eventStatistiquedto.getTotalcat();
                totalfrais = totalfrais + eventStatistiquedto.getFrais();
            }
            eventSta.setQtetotal(quantitetotalev);
            eventSta.setTotalev(totalev);
            eventSta.setTotalF(totalfrais);
            eventSta.setEventSta(lists);

            return ResponseEntity.ok(eventSta);



    }
}