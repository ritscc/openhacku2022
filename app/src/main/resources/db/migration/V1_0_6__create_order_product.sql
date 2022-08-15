CREATE TABLE `order_product`
(
    `order_id`   INT                                NOT NULL,
    `product_id` INT                                NOT NULL,
    `name`       VARCHAR(255)                       NOT NULL,
    `price`      INT UNSIGNED                       NOT NULL,
    `quantity`   INT UNSIGNED                       NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`order_id`, `product_id`),
    CONSTRAINT order_product_order_id_fk
        FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT order_product_product_id_fk
        FOREIGN KEY (`product_id`) REFERENCES product (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

