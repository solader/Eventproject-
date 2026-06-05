package com.projetFe.Event.repository;

import com.projetFe.Event.Event;
import com.projetFe.Event.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Imagerepository extends JpaRepository <Image,Long> {
}
