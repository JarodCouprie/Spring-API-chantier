package edu.jcpe.api.dao;

import edu.jcpe.api.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDao extends JpaRepository<Operation, Integer> {
}
