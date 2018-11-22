USE `ads`;

CREATE TABLE `user` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(200) NOT NULL UNIQUE,
	`password` VARCHAR(200) NOT NULL,
	
	/*
	 * 0 - ADMIN
	 * 1 - CUSTOMER
	 */
	`role` TINYINT NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info` (
	`id` INTEGER NOT NULL,
	`lastname` VARCHAR(40) NOT NULL,
    `firstname` VARCHAR(50) NOT NULL,
    `patronymic`VARCHAR(30) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `phone` INTEGER(20) NOT NULL,
	`password` VARCHAR(40) NOT NULL,
	`city` VARCHAR(40) NOT NULL,
	`img`LONGBLOB NOT NULL,  
	PRIMARY KEY (`id`),
	CONSTRAINT FK_user_info_user FOREIGN KEY (`id`)
	REFERENCES user(`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

/* НОВОСТИ*/
CREATE TABLE `news` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`date` DATE,
	`img` LONGBLOB NOT NULL,
	`text` VARCHAR(300) NOT NULL,
	`user_info_id` INTEGER NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT FK_news_user_info FOREIGN KEY (`user_info_id`)
	REFERENCES user_info(`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

/*КАТЕГОРИ*/
CREATE TABLE `categoty` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL,
	`parent_category_id` INTEGER NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT FK_categoty_categoty FOREIGN KEY (`parent_category_id`)
	REFERENCES categoty(`id`)
	
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

/*ОБъЯВЛЕНИЯ*/
CREATE TABLE `advertisement`(
     `id` INTEGER NOT NULL AUTO_INCREMENT,
	`date` DATE,
	`user_info_id` INTEGER NOT NULL,
	`text` VARCHAR(300) NOT NULL,
	`img` LONGBLOB NOT NULL,
	`price` INTEGER (10) NOT NULL,
	`status` BOOLEAN NOT NULL,
	`isSell` BOOLEAN NOT NULL
	`category_id` INTEGER NOT NULL,
	 PRIMARY KEY (`id`),
	CONSTRAINT FK_advertisement_categoty FOREIGN KEY (`category_id`)
	REFERENCES categoty(`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


--добавить внешний ключ
ALTER `advertisement`
ADD CONSTRAINT FK_advertisement_user_info
FOREIGN KEY (`user_info_id`)
REFERENCES user_info(`id`);
--USE `ads`

ALTER `advertisement`
ADD CONSTRAINT FK_advertisement_user_info
FOREIGN KEY (`user_info_id`)
REFERENCES `user_info`(`id`)
ON DELETE NO ACTION 
  ON UPDATE CASCADE