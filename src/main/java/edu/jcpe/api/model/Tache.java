package edu.jcpe.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.jcpe.api.view.OperationView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(OperationView.class)
    protected Integer id;

    @Length(min = 3, max = 50, message = "Le nom doit être compris entre 3 et 50 caractères")
    @JsonView(OperationView.class)
    protected String name;

    @JsonView(OperationView.class)
    @Min(value = 1, message = "Le temps doit être au minimum d'une minute")
    protected int time;

    @OneToMany(mappedBy = "taskToDo")
    protected List<Operation> operationSubjectList = new ArrayList<>();
}
