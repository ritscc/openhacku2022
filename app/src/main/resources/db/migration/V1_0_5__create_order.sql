CREATE TABLE IF NOT EXISTS `order`
(
    `id`             INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `transaction_id` INT UNSIGNED NOT NULL,
    `ordered_date`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_at`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `fk_order_transaction_id_idx` (`transaction_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_transaction_id`
        FOREIGN KEY (`transaction_id`)
            REFERENCES `transaction` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB