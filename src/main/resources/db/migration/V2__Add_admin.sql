insert into users(id, username, password, active)
VALUES (1, 'admin', '123', true);

insert into user_role(user_id, roles)
VALUES (1, 'USER'), (1, 'ADMIN');