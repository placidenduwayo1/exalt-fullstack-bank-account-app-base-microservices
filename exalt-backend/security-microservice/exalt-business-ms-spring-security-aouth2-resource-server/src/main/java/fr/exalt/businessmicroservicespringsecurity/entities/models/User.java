package fr.exalt.businessmicroservicespringsecurity.entities.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_table", schema = "security_service_schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "table_user_generator")
    @SequenceGenerator(
            name = "table_user_generator",
            sequenceName = "table_user_id_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long userId;
    @Column(unique = true)
    private String username;
    private String pwd;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    @Column(name = "created_date")
    private String createdAt;
    @ManyToMany @JoinTable(
            name = "users_roles_association_table", schema = "security_service_schema",
            joinColumns = {@JoinColumn(name = "user_Id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();
}
