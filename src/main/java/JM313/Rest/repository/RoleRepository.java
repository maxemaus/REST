package JM313.Rest.repository;


import JM313.Rest.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
