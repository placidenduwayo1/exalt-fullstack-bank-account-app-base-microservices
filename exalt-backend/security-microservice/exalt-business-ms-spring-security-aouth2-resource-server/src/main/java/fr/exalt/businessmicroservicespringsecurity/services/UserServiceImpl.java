package fr.exalt.businessmicroservicespringsecurity.services;

import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserRoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserUpdateDto;
import fr.exalt.businessmicroservicespringsecurity.entities.models.UserModel;
import fr.exalt.businessmicroservicespringsecurity.exceptions.*;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.RoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserDto;
import fr.exalt.businessmicroservicespringsecurity.entities.models.RoleModel;
import fr.exalt.businessmicroservicespringsecurity.repositories.RoleRepository;
import fr.exalt.businessmicroservicespringsecurity.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperService mapperService;

    @Override
    public UserModel createUser(UserDto userDto) {
        if (UserRoleValidator.isInvalid(userDto)) {
            throw new UserInformationInvalidException(ExceptionE.USER_INFO);
        }
        String username = UserRoleValidator.buildUsername(userDto.getFirstname(), userDto.getLastname());
        Optional<UserModel> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            throw new UserAlreadyExistsException(ExceptionE.USER_EXISTS);
        }

        if(!UserRoleValidator.passwordsMatch(userDto.getPwd(), userDto.getPwd1())){
            throw new PasswordsNotMatchException(ExceptionE.PASSWORD_MATCH);
        }
        UserModel mappedUserModel = mapperService.from(userDto);
        mappedUserModel.setCreatedAt(Instant.now().toString());
        mappedUserModel.setUsername(username);
        mappedUserModel.setPwd(passwordEncoder.encode(userDto.getPwd()));
        return userRepository.save(mappedUserModel);
    }

    @Override
    public UserModel userAddRole(UserRoleDto dto){
        UserModel userModel = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new UserNotFoundException(ExceptionE.USER_NOT_FOUND));
        RoleModel roleModel = roleRepository.findByRoleName(dto.getRoleName());
        if (roleModel == null)
            throw new RoleNotFoundException(ExceptionE.ROLE_NOT_FOUND);
        Set<RoleModel> roleModels = userModel.getRoles();
        if(roleModels.contains(roleModel))
            throw new UserPossessThisRoleException(ExceptionE.USER_POSSESS_ROLE);
        roleModels.add(roleModel);
        userModel.setRoles(roleModels);
        return userRepository.save(userModel);
    }

    @Override
    public Collection<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel getUser(long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ExceptionE.USER_NOT_FOUND));
    }

    @Override
    public RoleModel createRole(RoleDto dto){
        if (UserRoleValidator.isInvalid(dto)) {
            throw new RoleInformationInvalidException(ExceptionE.ROLE_INFO);
        }
        if (!UserRoleValidator.isValid(dto.getRoleName()))
            throw new RoleInformationInvalidException(ExceptionE.ROLE_INFO);
        RoleModel roleModel = roleRepository.findByRoleName(dto.getRoleName());
        if(UserRoleValidator.exists(roleModel))
            throw new RoleAlreadyExistsException(ExceptionE.ROLE_EXISTS);
        RoleModel mappedRoleModel = mapperService.from(dto);
        mappedRoleModel.setCreatedAt(Instant.now().toString());
        return roleRepository.save(mappedRoleModel);
    }

    @Override
    public Collection<RoleModel> geAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleModel getRole(long roleId){
        return roleRepository.findById(roleId).orElseThrow(
                () -> new RoleNotFoundException(ExceptionE.ROLE_NOT_FOUND));
    }

    @Override
    public UserModel removeUserRole(UserRoleDto dto) {
        UserModel userModel = getUser(dto.getUserId());
        RoleModel roleModel = roleRepository.findByRoleName(dto.getRoleName());
        if(!UserRoleValidator.exists(roleModel))
            throw new RoleNotFoundException(ExceptionE.ROLE_NOT_FOUND);
        if(!userModel.getRoles().contains(roleModel))
            throw new RoleNoAssignedTheUserException(ExceptionE.USER_ROLE);
        userModel.getRoles().remove(roleModel);
        return userRepository.save(userModel);
    }

    @Override
    public void deleteUser(Integer userId) {
        UserModel userModel = getUser(userId);
        userRepository.delete(userModel);
    }

    @Override
    public UserModel editUserInformation(long userId, UserUpdateDto dto){
        UserModel userModel = getUser(userId);
        String username = UserRoleValidator.buildUsername(dto.getFirstname(), dto.getLastname());
        userModel.setUsername(username);
        userModel.setFirstname(dto.getFirstname());
        userModel.setLastname(dto.getLastname());
        userModel.setEmail(dto.getEmail());
        //unchangeable user values
        userModel.setUserId(userId);
        userModel.setPwd(userModel.getPwd());
        userModel.setCreatedAt(userModel.getCreatedAt());
        userModel.setRoles(userModel.getRoles());
        return userRepository.save(userModel);
    }
}
