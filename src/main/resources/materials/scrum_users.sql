CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;
--
-- Table structure for table `scrum_users`
--
DROP TABLE IF EXISTS `scrum_users`;

CREATE TABLE `scrum_users` (
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `created_on` datetime NOT NULL,
  `approved_on` datetime DEFAULT NULL,
  `action_by` varchar(30) DEFAULT NULL,
  `reason` varchar(2000) DEFAULT NULL,
  `locked` numeric(1) NOT NULL check(locked in (0,1)),
  `active` numeric(1) NOT NULL check(active in (0,1)),
  PRIMARY KEY (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
