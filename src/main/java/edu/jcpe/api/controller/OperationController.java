package edu.jcpe.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.jcpe.api.dao.ChantierDao;
import edu.jcpe.api.dao.OperationDao;
import edu.jcpe.api.dao.UtilisateurDao;
import edu.jcpe.api.model.Chantier;
import edu.jcpe.api.model.Operation;
import edu.jcpe.api.model.Tache;
import edu.jcpe.api.model.Utilisateur;
import edu.jcpe.api.view.ChantierView;
import edu.jcpe.api.view.OperationView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OperationController {

    @Autowired
    OperationDao operationDao;

    @Autowired
    UtilisateurDao utilisateurDao;

    @GetMapping("/operation/list")
    @JsonView(OperationView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public List<Operation> list() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<Utilisateur> user = utilisateurDao.findByPseudo(currentPrincipalName);

        List<Integer> operationIds = user.get().getOperationGivenList().stream().map(
                Operation::getId
        ).collect(Collectors.toList());

        return operationDao.findAllById(operationIds);

    }

}
