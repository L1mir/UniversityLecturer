package org.limir.universitylecturer.mappers;

import org.limir.universitylecturer.dto.UserRequest;
import org.limir.universitylecturer.dto.UserResponse;
import org.limir.universitylecturer.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    User userDTOToUser(UserRequest userRequest);
    UserResponse userToUserResponse(User user);
}
