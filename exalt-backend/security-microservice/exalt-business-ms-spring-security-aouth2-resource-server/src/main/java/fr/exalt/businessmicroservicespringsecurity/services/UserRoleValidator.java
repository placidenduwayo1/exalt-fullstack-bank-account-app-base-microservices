package fr.exalt.businessmicroservicespringsecurity.services;

import fr.exalt.businessmicroservicespringsecurity.entities.dtos.RoleDto;
import fr.exalt.businessmicroservicespringsecurity.entities.dtos.UserDto;
import fr.exalt.businessmicroservicespringsecurity.entities.models.RoleModel;
import fr.exalt.businessmicroservicespringsecurity.entities.models.RoleEnum;
import fr.exalt.businessmicroservicespringsecurity.entities.models.UserModel;

public class UserRoleValidator {
    private UserRoleValidator(){}
    public static boolean isInvalid(UserDto dto){
        return dto.getFirstname().isBlank()
                || dto.getLastname().isBlank()
                || dto.getEmail().isBlank();
    }
    public static boolean isValid(String roleName){
        boolean roleValid = false;
        for(RoleEnum iterator: RoleEnum.values()){
            if(iterator.getRole().equals(roleName)){
                roleValid=true;
                break;
            }
        }
        return roleValid;
    }
    public static boolean isInvalid(RoleDto dto){
        return dto.getRoleName().isBlank();
    }

    public static String buildUsername(String firstname, String lastname){
       return firstname.strip().toLowerCase().replaceAll("\\s","-")+
                "."+lastname.strip().toLowerCase().replaceAll("\\s","-");
    }

    public static boolean exists(UserModel userModel){
        return userModel !=null;
    }
    public static boolean exists(RoleModel roleModel){
        return roleModel !=null;
    }

    public static boolean passwordsMatch(String pwd1, String pwd2){
        return pwd1.equals(pwd2);
    }
}
