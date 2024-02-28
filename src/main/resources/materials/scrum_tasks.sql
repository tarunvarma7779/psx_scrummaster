CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;
--
-- Table structure for table `scrum_tasks`
--
DROP TABLE IF EXISTS `scrum_tasks`;

CREATE TABLE `scrum_tasks` (
  `task_id` varchar(30) NOT NULL,
  `task_name` varchar(255) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `assigned_to` varchar(10) NOT NULL,
  `assigned_by` varchar(10) NOT NULL,
  `created_on` datetime NOT NULL,
  `deadline` datetime NOT NULL,
  `closed_on` datetime DEFAULT NULL,
  `priority` numeric(1) NOT NULL check(priority in (1,2,3,4)),
  `active` numeric(1) NOT NULL check(active in (0,1)),
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
