package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.entity.User;

/**
 * Created by Robin on 2018-02-08.
 * Repository with CRUD-Operations for user interaction.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
