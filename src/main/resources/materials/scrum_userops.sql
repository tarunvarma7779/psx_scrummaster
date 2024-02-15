CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `scrum_userops`
--

DROP TABLE IF EXISTS `scrum_userops`;

CREATE TABLE `scrum_userops` (
  `operation_time` datetime NOT NULL,
  `username`varchar(10) NOT NULL,
  `operation_type`varchar(30) NOT NULL,
  `role` varchar(50) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
