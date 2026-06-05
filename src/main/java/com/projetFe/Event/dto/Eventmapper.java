package com.projetFe.Event.dto;

import com.projetFe.Event.Event;

public class Eventmapper {
  public static EventDto retourneDto(Event ev) {
    EventDto event = new EventDto();
    //event.setId(ev.getId());
    event.setTitre_event(ev.getTitre_event());
    event.setTypeevent(ev.getTypeevent());
    event.setDate_event(ev.getDate_event());
    event.setHeure_event(ev.getHeure_event());
    event.setVille_event(ev.getVille_event());
    event.setAdresse_salle(ev.getAdresse_salle());
    event.setListCategories(ev.getListCategories());
    return event;

  }
  public static Eventstatu retourneallstatu (Event ev) {
    Eventstatu event = new Eventstatu();
    //event.setId(ev.getId());
    event.setTitre_event(ev.getTitre_event());
    event.setDate_event(ev.getDate_event());
    event.setHeure_event(ev.getHeure_event());
    event.setVille_event(ev.getVille_event());
    event.setAdresse_salle(ev.getAdresse_salle());
    event.setTypeevent(ev.getTypeevent());
    event.setStatuevent(ev.getStatuevent());
    return event;

  }
  public static EventdtoAcc retourneallDto(Event ev) {
    EventdtoAcc event = new EventdtoAcc();
    //event.setId(ev.getId());
    event.setTitre_event(ev.getTitre_event());
    event.setDate_event(ev.getDate_event());
    event.setHeure_event(ev.getHeure_event());
    event.setVille_event(ev.getVille_event());
    event.setAdresse_salle(ev.getAdresse_salle());
    event.setTypeevent(ev.getTypeevent());
    return event;
  }


  public static Eventdtouser retourneEvuser(Event ev) {
    Eventdtouser event = new Eventdtouser();

    event.setTitre_event(ev.getTitre_event());
    event.setTypeevent(ev.getTypeevent());
    event.setDate_event(ev.getDate_event());
    event.setHeure_event(ev.getHeure_event());
    event.setVille_event(ev.getVille_event());
    event.setAdresse_salle(ev.getAdresse_salle());
    event.setDescription(ev.getDescription());
    event.setListCategories(ev.getListCategories());
    event.setUser(ev.getUser());

    return event;

  }
}