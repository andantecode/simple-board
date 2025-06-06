-- -----------------------------------------------------
-- Schema simple_board
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `simple_board` DEFAULT CHARACTER SET utf8 ;
USE `simple_board` ;

-- -----------------------------------------------------
-- Table `simple_board`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_board`.`board` (
                                                      `id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT 'Primary KEY',
    `board_name` VARCHAR(100) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simple_board`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_board`.`post` (
                                                     `id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
    `board_id` BIGINT(32) NOT NULL,
    `user_name` VARCHAR(45) NOT NULL,
    `password` VARCHAR(4) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `text` TEXT NULL,
    `posted_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simple_board`.`reply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_board`.`reply` (
                                                      `id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
    `post_id` BIGINT(32) NOT NULL,
    `user_name` VARCHAR(45) NOT NULL,
    `password` VARCHAR(4) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `content` TEXT NOT NULL,
    `replied_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;