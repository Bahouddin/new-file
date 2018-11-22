USE `ads`;

DROP TABLE IF EXISTS `advertisement`;
DROP TABLE IF EXISTS `categoty`;
DROP TABLE IF EXISTS `news`;
DROP TABLE IF EXISTS `user_info`;
DROP TABLE IF EXISTS `user`;

--удалаем все ограничения
USE `ads`;
ALTER TABLE `advertisement`
DROP FOREIGN KEY FK_advertisement_user_info;

ALTER TABLE `advertisement`
DROP FOREIGN KEY FK_advertisement_categoty; 

ALTER TABLE `categoty`
DROP  FOREIGN KEY FK_categoty_categoty;

ALTER TABLE `news`
DROP FOREIGN KEY FK_news_user_info;



 


