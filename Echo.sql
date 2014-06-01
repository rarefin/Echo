---------------------------------
Table structure for table `user`
---------------------------------

CREATE TABLE IF NOT EXISTS `user` (
    `id`                	INT(16) NOT NULL AUTO_INCREMENT,
    `version`           	BIGINT(20) DEFAULT NULL,
    `firstName`         	VARCHAR(255) DEFAULT NULL,
    `lastName`          	VARCHAR(255) DEFAULT NULL,
    `emailAddress`      	VARCHAR(255) DEFAULT NULL,
    `userName`          	VARCHAR(255) DEFAULT NULL,
    `password`          	VARCHAR(255) DEFAULT NULL,
	
  	PRIMARY KEY (`id`),
    UNIQUE KEY (`userName`)
);

----------------------------------
Table structure for table `echo`
----------------------------------

CREATE TABLE IF NOT EXISTS `echo` (
  `id`                INT(16)  NOT NULL AUTO_INCREMENT,
  `version`           BIGINT(20) DEFAULT NULL,
  `postedBy`          VARCHAR(255) DEFAULT NULL,
  `createdDate`       VARCHAR(255) DEFAULT NULL,
  `lastModifiedDate`  DATETIME,
  `accuracy`          DOUBLE DEFAULT NULL,
  `altitude`          DOUBLE DEFAULT NULL,
  `geoLocation`       VARCHAR(255) DEFAULT NULL,
  `geoTimeStamp`      VARCHAR(255) DEFAULT NULL,
  `latitude`          DOUBLE DEFAULT NULL,
  `longitude`         DOUBLE DEFAULT NULL,
  `speed`         	  DOUBLE DEFAULT NULL,
  `echo`              VARCHAR(255) DEFAULT NULL,
  `issueCategory`     VARCHAR(255) DEFAULT NULL,
  `anonymous`         BIT(1)     NOT NULL,
  `createdBy_id`      INT(11) DEFAULT NULL,
  `lastModifiedBy_id` INT(11) DEFAULT NULL,
  `issueCategory_id`  INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


------------------------------------------
Table structure for table `issue_category`
------------------------------------------

CREATE TABLE IF NOT EXISTS `issue_category` (
  `id`                INT(11) NOT NULL AUTO_INCREMENT,
  `createdDate`       VARCHAR(255) DEFAULT NULL,
  `lastModifiedDate`  DATE,
  `isNew`             BIT(1)  NOT NULL,
  `title`             VARCHAR(255) DEFAULT NULL,
  `version`           BIGINT(20) DEFAULT NULL,
  `createdBy_id`      INT(11) DEFAULT NULL,
  `lastModifiedBy_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

------------------------------------------
Table structure for table `photo`
----------------------------------------

CREATE TABLE IF NOT EXISTS `photo` (
  `id`          	INT(16) NOT NULL AUTO_INCREMENT,
  `postedBy`        VARCHAR(255) DEFAULT NULL,
  `contentType` 	VARCHAR(255) DEFAULT NULL,
  `original`    	LONGBLOB,
  `thumbnail`   	LONGBLOB,
  `version`     	BIGINT(20) DEFAULT NULL,
  `echo_id`    		INT(16) NOT NULL,
  `createdBy_id`    INT(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-------------------------------
Table structure for table `tag`
-------------------------------

CREATE TABLE IF NOT EXISTS `tag` (
  `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title`   VARCHAR(255) DEFAULT NULL,
  `version` BIGINT(20) DEFAULT NULL,
  `echo_id` BIGINT(20), 
  PRIMARY KEY (`id`)
);