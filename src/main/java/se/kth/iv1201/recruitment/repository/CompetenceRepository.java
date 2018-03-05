package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.entity.Competence;

/**
 * Repository with CRUD-operations for Competence interacting
 */
public interface CompetenceRepository extends CrudRepository<Competence, Integer> {
}
