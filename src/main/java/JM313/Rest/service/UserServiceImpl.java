package JM313.Rest.service;


import JM313.Rest.entities.User;
import JM313.Rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(() -> new Exception("user with id= "+id+"is not found"));
        } catch (Exception e) {
            e.getMessage();
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(s);

        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
