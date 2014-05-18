---------------------------------
Table structure for table `user`
---------------------------------

CREATE TABLE IF NOT EXISTS `user` (
    `id`                	INT(16) NOT NULL AUTO_INCREMENT,
    `version`           	BIGINT(20) DEFAULT NULL,
    `firstName`         	VARCHAR(255) DEFAULT NULL,
    `lastName`          	VARCHAR(255) DEFAULT NULL,
    `gender`            	VARCHAR(255) DEFAULT NULL,
    `emailAddress`      	VARCHAR(255) DEFAULT NULL,
    `userName`          	VARCHAR(255) DEFAULT NULL,
    `password`          	VARCHAR(255) DEFAULT NULL,
    `accountNonExpired` 	BIT(1)     NOT NULL,
    `accountNonLocked`  	BIT(1)     NOT NULL,
    `enabled`           	BIT(1)     NOT NULL,
    `credentialsNonExpired` BIT(1)     NOT NULL,
	
  	PRIMARY KEY (`id`),
    UNIQUE KEY (`userName`)
);

----------------------------------
Table structure for table `echo`
----------------------------------

CREATE TABLE IF NOT EXISTS `echo` (
  `id`                INT (16) NOT NULL AUTO_INCREMENT,
  `createdDate`       DATETIME,
  `lastModifiedDate`  DATETIME,
  `accuracy`          DOUBLE DEFAULT NULL,
  `address`           VARCHAR(255) DEFAULT NULL,
  `altitude`          DOUBLE DEFAULT NULL,
  `anonymous`         BIT(1)     NOT NULL,
  `geoLocation`       VARCHAR(255) DEFAULT NULL,
  `geoTimeStamp`      DATETIME DEFAULT NULL,
  `latitude`          DOUBLE DEFAULT NULL,
  `longitude`         DOUBLE DEFAULT NULL,
  `echo`              VARCHAR(255) DEFAULT NULL,
  `timeStamp`         DATETIME,
  `version`           BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-------------------------------------
Table structure for table `authority`
-------------------------------------

CREATE TABLE IF NOT EXISTS `authority` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(255) DEFAULT NULL,
  `version` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


------------------------------------------
Table structure for table `issue_category`
------------------------------------------

CREATE TABLE IF NOT EXISTS `issue_category` (
  `id`                INT(11) NOT NULL AUTO_INCREMENT,
  `createdDate`       TINYBLOB,
  `lastModifiedDate`  TINYBLOB,
  `isNew`             BIT(1)  NOT NULL,
  `title`             VARCHAR(255) DEFAULT NULL,
  `version`           BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


------------------------------------------
Table structure for table `photo`
----------------------------------------

CREATE TABLE IF NOT EXISTS `photo` (
  `id`          INT(16) NOT NULL AUTO_INCREMENT,
  `contentType` VARCHAR(255) DEFAULT NULL,
  `original`    LONGBLOB,
  `thumbnail`   LONGBLOB,
  `version`     BIGINT(20) DEFAULT NULL,
  `issue_id`    BINARY(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
