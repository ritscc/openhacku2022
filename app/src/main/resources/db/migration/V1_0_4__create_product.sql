create table product
(
    id          int auto_increment
        primary key,
    shop_id     int                                not null,
    name        varchar(255)                       not null,
    description varchar(255)                       not null,
    price       int unsigned                       not null,
    image_url   varchar(2048)                      null,
    created_at  datetime default CURRENT_TIMESTAMP not null,
    updated_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint product_shop_id_fk
        foreign key (shop_id) references shop (id)
);

