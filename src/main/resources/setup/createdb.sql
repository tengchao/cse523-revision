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
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
  
  
CREATE TABLE `cse523_revision`.`courses` (
  `cid` INT NOT NULL AUTO_INCREMENT,
  `nickid` VARCHAR(45) NOT NULL,
  `cname` VARCHAR(45) NOT NULL,
  `section` INT NOT NULL DEFAULT 0,
  `factor` DOUBLE NOT NULL DEFAULT 1,
  `location` VARCHAR(45) NOT NULL DEFAULT 'TBD',
  `time` VARCHAR(45) NOT NULL DEFAULT 'TBD',
  `seats` INT NOT NULL,
  `available_seats` INT NOT NULL,
  `semester` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cid`));
  
CREATE TABLE `cse523_revision`.`people_courses` (
  `pid` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `data` TEXT NULL,
  `cid` INT NOT NULL,
  `nickid` VARCHAR(45) NOT NULL,
  `cname` VARCHAR(45) NOT NULL,
  `semester` VARCHAR(45) NOT NULL,
  `section` INT NOT NULL DEFAULT 0,
  `expectation` VARCHAR(2) NULL,
  `score` DOUBLE NULL,
  `grade` VARCHAR(2) NULL,
  PRIMARY KEY (`pid`, `cid`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `pc_foreign_cid_idx` (`cid` ASC),
  CONSTRAINT `pc_foreign_pid`
    FOREIGN KEY (`pid`)
    REFERENCES `cse523_revision`.`people` (`pid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pc_foreign_cid`
    FOREIGN KEY (`cid`)
    REFERENCES `cse523_revision`.`courses` (`cid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `cse523_revision`.`perference` (
  `pid` INT NOT NULL,
  `label` VARCHAR(45) NOT NULL,
  `perference` TEXT NOT NULL,
  PRIMARY KEY (`pid`, `label`),
  CONSTRAINT `pre_foreign_pid`
    FOREIGN KEY (`pid`)
    REFERENCES `cse523_revision`.`people` (`pid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `cse523_revision`.`people_courses` 
ADD CONSTRAINT `pc_foreign_email`
  FOREIGN KEY (`email`)
  REFERENCES `cse523_revision`.`people` (`email`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `cse523_revision`.`courses` 
DROP COLUMN `factor`,
DROP COLUMN `time`,
DROP COLUMN `location`,
DROP COLUMN `section`,
ADD INDEX `courses_semester` (`semester` ASC);

ALTER TABLE `cse523_revision`.`courses` 
DROP INDEX `courses_semester` ,
ADD INDEX `courses_index_semester` (`semester` ASC);

ALTER TABLE `cse523_revision`.`people_courses` 
DROP INDEX `pc_foreign_cid_idx` ,
ADD INDEX `pc_index_cid` (`cid` ASC),
ADD INDEX `pc_index_pid` (`pid` ASC),
ADD INDEX `pc_index_pid_semester` (`pid` ASC, `semester` ASC),
ADD INDEX `pc_index_cid_semester` (`cid` ASC, `semester` ASC),
ADD INDEX `pc_index_cid_semester_section` (`cid` ASC, `semester` ASC, `section` ASC);

CREATE TABLE `cse523_revision`.`updates` (
  `cid` INT NOT NULL,
  `nickid` VARCHAR(45) NOT NULL,
  `cname` VARCHAR(45) NOT NULL,
  `section` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `targetId` INT NOT NULL,
  `targetName` VARCHAR(45) NOT NULL,
  `requestStatus` VARCHAR(45) NOT NULL,
  `requestReason` TEXT NULL,
  `updaterId` INT NOT NULL,
  `updaterName` VARCHAR(45) NOT NULL,
  `updaterComment` TEXT NULL,
  PRIMARY KEY (`cid`, `section`, `timestamp`, `targetId`, `updaterId`),
  INDEX `updates_foreign_targetId_idx` (`targetId` ASC),
  INDEX `updates_foreign_updaterId_idx` (`updaterId` ASC),
  INDEX `updates_index_section` (`section` ASC),
  INDEX `updates_index_requestStatus` (`requestStatus` ASC),
  INDEX `updates_index_section_requestStatus` (`section` ASC, `requestStatus` ASC),
  INDEX `updates_index_section_updaterId` (`section` ASC, `updaterId` ASC),
  INDEX `update_index_section_updaterId_requestStatus` (`section` ASC, `updaterId` ASC, `requestStatus` ASC),
  CONSTRAINT `updates_foreign_cid`
    FOREIGN KEY (`cid`)
    REFERENCES `cse523_revision`.`courses` (`cid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `updates_foreign_targetId`
    FOREIGN KEY (`targetId`)
    REFERENCES `cse523_revision`.`people` (`pid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `updates_foreign_updaterId`
    FOREIGN KEY (`updaterId`)
    REFERENCES `cse523_revision`.`people` (`pid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `cse523_revision`.`people_courses` 
  DROP FOREIGN KEY `pc_foreign_email`;
ALTER TABLE `cse523_revision`.`people_courses` 
  DROP COLUMN `email`,
  DROP INDEX `email_UNIQUE` ;


