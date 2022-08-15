CREATE TABLE `order`
(
    `id`           INT AUTO_INCREMENT
        PRIMARY KEY,
    `shop_id`      INT                                NOT NULL,
    `user_id`      INT                                NOT NULL,
    `price`        INT UNSIGNED                       NOT NULL,
    `ordered_date` DATETIME                           NULL,
    `created_at`   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_at`   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `status`       INT                                NOT NULL,
    CONSTRAINT order_shop_id_fk
        FOREIGN KEY (`shop_id`) REFERENCES shop (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT order_user_id_fk
        FOREIGN KEY (`user_id`) REFERENCES user (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

