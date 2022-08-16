CREATE TABLE IF NOT EXISTS `table`
(
    `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`    INT UNSIGNED NOT NULL,
    `capacity`   INT UNSIGNED NOT NULL,
    `created_at` DATETIME     NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `fk_table_shop_id_idx` (`shop_id` ASC) VISIBLE,
    CONSTRAINT `fk_table_shop_id`
        FOREIGN KEY (`shop_id`)
            REFERENCES `shop` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB