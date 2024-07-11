-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 192.168.240.50    Database: registeringSystem
-- ------------------------------------------------------
-- Server version	5.5.5-10.11.6-MariaDB-0+deb12u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Surname` varchar(50) NOT NULL,
  `PersonID` varchar(50) NOT NULL,
  `Uuid` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`,`PersonID`,`Uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Jan','Novotny','jXa4g3H7oPq2','6eba059f-0190-1000-af45-969cd8dd8cf8'),(2,'Pavla','Novotna','yB9fR6tK0wLm','6eba05c6-0190-1000-8a6c-1b64a66c23cc'),(3,'Matyas','Novotny','cN1vZ8pE5sYx','6eba05da-0190-1000-882a-bdb5c6ad7725'),(4,'Adam','Novotny','tQdG2kP3mJfB','6eba05fa-0190-1000-8636-559d2ff98a0a'),(5,'Dalibor','Janda','iM5sO6zXcW7v','6eba0616-0190-1000-aa23-27cc32aa8297'),(6,'Franz','Kafka','rU8nA9eT2bYh','6eba0632-0190-1000-a48f-eb5256d6c88e'),(7,'James','Hetfield','wV6eH1fK7qZj','6eba0653-0190-1000-9287-b65a01835c3f'),(8,'Kirk','Hammet','sL4gN9dC3bXz','6eba0682-0190-1000-8b21-fa89aedaecc6'),(9,'Petr','Janda','kR0aZ7vW2nDl','6eba06a6-0190-1000-b4d5-36392d74ded4'),(10,'Ivan','Kral','eI1oY6tQ9dKj','6eba06d4-0190-1000-912e-af31e6819b0a'),(11,'Lars','Ulrich','gT4cR7wS0lVx','6eba070f-0190-1000-a41a-c275fb5de5e6'),(12,'Robert','Truchilo','xF9hD2yJ3sWv','6eba074c-0190-1000-9f44-7659403c4d0c'),(13,'Ivan','Hlas','hM5bZ8nK4aVf','6eba077e-0190-1000-9510-546d848c24ed'),(14,'David','Matasek','qE3lY6uT0vKd','6eba07a1-0190-1000-b646-2afdc3719f0c'),(15,'Petr','Muk','bG2zC7jR9xVp','6eba07df-0190-1000-bcf9-b89dfbdd3f3f'),(16,'Oskar','Petr','vB1fX4rH7iNt','6eba0819-0190-1000-b110-5495b279ff68'),(17,'David','Koller','aO8kP3mZ6dIw','6eba0849-0190-1000-933c-c1cad82199d6'),(18,'Robert','Kodym','dW9pL2eU1yNc','6eba0878-0190-1000-9e81-55031d99d3c6'),(32,'Jakub','Zlamal','nS7tJ0qR5wGh','9ed15906-0190-1000-917e-ec8e779ac7ae'),(33,'Matyas','Korvin','mY6sT1jA3cLz','9ed2bae4-0190-1000-b943-574a8cb5acdb');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'registeringSystem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-11 18:26:58
