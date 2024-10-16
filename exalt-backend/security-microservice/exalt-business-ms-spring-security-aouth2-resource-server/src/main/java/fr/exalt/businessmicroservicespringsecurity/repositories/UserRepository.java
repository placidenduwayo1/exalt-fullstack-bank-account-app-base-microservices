package fr.exalt.businessmicroservicespringsecurity.repositories;

import fr.exalt.businessmicroservicespringsecurity.entities.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}
