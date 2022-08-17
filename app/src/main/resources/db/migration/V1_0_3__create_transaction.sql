CREATE TABLE IF NOT EXISTS `transaction`
(
    `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`          INT UNSIGNED NOT NULL,
    `table_id`         INT UNSIGNED NOT NULL,
    `code`             CHAR(36)     NOT NULL,
    `number_of_people` INT          NOT NULL,
    `created_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
    INDEX `fk_transaction_shop_table_id_idx` (`table_id` ASC) VISIBLE,
    INDEX `fk_transaction_shop_id_idx` (`shop_id` ASC) VISIBLE,
    CONSTRAINT `fk_transaction_shop_id`
        FOREIGN KEY (`shop_id`)
            REFERENCES `shop` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_transaction_shop_table_id`
        FOREIGN KEY (`table_id`)
            REFERENCES `shop_table` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
