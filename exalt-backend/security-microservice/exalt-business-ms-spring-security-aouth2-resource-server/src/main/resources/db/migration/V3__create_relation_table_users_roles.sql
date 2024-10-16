create table if not exists security_service_schema.users_roles_association_table
(
    user_id
    BIGINT
    NOT
    NULL,
    role_id
    BIGINT
    NOT
    NULL
);