CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `scrum_requests`
--

DROP TABLE IF EXISTS `scrum_user_requests`;

CREATE TABLE `scrum_user_requests` (
  `request_id` varchar(30) NOT NULL,
  `raised_by` varchar(30) NOT NULL,
  `raised_to` varchar(30) NOT NULL,
  `operation_time` datetime NOT NULL,
  `active` numeric(1) NOT NULL check(active in (0,1)),
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
