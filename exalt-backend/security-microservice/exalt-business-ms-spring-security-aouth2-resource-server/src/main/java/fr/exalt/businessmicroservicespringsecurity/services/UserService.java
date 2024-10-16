package fr.exalt.businessmicroservicespringsecurity.services;

import fr.exalt.businessmicroservicespringsecurity.entities.dtos.RoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserRoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserUpdateDto;
import fr.exalt.businessmicroservicespringsecurity.entities.models.RoleModel;
import fr.exalt.businessmicroservicespringsecurity.entities.models.UserModel;

import java.util.Collection;

public interface UserService {
    UserModel createUser(UserDto userDto);
    UserModel userAddRole(UserRoleDto dto);
    Collection<UserModel> getAllUsers();
    UserModel getUser(long userId) ;
    RoleModel createRole(RoleDto dto);
    Collection<RoleModel> geAllRoles();
    RoleModel getRole(long roleId);

    UserModel removeUserRole(UserRoleDto dto);

    void deleteUser(Integer userId);

    UserModel editUserInformation(long userId, UserUpdateDto dto);
}
