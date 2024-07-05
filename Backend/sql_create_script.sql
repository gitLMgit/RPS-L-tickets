-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(2) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Admin` (
  `User_idUser` INT NOT NULL,
  `yearsExperience` INT NOT NULL,
  PRIMARY KEY (`User_idUser`),
  CONSTRAINT `fk_Admin_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `mydb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Buyer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Buyer` (
  `User_idUser` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `age` INT NULL,
  PRIMARY KEY (`User_idUser`),
  CONSTRAINT `fk_Buyer_User`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `mydb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Location` (
  `idLocation` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `hasHall` INT NOT NULL,
  PRIMARY KEY (`idLocation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Event` (
  `idEvent` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
  `description` VARCHAR(200) NULL,
  `Category_idCategory` INT NOT NULL,
  `Location_idLocation` INT NOT NULL,
  PRIMARY KEY (`idEvent`),
  INDEX `fk_Event_Category1_idx` (`Category_idCategory` ASC) VISIBLE,
  INDEX `fk_Event_Location1_idx` (`Location_idLocation` ASC) VISIBLE,
  CONSTRAINT `fk_Event_Category1`
    FOREIGN KEY (`Category_idCategory`)
    REFERENCES `mydb`.`Category` (`idCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Event_Location1`
    FOREIGN KEY (`Location_idLocation`)
    REFERENCES `mydb`.`Location` (`idLocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Seat` (
  `idSeat` INT NOT NULL AUTO_INCREMENT,
  `section` VARCHAR(45) NOT NULL,
  `row` INT NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`idSeat`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Hall`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Hall` (
  `idHall` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `seatsNumber` INT NOT NULL,
  `Location_idLocation` INT NOT NULL,
  PRIMARY KEY (`idHall`),
  INDEX `fk_Hall_Location1_idx` (`Location_idLocation` ASC) VISIBLE,
  CONSTRAINT `fk_Hall_Location1`
    FOREIGN KEY (`Location_idLocation`)
    REFERENCES `mydb`.`Location` (`idLocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Seat_in_Hall`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Seat_in_Hall` (
  `idSeatHall` INT NOT NULL AUTO_INCREMENT,
  `Seat_idSeat` INT NOT NULL,
  `Hall_idHall` INT NOT NULL,
  PRIMARY KEY (`idSeatHall`),
  INDEX `fk_Seat_has_Hall_Hall1_idx` (`Hall_idHall` ASC) VISIBLE,
  INDEX `fk_Seat_has_Hall_Seat1_idx` (`Seat_idSeat` ASC) VISIBLE,
  CONSTRAINT `fk_Seat_has_Hall_Seat1`
    FOREIGN KEY (`Seat_idSeat`)
    REFERENCES `mydb`.`Seat` (`idSeat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Seat_has_Hall_Hall1`
    FOREIGN KEY (`Hall_idHall`)
    REFERENCES `mydb`.`Hall` (`idHall`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ticket` (
  `idTicket` INT NOT NULL AUTO_INCREMENT,
  `start_price` DOUBLE NOT NULL,
  `purchaseDate` DATE NOT NULL,
  `Event_idEvent` INT NOT NULL,
  `Seat_in_Hall_idSeatHall` INT NOT NULL,
  PRIMARY KEY (`idTicket`),
  INDEX `fk_Buyer_has_Event_Event1_idx` (`Event_idEvent` ASC) VISIBLE,
  INDEX `fk_Ticket_Seat_in_Hall1_idx` (`Seat_in_Hall_idSeatHall` ASC) VISIBLE,
  CONSTRAINT `fk_Buyer_has_Event_Event1`
    FOREIGN KEY (`Event_idEvent`)
    REFERENCES `mydb`.`Event` (`idEvent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Seat_in_Hall1`
    FOREIGN KEY (`Seat_in_Hall_idSeatHall`)
    REFERENCES `mydb`.`Seat_in_Hall` (`idSeatHall`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Rating` (
  `idRating` INT NOT NULL AUTO_INCREMENT,
  `Buyer_User_idUser` INT NOT NULL,
  `Event_idEvent` INT NOT NULL,
  `rate` INT NOT NULL,
  `comment` VARCHAR(200) NULL,
  PRIMARY KEY (`idRating`),
  INDEX `fk_Buyer_has_Event_Event2_idx` (`Event_idEvent` ASC) VISIBLE,
  INDEX `fk_Buyer_has_Event_Buyer2_idx` (`Buyer_User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Buyer_has_Event_Buyer2`
    FOREIGN KEY (`Buyer_User_idUser`)
    REFERENCES `mydb`.`Buyer` (`User_idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Buyer_has_Event_Event2`
    FOREIGN KEY (`Event_idEvent`)
    REFERENCES `mydb`.`Event` (`idEvent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bids`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bids` (
  `idBid` INT NOT NULL AUTO_INCREMENT,
  `bid_value` FLOAT NOT NULL,
  `bid_time` DATE NOT NULL,
  `Buyer_User_idUser` INT NOT NULL,
  `Ticket_idTicket` INT NOT NULL,
  PRIMARY KEY (`idBid`),
  INDEX `fk_Bids_Buyer1_idx` (`Buyer_User_idUser` ASC) VISIBLE,
  INDEX `fk_Bids_Ticket1_idx` (`Ticket_idTicket` ASC) VISIBLE,
  CONSTRAINT `fk_Bids_Buyer1`
    FOREIGN KEY (`Buyer_User_idUser`)
    REFERENCES `mydb`.`Buyer` (`User_idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bids_Ticket1`
    FOREIGN KEY (`Ticket_idTicket`)
    REFERENCES `mydb`.`Ticket` (`idTicket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CurrentBids`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CurrentBids` (
  `idCurrentBid` INT NOT NULL AUTO_INCREMENT,
  `currMaxValue` FLOAT NOT NULL,
  `Bids_idBid` INT NOT NULL,
  PRIMARY KEY (`idCurrentBid`),
  INDEX `fk_CurrentBids_Bids1_idx` (`Bids_idBid` ASC) VISIBLE,
  CONSTRAINT `fk_CurrentBids_Bids1`
    FOREIGN KEY (`Bids_idBid`)
    REFERENCES `mydb`.`Bids` (`idBid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
