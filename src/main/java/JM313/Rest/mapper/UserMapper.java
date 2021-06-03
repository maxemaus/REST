package JM313.Rest.mapper;

import JM313.Rest.dto.UserDto;
import JM313.Rest.entities.User;

public class UserMapper {

    private UserMapper() {

    }

    public static User toModel(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassWord(dto.getPassWord());
        return user;
    }
}
