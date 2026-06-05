package com.projetFe.Event.repository;

import com.projetFe.Event.Event;
import com.projetFe.Event.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long > {
}
