package com.projetFe.Event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationRequestdto {
    private Long user_R;
    private Long evvent;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateReservation;
    private List<Categorie_qteDto> categories;
}
