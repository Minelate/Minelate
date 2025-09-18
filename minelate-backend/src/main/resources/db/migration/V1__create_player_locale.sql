CREATE TABLE IF NOT EXISTS player_locale
(
    player_uuid VARCHAR(36) NOT NULL PRIMARY KEY,
    locale      VARCHAR(10) NOT NULL
);