package edu.jcpe.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.jcpe.api.view.ChantierView;
import edu.jcpe.api.view.OperationView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.execution.Util;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ChantierView.class, OperationView.class})
    protected Integer id;

    @Column(unique = true, length = 50)
    @JsonView({ChantierView.class, OperationView.class})
    @Length(min = 3, max = 50, message = "Le pseudo doit être compris entre 3 et 50 charactères")
    @NotNull(message = "Le pseudo est obligatoire")
    protected String pseudo;

    @NotNull(message = "Le mot de passe est obligatoire")
    protected String password;

    @ManyToOne(optional = false)
    @NotNull(message = "Le role de l'utilisateur doit être renseigné")
    protected Role role;

    @OneToMany(mappedBy = "owner")
    protected List<Chantier> chantierOwnedList = new ArrayList<>();

    @OneToMany(mappedBy = "leader")
    protected List<Chantier> chantierLedList = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateurGiven")
    protected List<Operation> operationGivenList = new ArrayList<>();

}
