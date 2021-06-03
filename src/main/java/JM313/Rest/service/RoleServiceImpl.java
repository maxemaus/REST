package JM313.Rest.service;

import JM313.Rest.entities.Role;
import JM313.Rest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRolesList() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> mapRoleNamesToRoles(List<String> roles) {
        return roles.stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());
    }
}
