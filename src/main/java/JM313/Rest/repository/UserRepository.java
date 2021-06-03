package JM313.Rest.repository;


import JM313.Rest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String s);
}
