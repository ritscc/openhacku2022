CREATE TABLE `user`
(
    `id`         INT AUTO_INCREMENT
        PRIMARY KEY,
    `first_name` VARCHAR(255)                       NOT NULL,
    `last_name`  VARCHAR(255)                       NOT NULL,
    `tel`        VARCHAR(255)                       NOT NULL,
    `email`      VARCHAR(255)                       NOT NULL,
    `password`   VARCHAR(255)                       NOT NULL,
    `icon_url`   VARCHAR(2048)                      NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `is_admin`   BOOLEAN  DEFAULT FALSE             NOT NULL,
    CONSTRAINT user_email_uindex
        UNIQUE (`email`),
    CONSTRAINT user_tel_uindex
        UNIQUE (`tel`)
);

