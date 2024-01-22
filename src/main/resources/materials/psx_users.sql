CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `psx_users`
--

DROP TABLE IF EXISTS `psx_users`;

CREATE TABLE `psx_users` (
  `user_name` varchar(45) NOT NULL,
  `password`varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `teammates` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

