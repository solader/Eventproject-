package com.projetFe.Event.dto;

import com.projetFe.Event.security.AutentificationResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventSta {
    List <EventStatistiquedto> eventSta = new ArrayList<>() ;
    int qtetotal ;
    double totalev ;
    double totalF ;
}
