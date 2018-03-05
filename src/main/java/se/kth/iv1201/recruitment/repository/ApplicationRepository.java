package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.entity.Application;

/**
 * Repository with CRUD-operations for Application interacting
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {
}