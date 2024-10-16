create table if not exists security_service_schema.roles_table
(
    id
    BIGINT
    PRIMARY
    KEY,
    role_name
    VARCHAR
(
    255
) NOT NULL,
    created_date VARCHAR
(
    255
)
    );
create table if not exists security_service_schema.users_table
(
    id
    BIGINT
    PRIMARY
    KEY,
    username
    VARCHAR
(
    255
) NOT NULL,
    pwd VARCHAR
(
    255
) NOT NULL,
    firstname VARCHAR
(
    255
) NOT NULL,
    lastname VARCHAR
(
    255
) NOT NULL,
    email VARCHAR
(
    255
) NOT NULL,
    created_date VARCHAR
(
    255
) NOT NULL
    );