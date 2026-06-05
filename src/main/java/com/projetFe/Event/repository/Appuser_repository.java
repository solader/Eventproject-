package com.projetFe.Event.repository;

import com.projetFe.Event.Appuser;
import com.projetFe.Event.Event;
import com.projetFe.Event.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

@Repository
public interface Appuser_repository extends JpaRepository <Appuser,Long >{
    public Optional<Appuser> findByEmail (String em) ;
}
