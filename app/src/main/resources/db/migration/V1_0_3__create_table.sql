CREATE TABLE `table`
(
    `id`         INT AUTO_INCREMENT
        PRIMARY KEY,
    `shop_id`    INT                                NOT NULL,
    `capacity`   INT UNSIGNED                       NOT NULL,
    `category`   INT UNSIGNED                       NOT NULL,
    `status`     INT UNSIGNED                       NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT table_shop_id_fk
        FOREIGN KEY (`shop_id`) REFERENCES shop (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

