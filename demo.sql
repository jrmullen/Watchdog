-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2016 at 04:39 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bsbuckne`
--

DROP TABLE IF EXISTS tag_to_video;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS video;
DROP TABLE IF EXISTS device;
DROP TABLE IF EXISTS user_classification;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_type;
DROP TABLE IF EXISTS permissions;

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `DEVICE_ID` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT '1',
  `DEVICE_NAME` varchar(30) NOT NULL,
  `DEVICE_MAC` varchar(17) DEFAULT NULL,
  `DEVICE_ADDRESS` varchar(45) DEFAULT NULL,
  `DEVICE_PORT` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`DEVICE_ID`, `USER_ID`, `DEVICE_NAME`, `DEVICE_MAC`, `DEVICE_ADDRESS`, `DEVICE_PORT`) VALUES
(1, 1, 'Jeremy''s Pi', 'b8:27:eb:a0:64:06', 'jmullen.ddns.net', '8081'),
(2, 1, 'Renee''s Logitech Camera', 'b8:27:eb:b3:10:82', 'rlwise.ddns.net', '8081');

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `PERMISS_ID` int(11) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  `PERMISS_DESCRIPTION` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`PERMISS_ID`, `ROLE`, `PERMISS_DESCRIPTION`) VALUES
(1, 'ROLE_ADMIN', 'Users can do what users with ROLE_USER can do plus: manage devices, set user permissions, and view, record, and delete videos and tags.'),
(2, 'ROLE_USER', 'A user with basic permissions that can only view live video feed and edit his or her profile.');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE `tag` (
  `TAG_ID` int(11) NOT NULL,
  `TAG_NAME` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tag_to_video`
--

CREATE TABLE `tag_to_video` (
  `TAG_ID` int(11) NOT NULL,
  `VID_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL,
  `PERMISS_ID` int(11) DEFAULT '2',
  `USER_FNAME` varchar(30) NOT NULL,
  `USER_LNAME` varchar(30) NOT NULL,
  `USER_EMAIL` varchar(50) NOT NULL,
  `USER_PASSWORD` varchar(60) NOT NULL,
  `ENABLED` tinyint(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`USER_ID`, `PERMISS_ID`, `USER_FNAME`, `USER_LNAME`, `USER_EMAIL`, `USER_PASSWORD`, `ENABLED`) VALUES
(1, 1, 'admin', 'admin', 'admin@email.com', '$2a$10$LYpEMdLWyM8hzH/KxDO0VOLcEuO/YkhLfvMtQp4Kzo1ydCt/OKfdi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE `video` (
  `VID_ID` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT '1',
  `VID_FILE_PATH` varchar(255) NOT NULL,
  `VID_LENGTH` time DEFAULT '00:00:01',
  `VID_IS_COMPRESSED` tinyint(1) NOT NULL DEFAULT '0',
  `VID_IS_ENCRYPTED` tinyint(1) NOT NULL DEFAULT '0',
  `VID_SIZE_ON_DISK` bigint(20) NOT NULL DEFAULT '0',
  `VID_DATE` date DEFAULT NULL,
  `VID_TIME` time DEFAULT NULL,
  `VID_TITLE` varchar(50) NOT NULL,
  `VID_LOCATION` decimal(12,8) DEFAULT NULL,
  `VID_DESCRIPTION` varchar(255) DEFAULT NULL,
  `DEVICE_MAC` varchar(17) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`DEVICE_ID`),
  ADD UNIQUE KEY `DEVICE_NAME` (`DEVICE_NAME`),
  ADD UNIQUE KEY `DEVICE_MAC` (`DEVICE_MAC`),
  ADD KEY `DEVICE_ibfk_1` (`USER_ID`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`PERMISS_ID`),
  ADD UNIQUE KEY `ROLE` (`ROLE`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`TAG_ID`),
  ADD UNIQUE KEY `TAG_NAME` (`TAG_NAME`);

--
-- Indexes for table `tag_to_video`
--
ALTER TABLE `tag_to_video`
  ADD PRIMARY KEY (`TAG_ID`,`VID_ID`),
  ADD KEY `TAG_TO_VIDEO_ibfk_2` (`VID_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`USER_ID`),
  ADD UNIQUE KEY `USER_EMAIL` (`USER_EMAIL`),
  ADD KEY `USER_ibfk_1` (`PERMISS_ID`);

--
-- Indexes for table `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`VID_ID`),
  ADD UNIQUE KEY `VID_TITLE` (`VID_TITLE`),
  ADD KEY `VIDEO_ibfk_1` (`USER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `device`
--
ALTER TABLE `device`
  MODIFY `DEVICE_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `PERMISS_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `TAG_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `video`
--
ALTER TABLE `video`
  MODIFY `VID_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `DEVICE_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`);

--
-- Constraints for table `tag_to_video`
--
ALTER TABLE `tag_to_video`
  ADD CONSTRAINT `TAG_TO_VIDEO_ibfk_1` FOREIGN KEY (`TAG_ID`) REFERENCES `tag` (`TAG_ID`),
  ADD CONSTRAINT `TAG_TO_VIDEO_ibfk_2` FOREIGN KEY (`VID_ID`) REFERENCES `video` (`VID_ID`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `USER_ibfk_1` FOREIGN KEY (`PERMISS_ID`) REFERENCES `permissions` (`PERMISS_ID`);

--
-- Constraints for table `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `VIDEO_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
