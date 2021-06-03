package JM313.Rest.controller;

import JM313.Rest.dto.UserDto;
import JM313.Rest.entities.User;
import JM313.Rest.mapper.UserMapper;
import JM313.Rest.service.RoleService;
import JM313.Rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminsController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok((User) userService.loadUserByUsername(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDto userDto) {
        User user = UserMapper.toModel(userDto);
        user.setRoles(roleService.mapRoleNamesToRoles(userDto.getRoles()));
        return ResponseEntity.ok(userService.addOrUpdate(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDto userDto) {
        User user = UserMapper.toModel(userDto);
        user.setRoles(roleService.mapRoleNamesToRoles(userDto.getRoles()));
        return ResponseEntity.ok(userService.addOrUpdate(user));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> userPage(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
