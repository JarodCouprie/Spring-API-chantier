package edu.jcpe.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.jcpe.api.dao.ChantierDao;
import edu.jcpe.api.dao.UtilisateurDao;
import edu.jcpe.api.model.Chantier;
import edu.jcpe.api.model.Operation;
import edu.jcpe.api.model.Tache;
import edu.jcpe.api.model.Utilisateur;
import edu.jcpe.api.view.ChantierView;
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
public class ChantierController {

    @Autowired
    ChantierDao chantierDao;

    @Autowired
    UtilisateurDao utilisateurDao;

    @GetMapping("/chantier/list")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public List<Chantier> list() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<Utilisateur> user = utilisateurDao.findByPseudo(currentPrincipalName);

        List<Integer> chantierIds = user.get().getChantierLedList().stream().map(
                Chantier::getId
        ).collect(Collectors.toList());

        return chantierDao.findAllById(chantierIds);

    }

    @GetMapping("/chantier/time/{id}")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public ResponseEntity<Integer> getTotalTime(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<Utilisateur> user = utilisateurDao.findByPseudo(currentPrincipalName);

        List<Integer> chantierIds = user.get().getChantierLedList().stream().map(
                Chantier::getId
        ).collect(Collectors.toList());

        if (chantierIds.contains(id)) {
            Optional<Chantier> optionalChantier = chantierDao.findById(id);

            if (optionalChantier.isPresent()) {

                List<Operation> operationList = optionalChantier.get().getOperationPlannedList();

                List<Tache> taches = operationList.stream().map(Operation::getTaskToDo).collect(Collectors.toList());

                int sum = taches.stream().mapToInt(Tache::getTime).sum();

                return new ResponseEntity<>(sum, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/chantier/{id}")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public ResponseEntity<Chantier> get(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/chantier/name/{name}")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public ResponseEntity<Chantier> getByName(@PathVariable String name) {
        Optional<Chantier> optionalChantier = chantierDao.findByName(name);

        if (optionalChantier.isPresent()) {
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/chantier/{id}")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public ResponseEntity<Chantier> delete(@PathVariable int id) {

        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/chantier")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public ResponseEntity<Chantier> create(@RequestBody @Valid Chantier chantier) {
        chantier.setId(null);
        chantierDao.save(chantier);
        return new ResponseEntity<>(chantier, HttpStatus.CREATED);
    }

    @PutMapping("/chantier/{id}")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_WORKER", "ROLE_ADMIN"})
    public ResponseEntity<Chantier> update(@RequestBody @Valid Chantier chantier, @PathVariable int id) {
        chantier.setId(id);

        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.save(chantier);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
