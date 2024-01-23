CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `psx_users`
--

DROP TABLE IF EXISTS `psx_users`;

CREATE TABLE `psx_users` (
  `user_id` varchar(30) NOT NULL,
  `password`varchar(255) NOT NULL,
  `emp_id`varchar(10) NOT NULL unique,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `department_name` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `email_id`varchar(255) NOT NULL,
  `reporting_to`varchar(10) NOT NULL,
  `created_on`datetime NOT NULL,
  `approved_on`datetime default NULL,
  `active`varchar(1) NOT NULL check(active in ('Y','N')),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
