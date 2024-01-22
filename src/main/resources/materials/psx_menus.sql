CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `psx_users`
--

DROP TABLE IF EXISTS `psx_menus`;

CREATE TABLE `psx_menus` (
  `menu_id` varchar(3) NOT NULL,
  `menu_name`varchar(30) NOT NULL,
  `parent_name`varchar(30) NOT NULL,
  `active`varchar(1) NOT NULL check(status in ('Y','N')),
  `menu_href`varchar(30) NOT NULL,   
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
