USE `ads`;

INSERT INTO `user`(`login`,`password`,`role`) VALUES
("admin", "admin", 0),
("user1","user1", 1),
("user2","user2", 1);
--(1,"hojiakbar","yadgarov","hakimovich","hojisheyx@gmail.com"
--,+375255216355,"admin","dushanbe",LOAD_FILE("c:\Users\hojis\eclipse-workspace\Ads\WebContent\img\user\admin.png"));


--SELECT `user`.`id` AS `id`,  `login`, `role`,`password` FROM `user` INNER JOIN `user_info` on user.id = user_info.id
Use ads;

INSERT INTO `user_info`(`id`,`lastname`,`firstname`,`patronymic`,`email`,`phone`,`city`,`img`) VALUES
(1,`bahouddin`,`ashurov`,`ashurivich`,`jengo@gmail.com`,375255216344,`Minsk`,null);

INSERT INTO `user_info`(`id`,`lastname`,`firstname`,
`patronymic`,`email`,`phone`,`city`,`img`) VALUES
(1,"hojiakbar","yadgarov","hakimovich","hojisheyx@gmail.com"
,+375255216355,"dushanbe",LOAD_FILE("c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/user/admin.png")),
(2,"orif","yadgarov","hakimovich","orif@gmail.com"
,+375355216355,"dushanbe",LOAD_FILE("c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/user/user1.png")),
(3,"maks","petrov","sergievich","maks@gmail.com"
,+375255216455,"minsk",LOAD_FILE("c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/user/user2.png"));
--2
INSERT INTO `user_info`(`id`,`lastname`,`firstname`,
`patronymic`,`email`,`phone`,`city`,`img`) VALUES
(4,"hasan","salomov","jaborivich","hasan.salomov@gmail.com"
,+375245577899,"Regar",LOAD_FILE("c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/user/admin2.png")),
(5,"Ruzibek","haydarov","Namedonmivich","haydarov@gmail.com"
,+375355216359,"Shahrituz",LOAD_FILE("c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/user/admin3.png")),
(6,"Ibrohim","Zoirov","Talabshoivich","zoirov.ibrohim1996@gmail.com"
,+375255216454,"Minsk",LOAD_FILE("c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/user/admin4.png"));

INSERT INTO `news`(`id`,`date`,`path_img`,`text`,`user_info_id`) VALUES
(1,STR_TO_DATE('10-05-2018','%d-%m-%Y'),"c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/news/news01.png","на сайте появилыся раздел недвижимость",1),

INSERT INTO `news`(`id`,`date`,`path_img`,`text`,`user_info_id`) VALUES
(1,STR_TO_DATE('10-05-2018','%d-%m-%Y'),"c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/news/news01.png","на сайте появилыся раздел недвижимость",1),
(2,STR_TO_DATE('11-05-2018','%d-%m-%Y'),"c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/news/phone.png","на сайте появилыся раздел телефон",2),
(3,STR_TO_DATE('12-05-2018','%d-%m-%Y'),"c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/news/car.png","на сайте появилыся раздел транспорт",3),
(4,STR_TO_DATE('13-05-2018','%d-%m-%Y'),"c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/news/mebel.png","на сайте появилыся раздел все для дома",4);


INSERT INTO `category`(`id`,`name`,`parent_category_id`) VALUES
(1,"транспорт",null),
(2,"недвижимость",null),
(3,"телефон",null),
(4,"легковой автомобиль",1),
(5,"грузовой автомобиль",1),
(6,"мотоцикл",1),
(7,"дом",2),
(8,"квартира",2);

---
INSERT INTO `category`(`id`,`name`,`parent_category_id`) VALUES
(1,"транспорт",null),
(2,"недвижимость",null),
(3,"телефон",null),
(4,"")
(9,"phone 6s",3),
(10,"samsung j5",3),
(11,"Nokia",3),
(12,"Mersedes",1),
(13,"Audi",1);


INSERT INTO `advertisement`(`id`,`date`,`user_info_id`,`text`,`path_img`,`price`,`status`,`isSell`,`category_id`) VALUES
(1,STR_TO_DATE('10-05-2018','%d-%m-%Y'),2,"на сайте появилыся раздел все для дома","c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/ads/mebel.png",450,0,1,1),
(2,STR_TO_DATE('11-05-2018','%d-%m-%Y'),2,"на сайте появилыся раздел телефон","c:/Users/hojis/eclipse-workspace/Ads/WebContent/img/ads/phone.png",550,0,0,2);





