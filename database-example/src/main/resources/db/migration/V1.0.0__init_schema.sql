CREATE TABLE users (
                       id INTEGER PRIMARY KEY,
                       name VARCHAR(120)
);

CREATE TABLE roles (
                       id INTEGER PRIMARY KEY,
                       name VARCHAR(120)
);

CREATE TABLE users_to_roles (
                                user_id INTEGER,
                                role_id INTEGER,
                                PRIMARY KEY (user_id, role_id),
                                CONSTRAINT fk_users FOREIGN KEY(user_id) REFERENCES users(id),
                                CONSTRAINT fk_roles FOREIGN KEY(role_id) REFERENCES roles(id)
);

INSERT INTO  users(id, name)
VALUES(1, 'Vasya');

INSERT INTO  users(id, name)
VALUES(2, 'Petya');

INSERT INTO  roles(id, name)
VALUES(1, 'ADMIN');

INSERT INTO  roles(id, name)
VALUES(2, 'USER');

INSERT INTO  users_to_roles(user_id, role_id)
VALUES(1, 1);

INSERT INTO  users_to_roles(user_id, role_id)
VALUES(1, 2);

INSERT INTO  users_to_roles(user_id, role_id)
VALUES(2, 2);
