package fr.exalt.businessmicroservicespringsecurity.services;

import fr.exalt.businessmicroservicespringsecurity.entities.dtos.RoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserDto;
import fr.exalt.businessmicroservicespringsecurity.entities.models.UserModel;
import fr.exalt.businessmicroservicespringsecurity.entities.models.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperService {
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserModel from(UserDto dto);
    @Mapping(target = "roleId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    RoleModel from (RoleDto dto);
}
