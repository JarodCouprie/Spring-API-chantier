package edu.jcpe.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.jcpe.api.view.ChantierView;
import edu.jcpe.api.view.OperationView;
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
    @JsonView({ChantierView.class, OperationView.class})
    protected Integer id;

    @Length(min = 3, max = 50, message = "Le nom doit être compris entre 3 et 50 caractères")
    @JsonView({ChantierView.class, OperationView.class})
    protected String name;

    @JsonView({ChantierView.class, OperationView.class})
    protected String address;

    @OneToMany(mappedBy = "chantierPlanned")
    @JsonView(ChantierView.class)
    protected List<Operation> operationPlannedList = new ArrayList<>();

    @ManyToOne(optional = false)
    @JsonView({ChantierView.class, OperationView.class})
    protected Utilisateur owner;

    @ManyToOne(optional = false)
    @JsonView({ChantierView.class, OperationView.class})
    protected Utilisateur leader;

}
