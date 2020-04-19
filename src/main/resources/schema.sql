create table users
(
    username varchar_ignorecase(50)  not null primary key,
    password varchar_ignorecase(200) not null,
    enabled  boolean                 not null
);

create table authorities
(
    id        int primary key auto_increment,
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null
);
DROP TABLE IF EXISTS file;
create table file
(
    file_name      varchar_ignorecase(100) not null primary key,
    insertion_date timestamp               not null default current_timestamp(),
    file_type      varchar_ignorecase(50)  not null,
    user_uploader  varchar_ignorecase(50)  not null,
    constraint fk_files_users foreign key (user_uploader) references users (username)
);
DROP TABLE IF EXISTS file_authority;
create table file_authority
(
    id        int primary key auto_increment,
    file_name varchar_ignorecase(100),
    file_authority varchar_ignorecase(50),
    constraint fk_files_authority foreign key (file_name) references file (file_name));
