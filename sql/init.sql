-- -----------------------------------------------------
-- Schema enrollment_system
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enrollment_system`;
CREATE SCHEMA IF NOT EXISTS `enrollment_system` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `enrollment_system`;

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `specializations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `specializations`;
CREATE TABLE IF NOT EXISTS `specializations` (
  `specialization_id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `limit` INT NOT NULL,
  PRIMARY KEY (`specialization_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `user_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_statuses`;
CREATE TABLE IF NOT EXISTS `user_statuses` (
  `user_status_id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_status_id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  `role_id` BIGINT NOT NULL,
  `user_status_id` BIGINT NOT NULL,
  `specialization_id` BIGINT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_users_roles_idx` (`role_id` ASC),
  INDEX `fk_users_specializations1_idx` (`specialization_id` ASC),
  INDEX `fk_users_user_statuses1_idx` (`user_status_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC),
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`role_id`)
    REFERENCES `roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_specializations1`
    FOREIGN KEY (`specialization_id`)
    REFERENCES `specializations` (`specialization_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_user_statuses1`
    FOREIGN KEY (`user_status_id`)
    REFERENCES `user_statuses` (`user_status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `subjects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `subjects`;
CREATE TABLE IF NOT EXISTS `subjects` (
  `subject_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `closed` TINYINT NOT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `user_subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_subject`;
CREATE TABLE IF NOT EXISTS `user_subject` (
  `user_id` BIGINT NOT NULL,
  `subject_id` BIGINT NOT NULL,
  `score` INT NULL,
  PRIMARY KEY (`user_id`, `subject_id`),
  INDEX `fk_users_has_subjects_subjects1_idx` (`subject_id` ASC),
  INDEX `fk_users_has_subjects_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_users_has_subjects_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_subjects_subjects1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `specialization_subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `specialization_subject`;
CREATE TABLE IF NOT EXISTS `specialization_subject` (
  `specialization_id` BIGINT NOT NULL,
  `subject_id` BIGINT NOT NULL,
  `subject_weight` DECIMAL(3,2) NOT NULL,
  PRIMARY KEY (`specialization_id`, `subject_id`),
  INDEX `fk_specializations_has_subjects_subjects1_idx` (`subject_id` ASC),
  INDEX `fk_specializations_has_subjects_specializations1_idx` (`specialization_id` ASC),
  CONSTRAINT `fk_specializations_has_subjects_specializations1`
    FOREIGN KEY (`specialization_id`)
    REFERENCES `specializations` (`specialization_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_specializations_has_subjects_subjects1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
