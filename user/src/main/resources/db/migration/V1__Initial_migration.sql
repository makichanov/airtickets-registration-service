CREATE SEQUENCE user_id_seq;

CREATE TABLE users
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq'),
    username      VARCHAR(50) NOT NULL,
    password_hash VARCHAR(60) NOT NULL
);

CREATE SEQUENCE role_id_seq;

CREATE TABLE roles
(
    id INTEGER PRIMARY KEY DEFAULT nextval('role_id_seq'),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),

    UNIQUE(name)
);

CREATE TABLE user_roles
(
    user_id BIGINT,
    role_id INTEGER,

    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);