package edu.jcpe.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Chantier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Length(min = 3, max = 50, message = "Le nom doit être compris entre 3 et 50 caractères")
    protected String name;

    protected String address;

    @OneToMany(mappedBy = "chantierPlanned")
    protected List<Operation> operationPlannedList = new ArrayList<>();

    @ManyToOne(optional = false)
    protected Utilisateur owner;

    @ManyToOne(optional = false)
    protected Utilisateur leader;


}
