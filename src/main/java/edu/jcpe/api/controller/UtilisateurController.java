package edu.jcpe.api.controller;

import edu.jcpe.api.ErrorHandler;
import edu.jcpe.api.dao.UtilisateurDao;
import edu.jcpe.api.model.Utilisateur;
import edu.jcpe.api.security.AppUserDetailService;
import edu.jcpe.api.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AppUserDetailService appUserDetailService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/sign-in")
    public ResponseEntity<Utilisateur> signIn(@RequestBody @Valid Utilisateur user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        utilisateurDao.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public String logIn(@RequestBody Utilisateur user) {
        try {
            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getPseudo(), user.getPassword()
            )).getPrincipal();
            return jwtUtil.generateJwt(userDetails);

        } catch (Exception exception) {
            return null;
        }
    }

}
