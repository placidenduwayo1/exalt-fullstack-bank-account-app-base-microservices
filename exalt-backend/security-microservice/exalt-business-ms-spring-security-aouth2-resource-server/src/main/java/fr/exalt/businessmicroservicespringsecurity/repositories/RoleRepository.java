package fr.exalt.businessmicroservicespringsecurity.repositories;

import fr.exalt.businessmicroservicespringsecurity.entities.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    RoleModel findByRoleName(String roleName);
}
