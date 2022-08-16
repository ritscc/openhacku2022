CREATE TABLE IF NOT EXISTS `order_menu`
(
    `order_id`   INT UNSIGNED NOT NULL,
    `menu_id`    INT UNSIGNED NOT NULL,
    `quantity`   INT UNSIGNED NOT NULL,
    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`menu_id`, `order_id`),
    INDEX `fk_order_menu_order_id_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_menu_order_id`
        FOREIGN KEY (`order_id`)
            REFERENCES `order` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_menu_menu_id`
        FOREIGN KEY (`menu_id`)
            REFERENCES `menu` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB