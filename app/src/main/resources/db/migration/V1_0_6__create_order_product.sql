create table order_product
(
    order_id   int                                not null,
    product_id int                                not null,
    name       varchar(255)                       not null,
    price      int unsigned                       not null,
    quantity   int unsigned                       not null,
    created_at datetime default CURRENT_TIMESTAMP not null,
    updated_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    primary key (order_id, product_id),
    constraint order_product_order_id_fk
        foreign key (order_id) references `order` (id),
    constraint order_product_product_id_fk
        foreign key (product_id) references product (id)
);

