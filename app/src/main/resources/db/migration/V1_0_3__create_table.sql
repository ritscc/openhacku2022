create table `table`
(
    id         int auto_increment
        primary key,
    shop_id    int                                not null,
    capacity   int unsigned                       not null,
    category   int                                not null,
    status     int                                not null,
    created_at datetime default CURRENT_TIMESTAMP not null,
    updated_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint table_shop_id_fk
        foreign key (shop_id) references shop (id)
);

