insert into authority (authority)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPER_ADMIN');

insert into "user" (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, first_name, last_name,
                    email_address, birthdate)
values ('john', '$2b$12$hr2oZlCgSzak1g6fx3OqJOcuVW4dcHYNO0Z6frMexrQGmaFEi9/06', true, true, true, true, 'John', 'Doe', 'john@doe.com', '1980-01-01'),
       ('emile', '$2b$12$hr2oZlCgSzak1g6fx3OqJOcuVW4dcHYNO0Z6frMexrQGmaFEi9/06', true, true, true, true, 'Emile', 'Zola', 'emile@zola.com',
        '1980-01-01'),
       ('zinedine', '$2b$12$hr2oZlCgSzak1g6fx3OqJOcuVW4dcHYNO0Z6frMexrQGmaFEi9/06', true, true, true, true, 'Zinedine', 'Zidane',
        'zinedine@zidane.com',
        '1980-01-01');

insert into user_authority (user_id, authority_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 1);

insert into address (address_line)
values ('1234 Main St, Anytown, USA'),
       ('5678 Elm St, Anytown, USA'),
       ('91011 Oak St, Anytown, USA');

insert into owner (full_name, email, username, address_id)
values ('John Doe', 'john@doe.com', 'john', 1),
       ('Emile Zola', 'emile@zola.com', 'emile', 2),
       ('Zinedine Zidane', 'zinedine@zidane.com', 'zinedine', 3);

insert into todo (title, content, published_on, updated_on, owner_id)
values ('Buy milk', 'Buy milk', '2024-01-01', '2024-01-01', 1),
       ('Clean room', 'Clean room', '2024-02-02', '2024-02-02', 2),
       ('Learn Spring', 'Learn Spring', '2024-03-03', '2024-03-03', 3);

insert into comment (name, content, published_on, updated_on, todo_id)
values ('John Doe', 'Should buy milk', '2024-01-01', '2024-01-01', 1),
       ('Emile Zola', 'Should really buy milk', '2024-01-01', '2024-01-01', 1),
       ('Emile Zola', 'Should clean room', '2024-02-02', '2024-02-02', 2),
       ('Zinedine Zidane', 'Should learn Spring', '2024-03-03', '2024-03-03', 3);

insert into tutorial (title)
values ('Learn Spring Boot'),
       ('Learn Spring Data JPA'),
       ('Learn Spring Data JDBC');
