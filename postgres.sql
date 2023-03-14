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
                                role_id INTEGER
)
-- create PK for  users_to_roles
-- add users, roles and links
 -- select select user.name with role = Admin



