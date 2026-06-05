package com.projetFe.Event.restcontroller;

import com.projetFe.Event.*;
import com.projetFe.Event.dto.*;
import com.projetFe.Event.service.ReservationService;
import com.projetFe.Event.exception.ResourceNotFoundException;
import com.projetFe.Event.repository.Appuser_repository;
import com.projetFe.Event.repository.Eventrepository;
import com.projetFe.Event.repository.Reservationrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")
public class Reservationcont {
    @Autowired
    Reservationrepository reservationrepository ;
    @Autowired
    Appuser_repository appuserRepository ;
    @Autowired
    Eventrepository eventrepository ;
    @Autowired
    ReservationService reservationService ;

    @PostMapping
    public ResponseEntity<Reservation>postreservation(@RequestBody ReservationRequestdto res) throws Exception {

        Long userId = res.getUser_R();
        Long eventId = res.getEvvent();


        Appuser user = appuserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Event event = eventrepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));


        Reservation reservation = new Reservation();
        reservation.setUser_R(user);
        reservation.setEvvent(event);
        reservation.setDate_reservation(res.getDateReservation());



        List<ReservationCategorie> detail =new ArrayList<>();
        double total = 0 ;
        for(Categorie_billet_prix cate: event.getListCategories()){
           for (Categorie_qteDto cat : res.getCategories()) {
                    if(cat.getCat().getCategorie().equals(cate.getCategorie())) {
                        ReservationCategorie categorie = new ReservationCategorie();
                        categorie.setCategoriebilletprix(cate);
                        if (cate.getReste() - cat.getQuantite() < 0 ) throw new Exception() ;
                        cate.setReste(cate.getReste()-cat.getQuantite());

                        categorie.setQte(cat.getQuantite());
                        categorie.setTotalprix(cat.getQuantite() * cat.getCat().getPrix());
                        total = total + cat.getQuantite() * cat.getCat().getPrix() ;
                        Double n =reservationService.calculefraisapp(eventId, cat.getQuantite(), cat.getCat().getPrix());
                        categorie.setFraisApplication(n);

                        categorie.setReservationcat(reservation); //?
                        detail.add(categorie) ;
                    }

                }

            }
        reservation.setTotalPrixR(total);
        reservation.setDetails(detail);
        // Sauvegarde en cascade
        Reservation savedReservation = reservationrepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservationdto> getReservation (@PathVariable ("id") Long n){
        Reservation res = reservationrepository.findById(n).orElseThrow(null);

        Reservationdto dto = Reservationmapper.retourneR(res);
        return ResponseEntity.ok(dto);
        /*return reservationrepository.findById(n)
                .map(reservation -> ResponseEntity.ok(Reservationmapper.retourneR(reservation)))
                .orElse(ResponseEntity.notFound().build());*/

    }
    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<Categorie_qteDto>> geteReservation (@PathVariable ("id") Long n) {
        Event event = eventrepository.findById(n).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        List<ReservationCategorie> list = new ArrayList<>();

        for (Categorie_billet_prix cat : event.getListCategories()) {
            ReservationCategorie categorie = new ReservationCategorie();
            categorie.setCategoriebilletprix(cat);
            categorie.setQte(0);
            list.add(categorie);
        }
        List<Categorie_qteDto> liste = list.stream()
                .map(Categoriemapper::retournecat)
                .collect(Collectors.toList());

        return ResponseEntity.ok(liste) ;
    }

    @PostMapping ("enregistrer/{id}") //ID de event
    public List<ReservationCategorie> enregistrer (List<Categorie_qteDto> list , Long id ){
        Event event = eventrepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        List<ReservationCategorie> listr = new ArrayList<>();
        for(Categorie_billet_prix cate: event.getListCategories()) {
            for (Categorie_qteDto cat : list) {
                if (cat.getCat().getCategorie().equals(cate.getCategorie())) {
                    ReservationCategorie categorie = new ReservationCategorie();
                    categorie.setCategoriebilletprix(cate);
                    categorie.setQte(cat.getQuantite());
                    categorie.setTotalprix(cat.getQuantite() * cat.getCat().getPrix());
                    Double n =reservationService.calculefraisapp(id, cat.getQuantite(), cat.getCat().getPrix());
                    categorie.setFraisApplication(n);
                    //categorie.setReservationcat(); //?
                    listr.add(categorie) ;
                }

            }

        }
        return listr  ;
}
@DeleteMapping("/deleteR/{id}")
public void delete(Long n){
    eventrepository.findById(n).orElseThrow(() -> new ResourceNotFoundException("Erreur:aucune Reservation"));
    reservationrepository.deleteById(n);

}
}
