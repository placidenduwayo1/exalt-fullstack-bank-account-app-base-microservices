package fr.exalt.businessmicroservicespringsecurity.web;

import fr.exalt.businessmicroservicespringsecurity.entities.dtos.RoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserRoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserUpdateDto;
import fr.exalt.businessmicroservicespringsecurity.entities.models.RoleModel;
import fr.exalt.businessmicroservicespringsecurity.entities.models.UserModel;
import fr.exalt.businessmicroservicespringsecurity.exceptions.*;
import fr.exalt.businessmicroservicespringsecurity.services.UserService;
import fr.exalt.businessmicroservicespringsecurity.servicesecurity.securitywebtoken.JwtDto;
import fr.exalt.businessmicroservicespringsecurity.servicesecurity.securitywebtoken.JwtGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(value = "api-security")
@AllArgsConstructor
public class SecurityApisController {
    private final UserService userService;
    private final JwtGeneratorService jwtGeneratorService;
    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserDto dto) throws UserAlreadyExistsException,
            UserInformationInvalidException, PasswordsNotMatchException {
        UserModel userModel = userService.createUser(dto);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
    @PostMapping("/roles")
    public ResponseEntity<RoleModel> createRole(@RequestBody RoleDto dto) throws RoleInformationInvalidException, RoleAlreadyExistsException {
        return new ResponseEntity<>(userService.createRole(dto), HttpStatus.OK);
    }
    @PostMapping("/users/add-role")
    public ResponseEntity<UserModel> userAddRole(@RequestBody UserRoleDto dto) throws UserNotFoundException, RoleNotFoundException,
            UserPossessThisRoleException {
        return new ResponseEntity<>(userService.userAddRole(dto), HttpStatus.OK);
    }
    @GetMapping( "/users")
    public ResponseEntity<Collection<UserModel>> getAllUsers(){
        Collection<UserModel> userModels = userService.getAllUsers();
        return new ResponseEntity<>(userModels, HttpStatus.OK);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserModel> getUser(@PathVariable(name = "userId") long userId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<Collection<RoleModel>> getAllRoles(){
        return new ResponseEntity<>(userService.geAllRoles(), HttpStatus.OK);
    }
    @GetMapping("/roles/{roleId}")
    public ResponseEntity<RoleModel> getRole(@PathVariable(name = "roleId") long roleId) throws RoleNotFoundException {
        return new ResponseEntity<>(userService.getRole(roleId), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody JwtDto jwtDto) throws UserAuthenticationFailedException,
            RefreshTokenMissException {
        return jwtGeneratorService.generateJwt(jwtDto);
    }
    @PostMapping("/users/remove-role")
    public ResponseEntity<UserModel> userRemoveRole(@RequestBody UserRoleDto dto) throws UserNotFoundException, RoleNotFoundException, RoleNoAssignedTheUserException {
        return new ResponseEntity<>(userService.removeUserRole(dto),HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable(name = "userId") Integer userId) throws UserNotFoundException {
        userService.deleteUser(userId);
    }
    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<UserModel> editUserInformation(@PathVariable(name = "userId") long userId, @RequestBody UserUpdateDto dto) throws UserNotFoundException {
        return new ResponseEntity<>(userService.editUserInformation(userId, dto), HttpStatus.OK);
    }
}
