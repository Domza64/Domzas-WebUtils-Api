package xyz.domza.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.domza.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
