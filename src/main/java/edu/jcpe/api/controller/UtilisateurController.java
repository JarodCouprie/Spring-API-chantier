package edu.jcpe.api.controller;

import edu.jcpe.api.dao.UtilisateurDao;
import edu.jcpe.api.model.Utilisateur;
import edu.jcpe.api.security.AppUserDetailService;
import edu.jcpe.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public void signIn(@RequestBody Utilisateur user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        utilisateurDao.save(user);

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
