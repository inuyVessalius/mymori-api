package mymori.mymoriapi.repository;

import java.util.List;

import mymori.mymoriapi.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByFirstName(String FirstName);
}