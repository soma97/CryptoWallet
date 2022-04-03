-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema wallet
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wallet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wallet` DEFAULT CHARACTER SET utf8 ;
USE `wallet` ;

-- -----------------------------------------------------
-- Table `wallet`.`currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`currency` (
  `currency` VARCHAR(4) NOT NULL,
  `real` TINYINT NULL,
  PRIMARY KEY (`currency`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wallet`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`player` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `currency` VARCHAR(4) NOT NULL,
  `credit` BIGINT NULL,
  `external_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_player_currency_idx` (`currency` ASC) VISIBLE,
  CONSTRAINT `fk_player_currency`
    FOREIGN KEY (`currency`)
    REFERENCES `wallet`.`currency` (`currency`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wallet`.`wallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`wallet` (
  `hash` VARCHAR(70) NOT NULL,
  `currency` VARCHAR(4) NOT NULL,
  `player_id` INT NOT NULL,
  `virtual_credit` BIGINT NOT NULL,
  PRIMARY KEY (`hash`),
  INDEX `fk_wallet_currency1_idx` (`currency` ASC) VISIBLE,
  INDEX `fk_wallet_player1_idx` (`player_id` ASC) VISIBLE,
  CONSTRAINT `fk_wallet_currency1`
    FOREIGN KEY (`currency`)
    REFERENCES `wallet`.`currency` (`currency`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wallet_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `wallet`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wallet`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`transaction` (
  `type` INT NOT NULL,
  `hash` VARCHAR(70) NOT NULL,
  `wallet_hash` VARCHAR(70) NOT NULL,
  INDEX `fk_transaction_wallet1_idx` (`wallet_hash` ASC) VISIBLE,
  PRIMARY KEY (`hash`),
  CONSTRAINT `fk_transaction_wallet1`
    FOREIGN KEY (`wallet_hash`)
    REFERENCES `wallet`.`wallet` (`hash`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX unique_wallet ON wallet(currency, player_id);

DELIMITER $$

CREATE TRIGGER currency_trigger
    BEFORE INSERT
    ON player FOR EACH ROW
BEGIN
	DECLARE is_real TINYINT;
    SELECT currency.real FROM currency WHERE currency=NEW.currency INTO is_real;
    
    IF is_real = false THEN
        SIGNAL SQLSTATE '45000'  SET MESSAGE_TEXT = "Cannot set virtual cryptocurrency as primary currency for player.";
    END IF;
END$$    

DELIMITER ;


DELIMITER $$

CREATE TRIGGER currency_trigger_wallet
    BEFORE INSERT
    ON wallet FOR EACH ROW
BEGIN
	DECLARE is_real TINYINT;
    SELECT currency.real FROM currency WHERE currency=NEW.currency INTO is_real;
    
    IF is_real THEN
        SIGNAL SQLSTATE '45000'  SET MESSAGE_TEXT = "Cannot set real currency for crypto wallet.";
    END IF;
END$$    

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
