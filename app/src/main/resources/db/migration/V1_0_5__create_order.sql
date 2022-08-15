create table `order`
(
    id           int auto_increment
        primary key,
    shop_id      int                                not null,
    user_id      int                                not null,
    price        int unsigned                       not null,
    ordered_date datetime                           null,
    created_at   datetime default CURRENT_TIMESTAMP not null,
    updated_at   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    status       int                                not null,
    constraint order_shop_id_fk
        foreign key (shop_id) references shop (id),
    constraint order_user_id_fk
        foreign key (user_id) references user (id)
);

