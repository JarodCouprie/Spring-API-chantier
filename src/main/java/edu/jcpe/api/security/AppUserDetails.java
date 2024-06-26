package edu.jcpe.api.security;

import edu.jcpe.api.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class AppUserDetails implements UserDetails {

    private Utilisateur user;

    public AppUserDetails(Utilisateur user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (user.getRole().getDesignation().equals("administrateur")) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (user.getRole().getDesignation().equals("client")) {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }

        return List.of(new SimpleGrantedAuthority("ROLE_WORKER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPseudo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
