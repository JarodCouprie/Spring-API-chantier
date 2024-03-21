package edu.jcpe.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Length(min = 3, max = 50, message = "Le nom doit être compris entre 3 et 50 caractères")
    protected String name;

    protected LocalDate date;

    @ManyToOne(optional = false)
    protected Chantier chantierPlanned;

    @ManyToOne(optional = false)
    protected Utilisateur utilisateurGiven;

    @ManyToOne(optional = false)
    protected Tache taskToDo;

}
