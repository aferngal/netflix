-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: netflix
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actors`
--

DROP TABLE IF EXISTS `actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actors` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(256) NOT NULL,
  `SURNAME` varchar(256) NOT NULL,
  `DAY_OF_BIRTH` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actors`
--

LOCK TABLES `actors` WRITE;
/*!40000 ALTER TABLE `actors` DISABLE KEYS */;
INSERT INTO `actors` VALUES (1,'PETER','DINKLAGE','1980-05-25'),(2,'EMILIA','CLARKE','1990-08-22'),(3,'DANIEL','ITO','1986-03-29'),(4,'Bryan','Cranston','1984-02-25'),(6,'Jose','Pota','1980-01-01'),(7,'Paco','Mer','1900-07-05'),(8,'Manolo','Loco','1985-01-02');
/*!40000 ALTER TABLE `actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `awards`
--

DROP TABLE IF EXISTS `awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `awards` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(256) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `awards`
--

LOCK TABLES `awards` WRITE;
/*!40000 ALTER TABLE `awards` DISABLE KEYS */;
INSERT INTO `awards` VALUES (1,'EMMY MEJOR SERIE'),(2,'EMMY MEJOR ACTOR');
/*!40000 ALTER TABLE `awards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(60) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `unique` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (3,'ACCIÓN'),(2,'COMEDIA'),(4,'DRAMA'),(1,'TERROR');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories_tv_shows`
--

DROP TABLE IF EXISTS `categories_tv_shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories_tv_shows` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` bigint(20) NOT NULL,
  `TV_SHOW_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`),
  KEY `TV_SHOW_ID` (`TV_SHOW_ID`),
  CONSTRAINT `categories_tv_shows_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `categories` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `categories_tv_shows_ibfk_2` FOREIGN KEY (`TV_SHOW_ID`) REFERENCES `tv_shows` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_tv_shows`
--

LOCK TABLES `categories_tv_shows` WRITE;
/*!40000 ALTER TABLE `categories_tv_shows` DISABLE KEYS */;
INSERT INTO `categories_tv_shows` VALUES (1,1,1),(2,3,1),(3,2,2),(4,1,2);
/*!40000 ALTER TABLE `categories_tv_shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chapters` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NUMBER` tinyint(4) NOT NULL,
  `NAME` varchar(256) NOT NULL,
  `DURATION` tinyint(4) NOT NULL,
  `SEASON_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CHAPTERS_SEASON_ID` (`SEASON_ID`),
  CONSTRAINT `FK_CHAPTERS_SEASON_ID` FOREIGN KEY (`SEASON_ID`) REFERENCES `seasons` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters`
--

LOCK TABLES `chapters` WRITE;
/*!40000 ALTER TABLE `chapters` DISABLE KEYS */;
INSERT INTO `chapters` VALUES (1,1,'Chapter 1',43,1),(2,2,'Chapter 2',45,1),(3,3,'Chapter 3',44,1),(4,1,'Chapter 0',50,2),(5,1,'Chapter1',40,6),(6,1,'Chapter1',42,3);
/*!40000 ALTER TABLE `chapters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapters_actors`
--

DROP TABLE IF EXISTS `chapters_actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chapters_actors` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CHAPTER_ID` bigint(20) NOT NULL,
  `ACTOR_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CHAPTER_ID` (`CHAPTER_ID`),
  KEY `ACTOR_ID` (`ACTOR_ID`),
  CONSTRAINT `chapters_actors_ibfk_1` FOREIGN KEY (`CHAPTER_ID`) REFERENCES `chapters` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `chapters_actors_ibfk_2` FOREIGN KEY (`ACTOR_ID`) REFERENCES `actors` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters_actors`
--

LOCK TABLES `chapters_actors` WRITE;
/*!40000 ALTER TABLE `chapters_actors` DISABLE KEYS */;
INSERT INTO `chapters_actors` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2),(5,4,2),(6,5,3),(7,6,3),(8,4,1);
/*!40000 ALTER TABLE `chapters_actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seasons`
--

DROP TABLE IF EXISTS `seasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `seasons` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NUMBER` tinyint(4) NOT NULL,
  `NAME` varchar(256) NOT NULL,
  `TV_SHOW_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SEASONS_TV_SHOW_ID` (`TV_SHOW_ID`),
  CONSTRAINT `FK_SEASONS_TV_SHOW_ID` FOREIGN KEY (`TV_SHOW_ID`) REFERENCES `tv_shows` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seasons`
--

LOCK TABLES `seasons` WRITE;
/*!40000 ALTER TABLE `seasons` DISABLE KEYS */;
INSERT INTO `seasons` VALUES (1,1,'One',1),(2,2,'Two',1),(3,1,'One',2),(4,2,'Two',2),(5,3,'Three',2),(6,1,'One',3);
/*!40000 ALTER TABLE `seasons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tv_shows`
--

DROP TABLE IF EXISTS `tv_shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tv_shows` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(256) NOT NULL,
  `SHORT_DESC` varchar(256) DEFAULT NULL,
  `LONG_DESC` varchar(2048) DEFAULT NULL,
  `YEAR` year(4) NOT NULL,
  `RECOMMENDED_AGE` tinyint(4) NOT NULL,
  `ADVERTISING` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv_shows`
--

LOCK TABLES `tv_shows` WRITE;
/*!40000 ALTER TABLE `tv_shows` DISABLE KEYS */;
INSERT INTO `tv_shows` VALUES (1,'Juego de tronos','Descripción corta','Descripción larga',2012,16,NULL),(2,'American horror Story',NULL,NULL,2010,16,NULL),(3,'Big Bang',NULL,NULL,2008,7,NULL);
/*!40000 ALTER TABLE `tv_shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tv_shows_awards`
--

DROP TABLE IF EXISTS `tv_shows_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tv_shows_awards` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TV_SHOWS_ID` bigint(20) NOT NULL,
  `AWARDS_ID` bigint(20) NOT NULL,
  `YEAR` year(4) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `TV_SHOWS_ID` (`TV_SHOWS_ID`),
  KEY `AWARDS_ID` (`AWARDS_ID`),
  CONSTRAINT `tv_shows_awards_ibfk_1` FOREIGN KEY (`TV_SHOWS_ID`) REFERENCES `tv_shows` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tv_shows_awards_ibfk_2` FOREIGN KEY (`AWARDS_ID`) REFERENCES `awards` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv_shows_awards`
--

LOCK TABLES `tv_shows_awards` WRITE;
/*!40000 ALTER TABLE `tv_shows_awards` DISABLE KEYS */;
INSERT INTO `tv_shows_awards` VALUES (1,1,1,2018),(2,1,2,2018),(3,2,1,2019);
/*!40000 ALTER TABLE `tv_shows_awards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-25 14:42:19
