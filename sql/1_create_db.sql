DROP DATABASE IF EXISTS `ads`;

CREATE DATABASE `ads` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `ads`.*
TO user_ads@'%'
IDENTIFIED BY 'user_pw';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `ads`.*
TO user_ads@'localhost'
IDENTIFIED BY 'user_pw';