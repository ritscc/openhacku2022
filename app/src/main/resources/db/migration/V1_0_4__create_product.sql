CREATE TABLE `product`
(
    `id`          INT AUTO_INCREMENT
        PRIMARY KEY,
    `shop_id`     INT                                NOT NULL,
    `name`        VARCHAR(255)                       NOT NULL,
    `description` VARCHAR(255)                       NOT NULL,
    `price`       INT UNSIGNED                       NOT NULL,
    `image_url`   VARCHAR(2048)                      NULL,
    `created_at`  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_at`  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT product_shop_id_fk
        FOREIGN KEY (`shop_id`) REFERENCES shop (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

