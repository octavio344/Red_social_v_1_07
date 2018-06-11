-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: userdb
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `Idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Contraseña` varchar(45) NOT NULL,
  `Fecha_nacimiento` date NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Edad` int(11) DEFAULT NULL,
  PRIMARY KEY (`Idusuario`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'sad','dsad','yolo123','123','2018-05-17','esasadd@gmail.com',NULL),(3,'yolo','dsaf','dfsfd123','123','2018-06-02','dsfddsffsd@gmail.com',NULL),(6,'fdsfd','fdfsfds','123','123','2018-06-02','yolo@gmail.com',NULL),(7,'fdsdfsfds','fdsfdsdsf','fdsfddfs123','123','2018-06-02','dfsfdsf@gmail.com',NULL),(8,'octavio','ibanez','octi99','octavio9','1999-05-17','octavioibaniez@gmail.com',NULL),(9,'octasd','dfsfds','octavio9','octavio9','2018-06-02','octavio@gmail.com',NULL),(13,'paco','rabon','raboncito123','123','2018-06-03','paco@gmail.com',NULL),(20,'yolandra','algarrobos','algaroo123','1234567','2004-06-15','algaroo@gmail.com',NULL),(21,'yolagarobos','golan','golan123','1234567','2002-06-04','golan@gmail.com',NULL),(22,'pepito','ibanez','octavio98','1234','2018-06-11','example@exmpl.com',NULL),(24,'dsadsfdsf','balasdas','octi99255','octavio9','1996-06-11','exampldfsfsdfdsdsfe@exmpl.com',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-11  0:59:36
