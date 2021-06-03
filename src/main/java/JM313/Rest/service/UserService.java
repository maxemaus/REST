package JM313.Rest.service;


import JM313.Rest.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User addOrUpdate(User user);
    void remove(Long id);
    List<User> getUsers();
    User findById(Long id);
}
