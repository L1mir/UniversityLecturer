package org.limir.universitylecturer.mappers;

import org.limir.universitylecturer.dto.RoleDTO;
import org.limir.universitylecturer.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);
}
