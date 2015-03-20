drop database if exists `cse523_revision`;

create database `cse523_revision`;

CREATE TABLE `cse523_revision`.`people` (
  `pid` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `last_login_time` TIMESTAMP NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE INDEX `pid_UNIQUE` (`pid` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
  
CREATE TABLE `cse523_revision`.`courses` (
  `cid` INT NOT NULL AUTO_INCREMENT,
  `nickId` VARCHAR(45) NOT NULL,
  `cname` VARCHAR(45) NOT NULL,
  `semester` VARCHAR(45) NOT NULL,
  `professorId` INT NOT NULL,
  `professorName` VARCHAR(45) NOT NULL,
  `gradeRange` TEXT NOT NULL,
  `percentageFlag` VARCHAR(45) NOT NULL,
  `categories` TEXT NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE INDEX `cid_UNIQUE` (`cid` ASC));

CREATE TABLE `cse523_revision`.`people_courses` (
  `pid` INT NOT NULL,
  `cid` INT NOT NULL,
  `section` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  `data` TEXT NULL,
  `nickId` VARCHAR(45) NULL,
  `cname` VARCHAR(45) NULL,
  `semester` VARCHAR(45) NULL,
  `expectation` VARCHAR(45) NULL,
  `score` DECIMAL NULL,
  `grade` VARCHAR(45) NULL,
  PRIMARY KEY (`pid`, `cid`, `section`));

ALTER TABLE `cse523_revision`.`courses` 
ADD INDEX `fk_courses_pid_idx` (`professorId` ASC);
ALTER TABLE `cse523_revision`.`courses` 
ADD CONSTRAINT `fk_courses_pid`
  FOREIGN KEY (`professorId`)
  REFERENCES `cse523_revision`.`people` (`pid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `cse523_revision`.`people_courses` 
ADD INDEX `fk_pc_cid_idx` (`cid` ASC);
ALTER TABLE `cse523_revision`.`people_courses` 
ADD CONSTRAINT `fk_pc_pid`
  FOREIGN KEY (`pid`)
  REFERENCES `cse523_revision`.`people` (`pid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_pc_cid`
  FOREIGN KEY (`cid`)
  REFERENCES `cse523_revision`.`courses` (`cid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


