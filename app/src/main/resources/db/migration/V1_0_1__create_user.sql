create table user
(
    id         int auto_increment
        primary key,
    first_name varchar(255)                         not null,
    last_name  varchar(255)                         not null,
    tel        varchar(255)                         not null,
    email      varchar(255)                         not null,
    password   varchar(255)                         not null,
    icon_url   varchar(2048)                        null,
    created_at datetime   default CURRENT_TIMESTAMP not null,
    updated_at datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_admin   tinyint(1) default 0                 not null,
    constraint user_email_uindex
        unique (email),
    constraint user_tel_uindex
        unique (tel)
);

