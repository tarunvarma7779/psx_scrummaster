CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `psx_users`
--

DROP TABLE IF EXISTS `psx_roles`;

CREATE TABLE `psx_roles` (
  `role_id` varchar(30) NOT NULL,
  `active`varchar(1) NOT NULL check(active in ('Y','N')),
  `created_by`varchar(10) NOT NULL,
  `created_on` datetime NOT NULL,
  `menu_json`varchar(4000) NOT NULL,
   
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
