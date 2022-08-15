create table shop
(
    id          int auto_increment
        primary key,
    code        varchar(16)                        not null,
    name        varchar(64)                        not null,
    description text                               null,
    tel         varchar(255)                       not null,
    updated_at  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    created_at  datetime default CURRENT_TIMESTAMP not null
);

