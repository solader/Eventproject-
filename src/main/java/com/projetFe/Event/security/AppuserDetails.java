package com.projetFe.Event.security;

import com.projetFe.Event.Appuser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public class AppuserDetails implements UserDetails {
    private final Appuser appuser;

    public AppuserDetails (Appuser appuser) {
        this.appuser = appuser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // tu ajouteras les rôles plus tard si besoin
    }

    @Override
    public String getPassword() {
        return appuser.getPassword();
    }

    @Override
    public String getUsername() {
        return appuser.getEmail(); // getUsername()
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // à adapter si tu veux gérer ça plus tard
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
