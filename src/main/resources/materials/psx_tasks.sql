CREATE DATABASE  IF NOT EXISTS `scrummaster`;
USE `scrummaster`;

--
-- Table structure for table `psx_tasks`
--

DROP TABLE IF EXISTS `psx_tasks`;

CREATE TABLE `psx_tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_name`varchar(45) DEFAULT NULL,
  `task_details` varchar(255) DEFAULT NULL,
  `assigned_by` varchar(45) DEFAULT NULL,
  `assigned_to` varchar(45) DEFAULT NULL,
  `priority` varchar(1) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `closed_on` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

