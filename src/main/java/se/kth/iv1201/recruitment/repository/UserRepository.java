package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.entity.User;

/**
 * Created by Robin on 2018-02-08.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
