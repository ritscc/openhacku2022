CREATE TABLE `shop`
(
    `id`          INT AUTO_INCREMENT
        PRIMARY KEY,
    `code`        VARCHAR(16)                        NOT NULL,
    `name`        VARCHAR(64)                        NOT NULL,
    `description` TEXT                               NULL,
    `tel`         VARCHAR(255)                       NOT NULL,
    `created_at`  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_at`  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
);

