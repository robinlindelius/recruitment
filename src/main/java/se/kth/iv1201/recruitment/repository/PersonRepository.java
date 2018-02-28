package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.entity.Person;

public interface PersonRepository extends CrudRepository<Person, String> {
}