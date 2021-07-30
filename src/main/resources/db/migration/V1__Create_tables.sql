-- auto-generated definition
create table user
(
    id                 bigint auto_increment primary key,
    username           varchar(100),
    avatar             varchar(100),
    encrypted_password varchar(100),
    created_at         datetime,
    updated_at         datetime,
    constraint user_username_uindex
        unique (username)
);