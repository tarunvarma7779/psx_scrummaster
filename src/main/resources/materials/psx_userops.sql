CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `psx_userops`
--

DROP TABLE IF EXISTS `psx_userops`;

CREATE TABLE `psx_userops` (
  `operation_time` datetime NOT NULL,
  `user_id`varchar(10) NOT NULL,
  `operation_type`varchar(30) NOT NULL,
  `role` varchar(50) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
