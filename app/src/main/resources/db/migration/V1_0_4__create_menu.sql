CREATE TABLE IF NOT EXISTS `menu`
(
    `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`    INT UNSIGNED NOT NULL,
    `name`       VARCHAR(255) NOT NULL,
    `price`      INT UNSIGNED NOT NULL,
    `image_url`  VARCHAR(255) NOT NULL,
    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `fk_menu_shop_id_idx` (`shop_id` ASC) VISIBLE,
    CONSTRAINT `fk_menu_shop_id`
        FOREIGN KEY (`shop_id`)
            REFERENCES `shop` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB