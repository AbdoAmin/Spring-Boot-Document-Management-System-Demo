insert into users (username, password, enabled) values ('admin', '{noop}admin', true);
insert into users (username, password, enabled) values ('user', '{noop}user', true);
INSERT INTO users  (username, password, enabled)
values ('abdo',
        '{bcrypt}$2a$10$jhyTO4zyD1fmaLVZZn9irOgEJnHAExaNYcFTy2cIv2chUEQUNHaei', -- abdo
        1);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('abdo', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('abdo', 'ROLE_ADMIN');