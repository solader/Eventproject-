package com.projetFe.Event.repository;

import com.projetFe.Event.Appuser;
import com.projetFe.Event.Event;
import com.projetFe.Event.Statuevent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Eventrepository extends JpaRepository <Event,Long > {
    public List<Event> findByUserId(Long userId);
    List<Event> findByStatuevent (Statuevent statuevent);


}
