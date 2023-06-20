-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: creatorconnect
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `connection_requests`
--

DROP TABLE IF EXISTS `connection_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connection_requests` (
  `RequestID` bigint NOT NULL AUTO_INCREMENT,
  `OrgID` bigint DEFAULT NULL,
  `InfluencerID` bigint DEFAULT NULL,
  `RequestMessage` text,
  `RequestStatus` enum('Pending','Accepted','Denied') DEFAULT NULL,
  PRIMARY KEY (`RequestID`),
  KEY `OrgID` (`OrgID`),
  KEY `InfluencerID` (`InfluencerID`),
  CONSTRAINT `connection_requests_ibfk_1` FOREIGN KEY (`OrgID`) REFERENCES `organizations` (`OrgID`),
  CONSTRAINT `connection_requests_ibfk_2` FOREIGN KEY (`InfluencerID`) REFERENCES `influencers` (`InfluencerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connection_requests`
--

LOCK TABLES `connection_requests` WRITE;
/*!40000 ALTER TABLE `connection_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `connection_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `influencers`
--

DROP TABLE IF EXISTS `influencers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `influencers` (
  `InfluencerID` bigint NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `ProfileImage` varchar(255) DEFAULT NULL,
  `Gender` enum('Male','Female','Other') DEFAULT NULL,
  `InfluencerName` varchar(255) DEFAULT NULL,
  `InfluencerType` varchar(255) DEFAULT NULL,
  `InterestedIn` varchar(255) DEFAULT NULL,
  `MinRate` bigint DEFAULT NULL,
  `PreviousBrands` text,
  `Location` varchar(255) DEFAULT NULL,
  `BestPosts` text,
  PRIMARY KEY (`InfluencerID`),
  CONSTRAINT `influencers_ibfk_1` FOREIGN KEY (`InfluencerID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `influencers`
--

LOCK TABLES `influencers` WRITE;
/*!40000 ALTER TABLE `influencers` DISABLE KEYS */;
/*!40000 ALTER TABLE `influencers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizations`
--

DROP TABLE IF EXISTS `organizations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizations` (
  `OrgID` bigint NOT NULL,
  `OrgName` varchar(255) DEFAULT NULL,
  `ProfileImage` varchar(255) DEFAULT NULL,
  `CompanyType` varchar(255) DEFAULT NULL,
  `Size` bigint DEFAULT NULL,
  `WebsiteLink` varchar(255) DEFAULT NULL,
  `TargetInfluencerType` varchar(255) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`OrgID`),
  CONSTRAINT `organizations_ibfk_1` FOREIGN KEY (`OrgID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizations`
--

LOCK TABLES `organizations` WRITE;
/*!40000 ALTER TABLE `organizations` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_media`
--

DROP TABLE IF EXISTS `social_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_media` (
  `SMID` bigint NOT NULL AUTO_INCREMENT,
  `UserID` bigint DEFAULT NULL,
  `Platform` varchar(50) DEFAULT NULL,
  `Link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SMID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `social_media_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_media`
--

LOCK TABLES `social_media` WRITE;
/*!40000 ALTER TABLE `social_media` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` bigint NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-17 22:52:48
