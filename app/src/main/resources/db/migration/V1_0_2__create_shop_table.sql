CREATE TABLE IF NOT EXISTS `shop_table`
(
    `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`    INT UNSIGNED NOT NULL,
    `number`     INT UNSIGNED NOT NULL,
    `capacity`   INT UNSIGNED NOT NULL,
    `created_at` DATETIME     NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `shop_id_and_number_UNIQUE` (`shop_id` ASC, `number` ASC) VISIBLE,
    INDEX `fk_shop_table_shop_id_idx` (`shop_id` ASC) VISIBLE,
    CONSTRAINT `fk_shop_table_shop_id`
        FOREIGN KEY (`shop_id`)
            REFERENCES `shop` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
