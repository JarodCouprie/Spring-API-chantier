package edu.jcpe.api.dao;

import edu.jcpe.api.model.Chantier;
import edu.jcpe.api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChantierDao extends JpaRepository<Chantier, Integer> {

    Optional<Chantier> findByName(String name);
}