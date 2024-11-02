CREATE table "user"
(
    id                      BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username                VARCHAR(255) NOT NULL,
    password                VARCHAR(255) NOT NULL,
    account_non_expired     BOOLEAN      NOT NULL,
    account_non_locked      BOOLEAN      NOT NULL,
    credentials_non_expired BOOLEAN      NOT NULL,
    enabled                 BOOLEAN      NOT NULL,
    first_name              VARCHAR(255) NOT NULL,
    last_name               VARCHAR(255) NOT NULL,
    email_address           VARCHAR(255) NOT NULL,
    birthdate               DATE         NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS users_idx_username on "user" (username);

CREATE table authority
(
    id        BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    authority VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS authorities_idx_authority on authority (authority);

CREATE table user_authority
(
    user_id      BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, authority_id)
);

ALTER TABLE user_authority
    ADD CONSTRAINT fk_user_authority_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE user_authority
    ADD CONSTRAINT fk_user_authority_authority FOREIGN KEY (authority_id) REFERENCES authority (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE table address
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address_line VARCHAR(255) NOT NULL
);

CREATE table owner
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    full_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    username   VARCHAR(255) NOT NULL,
    address_id BIGINT       NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS owner_idx_username on owner (username);
ALTER TABLE owner
    ADD CONSTRAINT fk_owner_address FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE table todo
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title        VARCHAR(255) NOT NULL,
    content      VARCHAR(255) NOT NULL,
    published_on TIMESTAMP    NOT NULL,
    updated_on   TIMESTAMP    NOT NULL,
    owner_id     BIGINT       NOT NULL
);

ALTER TABLE todo
    ADD CONSTRAINT fk_todo_owner FOREIGN KEY (owner_id) REFERENCES owner (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE table comment
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name         VARCHAR(255) NOT NULL,
    content      VARCHAR(255) NOT NULL,
    published_on TIMESTAMP    NOT NULL,
    updated_on   TIMESTAMP    NOT NULL,
    todo_id      BIGINT       NOT NULL
);

ALTER TABLE comment
    ADD CONSTRAINT fk_comment_todo FOREIGN KEY (todo_id) REFERENCES todo (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE table tutorial
(
    id    BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(255) NOT NULL
);
