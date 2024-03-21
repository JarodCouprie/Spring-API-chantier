package edu.jcpe.api.security;

import edu.jcpe.api.dao.UtilisateurDao;
import edu.jcpe.api.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUser = utilisateurDao.findByPseudo(pseudo);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("pseudo introuvable");
        }

        return new AppUserDetails(optionalUser.get());
    }
}
