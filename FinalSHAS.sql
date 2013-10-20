-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.11 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2013-05-10 14:56:36
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for shas
DROP DATABASE IF EXISTS `shas`;
CREATE DATABASE IF NOT EXISTS `shas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shas`;


-- Dumping structure for table shas.configdevicestatus
DROP TABLE IF EXISTS `configdevicestatus`;
CREATE TABLE IF NOT EXISTS `configdevicestatus` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Status` varchar(3) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configdevicestatus: ~2 rows (approximately)
/*!40000 ALTER TABLE `configdevicestatus` DISABLE KEYS */;
INSERT INTO `configdevicestatus` (`ID`, `Status`) VALUES
	(1, 'ON'),
	(2, 'OFF');
/*!40000 ALTER TABLE `configdevicestatus` ENABLE KEYS */;


-- Dumping structure for table shas.configemergencyauthorities
DROP TABLE IF EXISTS `configemergencyauthorities`;
CREATE TABLE IF NOT EXISTS `configemergencyauthorities` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configemergencyauthorities: ~1 rows (approximately)
/*!40000 ALTER TABLE `configemergencyauthorities` DISABLE KEYS */;
INSERT INTO `configemergencyauthorities` (`ID`, `Name`, `Email`, `Number`) VALUES
	(1, 'Govind', 'imgovind@live.com', '8058861810');
/*!40000 ALTER TABLE `configemergencyauthorities` ENABLE KEYS */;


-- Dumping structure for table shas.configsmartplug
DROP TABLE IF EXISTS `configsmartplug`;
CREATE TABLE IF NOT EXISTS `configsmartplug` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceID` int(11) DEFAULT NULL,
  `StatusID` int(11) DEFAULT NULL,
  `DeviceThreshold` varchar(4) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DeviceID_UNIQUE` (`DeviceID`),
  KEY `DeviceID_idx` (`DeviceID`),
  KEY `StatusIDFromDeviceStatusTable_idx` (`StatusID`),
  CONSTRAINT `DeviceIDFromDevice` FOREIGN KEY (`DeviceID`) REFERENCES `devices` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `StatusIDFromDeviceStatusTable` FOREIGN KEY (`StatusID`) REFERENCES `configdevicestatus` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configsmartplug: ~11 rows (approximately)
/*!40000 ALTER TABLE `configsmartplug` DISABLE KEYS */;
INSERT INTO `configsmartplug` (`ID`, `DeviceID`, `StatusID`, `DeviceThreshold`, `TimeStamp`) VALUES
	(1, 1, 1, '2', '2013-05-10 12:59:00'),
	(2, 2, 2, '10', '2013-05-09 13:54:01'),
	(3, 3, 2, '10', '2013-05-09 13:54:01'),
	(4, 4, 2, '10', '2013-05-09 13:54:01'),
	(5, 5, 2, '10', '2013-05-09 13:54:01'),
	(6, 6, 2, '10', '2013-05-09 13:54:01'),
	(7, 7, 2, '10', '2013-05-09 13:54:01'),
	(8, 8, 2, '10', '2013-05-09 13:54:01'),
	(9, 9, 2, '10', '2013-05-09 13:54:01'),
	(10, 10, 2, '10', '2013-05-09 13:54:01'),
	(11, 11, 2, '10', '2013-05-09 13:54:01');
/*!40000 ALTER TABLE `configsmartplug` ENABLE KEYS */;


-- Dumping structure for table shas.configsmartrefrigerator
DROP TABLE IF EXISTS `configsmartrefrigerator`;
CREATE TABLE IF NOT EXISTS `configsmartrefrigerator` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `StartTime` varchar(3) DEFAULT NULL,
  `EndTime` varchar(3) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configsmartrefrigerator: ~1 rows (approximately)
/*!40000 ALTER TABLE `configsmartrefrigerator` DISABLE KEYS */;
INSERT INTO `configsmartrefrigerator` (`ID`, `StartTime`, `EndTime`, `TimeStamp`) VALUES
	(1, '18', '20', '2013-05-09 22:05:29');
/*!40000 ALTER TABLE `configsmartrefrigerator` ENABLE KEYS */;


-- Dumping structure for table shas.configsmartshower
DROP TABLE IF EXISTS `configsmartshower`;
CREATE TABLE IF NOT EXISTS `configsmartshower` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User1` varchar(3) DEFAULT NULL,
  `User2` varchar(3) DEFAULT NULL,
  `User3` varchar(3) DEFAULT NULL,
  `User4` varchar(3) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configsmartshower: ~1 rows (approximately)
/*!40000 ALTER TABLE `configsmartshower` DISABLE KEYS */;
INSERT INTO `configsmartshower` (`ID`, `User1`, `User2`, `User3`, `User4`, `TimeStamp`) VALUES
	(1, '80', '85', '90', '95', '2013-05-09 21:38:07');
/*!40000 ALTER TABLE `configsmartshower` ENABLE KEYS */;


-- Dumping structure for table shas.configsmartsprinkler
DROP TABLE IF EXISTS `configsmartsprinkler`;
CREATE TABLE IF NOT EXISTS `configsmartsprinkler` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DesiredMoisture` varchar(3) DEFAULT NULL,
  `StartTime` varchar(4) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configsmartsprinkler: ~1 rows (approximately)
/*!40000 ALTER TABLE `configsmartsprinkler` DISABLE KEYS */;
INSERT INTO `configsmartsprinkler` (`ID`, `DesiredMoisture`, `StartTime`, `TimeStamp`) VALUES
	(2, '90', '18', '2013-05-09 21:38:23');
/*!40000 ALTER TABLE `configsmartsprinkler` ENABLE KEYS */;


-- Dumping structure for table shas.configsmartthermostat
DROP TABLE IF EXISTS `configsmartthermostat`;
CREATE TABLE IF NOT EXISTS `configsmartthermostat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CurrentTemperature` varchar(3) DEFAULT NULL,
  `DesiredTemperature` varchar(3) DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configsmartthermostat: ~1 rows (approximately)
/*!40000 ALTER TABLE `configsmartthermostat` DISABLE KEYS */;
INSERT INTO `configsmartthermostat` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `StartTime`, `EndTime`, `TimeStamp`) VALUES
	(1, '40', '50', '2013-05-09 05:00:00', '2013-05-09 06:00:00', '2013-05-09 10:26:17');
/*!40000 ALTER TABLE `configsmartthermostat` ENABLE KEYS */;


-- Dumping structure for table shas.configsmartwaterheater
DROP TABLE IF EXISTS `configsmartwaterheater`;
CREATE TABLE IF NOT EXISTS `configsmartwaterheater` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `StartTime` varchar(3) DEFAULT NULL,
  `EndTime` varchar(3) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.configsmartwaterheater: ~1 rows (approximately)
/*!40000 ALTER TABLE `configsmartwaterheater` DISABLE KEYS */;
INSERT INTO `configsmartwaterheater` (`ID`, `StartTime`, `EndTime`, `TimeStamp`) VALUES
	(5, '18', '20', '2013-05-09 22:05:50');
/*!40000 ALTER TABLE `configsmartwaterheater` ENABLE KEYS */;


-- Dumping structure for table shas.devices
DROP TABLE IF EXISTS `devices`;
CREATE TABLE IF NOT EXISTS `devices` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `DeviceStatus` varchar(3) DEFAULT 'OFF',
  `DeviceThreshold` varchar(4) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `DeviceStatusMapping_idx` (`DeviceStatus`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.devices: ~11 rows (approximately)
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` (`ID`, `Name`, `Description`, `DeviceStatus`, `DeviceThreshold`, `TimeStamp`) VALUES
	(1, 'Thermostat', 'Thermostat device used to manage temperature', 'ON', NULL, '2013-05-10 12:59:00'),
	(2, 'Smart Meter', 'Smart Meter', 'OFF', NULL, '2013-05-09 13:27:06'),
	(3, 'Smart Water Heater', 'Handles the water heater in house', 'OFF', NULL, '2013-05-09 13:27:06'),
	(4, 'Smart Refrigerator', 'Manages the refrigerator in the house', 'OFF', NULL, '2013-05-09 13:27:06'),
	(5, 'Smart Sprinkler', 'Control the sprinkler system in house.', 'OFF', NULL, '2013-05-09 13:27:06'),
	(6, 'Smart Shower', 'Smartly manage the shower in the house', 'OFF', NULL, '2013-05-09 13:27:06'),
	(7, 'Smart Plug', 'Control appliances inside the house.', 'OFF', NULL, '2013-05-09 13:27:06'),
	(8, 'Coffee Maker', 'Something that you use to make coffee', 'OFF', NULL, '2013-05-09 13:27:06'),
	(9, 'Microwave Oven', 'Cooks the food, by using Microwaves', 'OFF', NULL, '2013-05-09 13:27:06'),
	(10, 'Television', 'Used to watch many shows and spend time', 'OFF', NULL, '2013-05-09 13:27:06'),
	(11, 'Conventional Oven', 'Used for baking and works off gas', 'OFF', NULL, '2013-05-09 13:27:06');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;


-- Dumping structure for table shas.logsmartmeter
DROP TABLE IF EXISTS `logsmartmeter`;
CREATE TABLE IF NOT EXISTS `logsmartmeter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceID` int(11) DEFAULT NULL,
  `EnergyUsed` varchar(45) DEFAULT NULL,
  `WaterUsed` varchar(45) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.logsmartmeter: ~27 rows (approximately)
/*!40000 ALTER TABLE `logsmartmeter` DISABLE KEYS */;
INSERT INTO `logsmartmeter` (`ID`, `DeviceID`, `EnergyUsed`, `WaterUsed`, `TimeStamp`) VALUES
	(1, 6, 'NULL', '45', '2013-05-09 22:33:29'),
	(2, 6, 'NULL', '45', '2013-05-09 22:48:29'),
	(3, 5, 'NULL', '0', '2013-05-09 23:03:29'),
	(4, 4, '1.875', 'NULL', '2013-05-09 23:18:29'),
	(5, 1, '0.0', 'NULL', '2013-05-09 23:33:29'),
	(6, 1, '2.7375', 'NULL', '2013-05-09 23:48:29'),
	(7, 1, '1.36875', 'NULL', '2013-05-10 00:03:29'),
	(8, 1, '1.36875', 'NULL', '2013-05-10 00:18:29'),
	(9, 1, '1.36875', 'NULL', '2013-05-10 00:33:29'),
	(10, 1, '1.36875', 'NULL', '2013-05-10 00:48:29'),
	(11, 1, '0.0', 'NULL', '2013-05-10 01:03:29'),
	(12, 5, 'NULL', '0', '2013-05-10 01:18:29'),
	(13, 4, '3.75', 'NULL', '2013-05-10 01:33:29'),
	(14, 6, 'NULL', '45', '2013-05-10 01:48:29'),
	(15, 6, 'NULL', '150', '2013-05-10 02:03:29'),
	(16, 1, '0.0', 'NULL', '2013-05-10 02:18:29'),
	(17, 1, '1.36875', 'NULL', '2013-05-10 02:33:29'),
	(18, 1, '1.36875', 'NULL', '2013-05-10 02:48:29'),
	(19, 1, '1.36875', 'NULL', '2013-05-10 03:03:29'),
	(20, 1, '1.36875', 'NULL', '2013-05-10 03:18:29'),
	(21, 1, '1.36875', 'NULL', '2013-05-10 03:33:29'),
	(22, 1, '0.0', 'NULL', '2013-05-10 03:48:29'),
	(23, 6, 'NULL', '45', '2013-05-10 04:03:29'),
	(24, 6, 'NULL', '0', '2013-05-10 04:18:29'),
	(25, 6, 'NULL', '45', '2013-05-10 04:33:29'),
	(26, 4, '0.0', 'NULL', '2013-05-10 04:48:29'),
	(27, 5, 'NULL', '0', '2013-05-10 05:03:29');
/*!40000 ALTER TABLE `logsmartmeter` ENABLE KEYS */;


-- Dumping structure for table shas.logsmartrefrigerator
DROP TABLE IF EXISTS `logsmartrefrigerator`;
CREATE TABLE IF NOT EXISTS `logsmartrefrigerator` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DoorStatus` varchar(1) DEFAULT NULL,
  `DeviceStatus` varchar(1) DEFAULT NULL,
  `EnergyMode` varchar(1) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.logsmartrefrigerator: ~13 rows (approximately)
/*!40000 ALTER TABLE `logsmartrefrigerator` DISABLE KEYS */;
INSERT INTO `logsmartrefrigerator` (`ID`, `DoorStatus`, `DeviceStatus`, `EnergyMode`, `TimeStamp`) VALUES
	(1, '3', '3', '2', '2013-05-09 14:00:00'),
	(2, '3', '3', '2', '2013-05-09 14:15:00'),
	(3, '0', '3', '2', '2013-05-09 14:30:00'),
	(4, '0', '3', '2', '2013-05-09 14:45:00'),
	(5, '3', '3', '2', '2013-05-09 15:00:00'),
	(6, '3', '3', '1', '2013-05-09 15:15:00'),
	(7, '0', '3', '1', '2013-05-09 15:30:00'),
	(8, '3', '3', '1', '2013-05-09 15:45:00'),
	(9, '0', '3', '1', '2013-05-09 16:00:00'),
	(10, '3', '3', '1', '2013-05-09 16:15:00'),
	(11, '3', '3', '1', '2013-05-09 23:18:29'),
	(12, '3', '3', '2', '2013-05-10 01:33:29'),
	(13, '0', '0', '0', '2013-05-10 04:48:29');
/*!40000 ALTER TABLE `logsmartrefrigerator` ENABLE KEYS */;


-- Dumping structure for table shas.logsmartshower
DROP TABLE IF EXISTS `logsmartshower`;
CREATE TABLE IF NOT EXISTS `logsmartshower` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserSelection` varchar(1) DEFAULT '1',
  `WaterStatus` varchar(1) DEFAULT NULL,
  `DeviceStatus` varchar(1) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.logsmartshower: ~15 rows (approximately)
/*!40000 ALTER TABLE `logsmartshower` DISABLE KEYS */;
INSERT INTO `logsmartshower` (`ID`, `UserSelection`, `WaterStatus`, `DeviceStatus`, `TimeStamp`) VALUES
	(1, '1', '3', '3', '2013-05-09 07:00:00'),
	(2, '1', '3', '3', '2013-05-09 07:15:00'),
	(3, '1', '0', '0', '2013-05-09 07:30:00'),
	(4, '1', '0', '0', '2013-05-09 07:35:00'),
	(5, '1', '3', '3', '2013-05-09 08:00:00'),
	(6, '2', '3', '3', '2013-05-09 08:15:00'),
	(7, '2', '3', '3', '2013-05-09 08:30:00'),
	(8, '2', '0', '0', '2013-05-09 08:45:00'),
	(9, '1', '3', '3', '2013-05-09 22:33:29'),
	(10, '1', '3', '3', '2013-05-09 22:48:29'),
	(11, '1', '3', '3', '2013-05-10 01:48:29'),
	(12, '1', '0', '3', '2013-05-10 02:03:29'),
	(13, '1', '3', '3', '2013-05-10 04:03:29'),
	(14, '1', '0', '3', '2013-05-10 04:18:29'),
	(15, '1', '3', '3', '2013-05-10 04:33:29');
/*!40000 ALTER TABLE `logsmartshower` ENABLE KEYS */;


-- Dumping structure for table shas.logsmartsprinkler
DROP TABLE IF EXISTS `logsmartsprinkler`;
CREATE TABLE IF NOT EXISTS `logsmartsprinkler` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CurrentMoisture` varchar(3) DEFAULT NULL,
  `DesiredMoisture` varchar(3) DEFAULT NULL,
  `WaterStatus` varchar(1) DEFAULT NULL,
  `DeviceStatus` varchar(1) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.logsmartsprinkler: ~13 rows (approximately)
/*!40000 ALTER TABLE `logsmartsprinkler` DISABLE KEYS */;
INSERT INTO `logsmartsprinkler` (`ID`, `CurrentMoisture`, `DesiredMoisture`, `WaterStatus`, `DeviceStatus`, `TimeStamp`) VALUES
	(1, '62', '70', '0', '0', '2013-05-08 06:00:00'),
	(2, '63', '70', '3', '3', '2013-05-08 06:15:00'),
	(3, '64', '70', '3', '3', '2013-05-08 06:30:00'),
	(4, '65', '70', '3', '3', '2013-05-08 06:45:00'),
	(5, '66', '70', '3', '3', '2013-05-08 07:00:00'),
	(6, '67', '70', '3', '3', '2013-05-08 07:15:00'),
	(7, '68', '70', '3', '3', '2013-05-08 07:30:00'),
	(8, '69', '70', '3', '3', '2013-05-08 07:45:00'),
	(9, '70', '70', '3', '3', '2013-05-08 08:00:00'),
	(10, '70', '70', '0', '0', '2013-05-08 08:15:00'),
	(11, '68', '80', '0', '0', '2013-05-09 23:03:29'),
	(12, '26', '90', '0', '0', '2013-05-10 01:18:29'),
	(13, '80', '80', '0', '0', '2013-05-10 05:03:29');
/*!40000 ALTER TABLE `logsmartsprinkler` ENABLE KEYS */;


-- Dumping structure for table shas.logsmartwaterheater
DROP TABLE IF EXISTS `logsmartwaterheater`;
CREATE TABLE IF NOT EXISTS `logsmartwaterheater` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CurrentTemperature` varchar(3) DEFAULT NULL,
  `DesiredTemperature` varchar(3) DEFAULT NULL,
  `DeviceStatus` varchar(1) DEFAULT NULL,
  `EnergyMode` varchar(1) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.logsmartwaterheater: ~10 rows (approximately)
/*!40000 ALTER TABLE `logsmartwaterheater` DISABLE KEYS */;
INSERT INTO `logsmartwaterheater` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `EnergyMode`, `TimeStamp`) VALUES
	(1, '64', '80', '3', '3', '2013-05-09 14:00:00'),
	(2, '68', '80', '3', '3', '2013-05-09 14:15:00'),
	(3, '72', '80', '3', '3', '2013-05-09 14:30:00'),
	(4, '74', '80', '3', '2', '2013-05-09 15:00:00'),
	(5, '76', '80', '3', '2', '2013-05-09 15:15:00'),
	(6, '77', '80', '3', '1', '2013-05-09 15:30:00'),
	(7, '78', '80', '3', '1', '2013-05-09 15:45:00'),
	(8, '79', '80', '3', '1', '2013-05-09 16:00:00'),
	(9, '80', '80', '3', '1', '2013-05-09 16:15:00'),
	(10, '80', '80', '0', '0', '2013-05-09 16:30:00');
/*!40000 ALTER TABLE `logsmartwaterheater` ENABLE KEYS */;


-- Dumping structure for table shas.logsthermostat
DROP TABLE IF EXISTS `logsthermostat`;
CREATE TABLE IF NOT EXISTS `logsthermostat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CurrentTemperature` varchar(3) DEFAULT NULL,
  `DesiredTemperature` varchar(3) DEFAULT NULL,
  `DeviceStatus` varchar(1) DEFAULT NULL,
  `OperatingMode` varchar(1) DEFAULT NULL,
  `EnergyMode` varchar(1) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.logsthermostat: ~35 rows (approximately)
/*!40000 ALTER TABLE `logsthermostat` DISABLE KEYS */;
INSERT INTO `logsthermostat` (`ID`, `CurrentTemperature`, `DesiredTemperature`, `DeviceStatus`, `OperatingMode`, `EnergyMode`, `TimeStamp`) VALUES
	(227, '60', '80', '0', '2', '0', '2013-05-09 15:45:00'),
	(228, '64', '80', '3', '3', '3', '2013-05-09 16:00:00'),
	(229, '68', '80', '3', '3', '3', '2013-05-09 16:15:00'),
	(230, '72', '80', '3', '3', '3', '2013-05-09 16:30:00'),
	(231, '74', '80', '3', '3', '2', '2013-05-09 16:45:00'),
	(232, '76', '80', '3', '3', '2', '2013-05-09 17:00:00'),
	(233, '77', '80', '3', '3', '1', '2013-05-09 17:15:00'),
	(234, '78', '80', '3', '3', '1', '2013-05-09 17:30:00'),
	(235, '79', '80', '3', '3', '1', '2013-05-09 17:45:00'),
	(236, '80', '80', '3', '3', '1', '2013-05-09 18:00:00'),
	(237, '80', '80', '0', '2', '0', '2013-05-09 18:15:00'),
	(238, '85', '80', '0', '2', '0', '2013-05-09 18:30:00'),
	(239, '95', '80', '0', '2', '0', '2013-05-09 18:45:00'),
	(240, '91', '80', '3', '1', '3', '2013-05-09 19:00:00'),
	(241, '87', '80', '3', '1', '3', '2013-05-09 19:15:00'),
	(242, '85', '80', '3', '1', '2', '2013-05-09 19:30:00'),
	(243, '83', '80', '3', '1', '2', '2013-05-09 19:45:00'),
	(244, '82', '80', '3', '1', '1', '2013-05-09 20:00:00'),
	(245, '81', '80', '3', '1', '1', '2013-05-09 20:15:00'),
	(246, '80', '80', '3', '1', '1', '2013-05-09 20:30:00'),
	(247, '80', '80', '0', '2', '0', '2013-05-09 20:45:00'),
	(248, '59', '65', '3', '3', '0', '2013-05-09 23:33:29'),
	(249, '61', '65', '3', '3', '2', '2013-05-09 23:48:29'),
	(250, '62', '65', '3', '3', '1', '2013-05-10 00:03:29'),
	(251, '63', '65', '3', '3', '1', '2013-05-10 00:18:29'),
	(252, '64', '65', '3', '3', '1', '2013-05-10 00:33:29'),
	(253, '65', '65', '3', '3', '1', '2013-05-10 00:48:29'),
	(254, '65', '65', '3', '2', '0', '2013-05-10 01:03:29'),
	(255, '75', '80', '3', '3', '0', '2013-05-10 02:18:29'),
	(256, '76', '80', '3', '3', '1', '2013-05-10 02:33:29'),
	(257, '77', '80', '3', '3', '1', '2013-05-10 02:48:29'),
	(258, '78', '80', '3', '3', '1', '2013-05-10 03:03:29'),
	(259, '79', '80', '3', '3', '1', '2013-05-10 03:18:29'),
	(260, '80', '80', '3', '3', '1', '2013-05-10 03:33:29'),
	(261, '80', '80', '3', '2', '0', '2013-05-10 03:48:29');
/*!40000 ALTER TABLE `logsthermostat` ENABLE KEYS */;


-- Dumping structure for table shas.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `Mobile` varchar(10) DEFAULT '8058861810',
  `Password` varchar(45) DEFAULT NULL,
  `RememberMe` varchar(1) DEFAULT 'N',
  `SecurityQuestion` varchar(45) DEFAULT NULL,
  `SecurityAnswer` varchar(45) DEFAULT NULL,
  `TimeStamp` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table shas.user: ~4 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`, `UserName`, `Mobile`, `Password`, `RememberMe`, `SecurityQuestion`, `SecurityAnswer`, `TimeStamp`) VALUES
	(1, 'mailgovind11@gmail.com', '8058861810', 'govind', 'N', 'Who is great?', 'govind', '2013-05-01 11:31:37'),
	(2, 'jyn.jayan@gmail.com', '9727611896', 'govind', 'N', 'Who is great?', 'govind', '2013-04-28 21:05:37'),
	(3, 'kundhavi08@gmail.com', '8188779928', 'govind', 'N', 'Who is great?', 'govind', '2013-05-09 01:48:27'),
	(4, 'riyudds@gmail.com', '9198079811', 'govind', 'N', 'Who is great?', 'govind', '2013-05-09 01:48:27');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
