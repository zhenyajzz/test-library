package ex.smart.library.repo;

import ex.smart.library.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {

    public User findByUsername(String user);

    public List<User> findAllByUsernameOrderByUsername(String user);
}
