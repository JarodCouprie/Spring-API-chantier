package edu.jcpe.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.jcpe.api.view.ChantierView;
import edu.jcpe.api.view.OperationView;
import jakarta.persistence.*;
import jdk.jshell.execution.Util;
import lombok.Getter;
import lombok.Setter;

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
    protected String pseudo;
    protected String password;

    @ManyToOne(optional = false)
    protected Role role;

    @OneToMany(mappedBy = "owner")
    protected List<Chantier> chantierOwnedList = new ArrayList<>();

    @OneToMany(mappedBy = "leader")
    protected List<Chantier> chantierLedList = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateurGiven")
    protected List<Operation> operationGivenList = new ArrayList<>();

}
