package com.projetFe.Event.dto;

import com.projetFe.Event.Appuser;

public class usermapper {
    public static UserReserveurdto retourneuserR (Appuser user) {
        UserReserveurdto usere  = new UserReserveurdto() ;
        usere.setNom(user.getNom());
        usere.setPrenom(user.getPrenom());
        usere.setAge(user.getAge());
        usere.setEmail(user.getEmail());
        usere.setPassword(user.getPassword());
        return usere ;
    }
    public static Appuser retourneApp (UserReserveurdto user){
       Appuser usere  = new Appuser() ;
        usere.setNom(user.getNom());
        usere.setPrenom(user.getPrenom());
        usere.setAge(user.getAge());
        usere.setEmail(user.getEmail());
        usere.setPassword(user.getPassword());
        return usere ;

    }
    public static UserOrgdto retourneO (Appuser user){
        UserOrgdto usere  = new UserOrgdto() ;
        usere.setNom(user.getNom());
        usere.setPrenom(user.getPrenom());
        usere.setAge(user.getAge());
        usere.setEmail(user.getEmail());
        usere.setPassword(user.getPassword());
        usere.setCne(user.getCne());
        //usere.setImagecarte(user.getImagecarte());
        //usere.setPhoto(user.getPhoto());
        return usere ;
    }

    public static Useradmindto retourne (Appuser user){
        Useradmindto usere = new Useradmindto() ;
        usere.setNom(user.getNom());
        usere.setPrenom(user.getPrenom());
        usere.setAge(user.getAge());
        usere.setEmail(user.getEmail());
        usere.setCode_acces(user.getCode_acces());
        return usere ;
    }



}
