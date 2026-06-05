package com.projetFe.Event.restcontroller;


import com.projetFe.Event.*;
import com.projetFe.Event.repository.Appuser_repository;
import com.projetFe.Event.repository.ParticipantRepository;
import com.projetFe.Event.repository.Reservationrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private Appuser_repository appuserRepository;
    @Autowired
    private Reservationrepository reservationrepository;

    @GetMapping("/affichelist/{id}")
    public List<Participant> postParticipant(@PathVariable("id") Long n) {
        Reservation res = reservationrepository.findById(n).orElseThrow(null);
        List<Participant> participantList = new ArrayList<>() ;
        for (ReservationCategorie resc : res.getDetails()) {
            int ne = 0 ;
            while (  ne < resc.getQte()) {
                Participant par = new Participant() ;
                par.setAge(0);
                par.setNom("dd");par.setPhone("ss");par.setPhone("sz");
                par.setCategoriepar(resc.getCategoriebilletprix());
                participantList.add(par) ;
                ne++ ;
            }
        }
        return  participantList ;

    }

}