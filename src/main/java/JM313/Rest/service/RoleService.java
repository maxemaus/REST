package JM313.Rest.service;

import JM313.Rest.entities.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getRolesList();
    Set<Role> mapRoleNamesToRoles(List<String> roles);
}
