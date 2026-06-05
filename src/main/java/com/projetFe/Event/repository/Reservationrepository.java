package com.projetFe.Event.repository;

import com.projetFe.Event.Event;
import com.projetFe.Event.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Reservationrepository extends JpaRepository <Reservation,Long > {
     List<Reservation> findByEvventId (Long id) ;
}
