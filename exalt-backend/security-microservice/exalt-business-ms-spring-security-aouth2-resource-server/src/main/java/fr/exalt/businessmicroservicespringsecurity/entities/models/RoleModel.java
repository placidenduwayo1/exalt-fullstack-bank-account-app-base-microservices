package fr.exalt.businessmicroservicespringsecurity.entities.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles_table", schema = "security_service_schema")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "table_role_generator")
    @SequenceGenerator(
            name = "table_role_generator",
            sequenceName = "table_role_id_seq"
    )
    @Column(name = "id")
    private Long roleId;
    @Column(unique = true, name = "role_name")
    private String roleName;
    @Column(name = "created_date")
    private String createdAt;
}
