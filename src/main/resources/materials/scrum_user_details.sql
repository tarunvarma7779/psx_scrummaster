CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;
--
-- Table structure for table `scrum_user_details`
--
DROP TABLE IF EXISTS `scrum_user_details`;

CREATE TABLE `scrum_user_details` (
  `emp_id` varchar(10) NOT NULL,
  `username` varchar(30) NOT NULL UNIQUE,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `department_name` varchar(50) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `reporting_to` varchar(10) NOT NULL,
  FOREIGN KEY (username) REFERENCES scrum_users(username),
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
