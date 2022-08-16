CREATE TABLE IF NOT EXISTS `order`
(
    `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `shop_id`      INT UNSIGNED NOT NULL,
    `user_id`      INT UNSIGNED NOT NULL,
    `status`       INT UNSIGNED NOT NULL,
    `ordered_date` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `fk_order_user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_order_shop_id_idx` (`shop_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_shop_id`
        FOREIGN KEY (`shop_id`)
            REFERENCES `shop` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB