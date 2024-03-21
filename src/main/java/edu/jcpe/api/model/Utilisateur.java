package edu.jcpe.api.model;

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
    protected Integer id;

    @Column(unique = true, length = 50)
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
