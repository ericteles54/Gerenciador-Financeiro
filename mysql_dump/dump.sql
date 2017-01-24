-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: gerfinan
-- ------------------------------------------------------
-- Server version	5.5.54-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `saldo` decimal(19,2) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq872bs93677sbx864oayoe3jv` (`username`),
  CONSTRAINT `FKq872bs93677sbx864oayoe3jv` FOREIGN KEY (`username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (5,'Santander',0.00,'joao'),(12,'Banco do Brasil',1600.00,'ericteles54'),(13,'Santander',208.02,'ericteles54'),(14,'Bradesco',630.00,'ericteles54');
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacao`
--

DROP TABLE IF EXISTS `transacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `consolidada` bit(1) NOT NULL,
  `data` datetime NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `tipo_transacao` int(11) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `conta_id` bigint(20) NOT NULL,
  `cor_transacao` varchar(255) NOT NULL,
  `img_transacao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6968iodq71yxdsg3ctxmnffv1` (`conta_id`),
  CONSTRAINT `FK6968iodq71yxdsg3ctxmnffv1` FOREIGN KEY (`conta_id`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacao`
--

LOCK TABLES `transacao` WRITE;
/*!40000 ALTER TABLE `transacao` DISABLE KEYS */;
INSERT INTO `transacao` VALUES (80,'','2017-01-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(81,'\0','2017-02-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(82,'\0','2017-03-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(83,'\0','2017-04-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(84,'\0','2017-05-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(85,'\0','2017-06-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(86,'\0','2017-07-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(87,'\0','2017-08-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(88,'\0','2017-09-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(89,'\0','2017-10-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(90,'\0','2017-11-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(91,'\0','2017-12-10 00:00:00','Salario',1,4101.00,13,'#006600','images/receita.png'),(92,'','2017-01-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(93,'\0','2017-02-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(94,'\0','2017-03-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(95,'\0','2017-04-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(96,'\0','2017-05-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(97,'\0','2017-06-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(98,'\0','2017-07-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(99,'\0','2017-08-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(100,'\0','2017-09-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(101,'\0','2017-10-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(102,'\0','2017-11-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(103,'\0','2017-12-06 00:00:00','Gás SEG',0,30.25,13,'#660000','images/despesa.png'),(104,'','2017-01-06 00:00:00','Remedio - Boca',0,38.88,13,'#660000','images/despesa.png'),(105,'','2017-01-07 00:00:00','Muletas e outros',0,216.00,13,'#660000','images/despesa.png'),(106,'','2017-01-09 00:00:00','Saque carteira',0,50.00,13,'#660000','images/despesa.png'),(107,'','2017-01-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(108,'\0','2017-02-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(109,'\0','2017-03-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(110,'\0','2017-04-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(111,'\0','2017-05-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(112,'\0','2017-06-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(113,'\0','2017-07-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(114,'\0','2017-08-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(115,'\0','2017-09-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(116,'\0','2017-10-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(117,'\0','2017-11-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(118,'\0','2017-12-10 00:00:00','Financiamento Casa CAIXA',0,1000.00,13,'#660000','images/despesa.png'),(119,'','2017-01-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(120,'\0','2017-02-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(121,'\0','2017-03-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(122,'\0','2017-04-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(123,'\0','2017-05-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(124,'\0','2017-06-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(125,'\0','2017-07-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(126,'\0','2017-08-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(127,'\0','2017-09-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(128,'\0','2017-10-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(129,'\0','2017-11-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(130,'\0','2017-12-10 00:00:00','CATV - Friburgo',0,66.48,13,'#660000','images/despesa.png'),(131,'','2017-01-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(132,'\0','2017-02-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(133,'\0','2017-03-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(134,'\0','2017-04-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(135,'\0','2017-05-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(136,'\0','2017-06-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(137,'\0','2017-07-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(138,'\0','2017-08-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(139,'\0','2017-09-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(140,'\0','2017-10-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(141,'\0','2017-11-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(142,'\0','2017-12-10 00:00:00','Condominio - Friburgo',0,190.00,13,'#660000','images/despesa.png'),(143,'','2017-01-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(144,'\0','2017-02-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(145,'\0','2017-03-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(146,'\0','2017-04-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(147,'\0','2017-05-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(148,'\0','2017-06-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(149,'\0','2017-07-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(150,'\0','2017-08-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(151,'\0','2017-09-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(152,'\0','2017-10-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(153,'\0','2017-11-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(154,'\0','2017-12-10 00:00:00','Conta Celular',0,100.00,13,'#660000','images/despesa.png'),(155,'','2017-01-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(156,'\0','2017-02-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(157,'\0','2017-03-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(158,'\0','2017-04-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(159,'\0','2017-05-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(160,'\0','2017-06-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(161,'\0','2017-07-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(162,'\0','2017-08-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(163,'\0','2017-09-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(164,'\0','2017-10-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(165,'\0','2017-11-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(166,'\0','2017-12-10 00:00:00','Previdencia - Seguro vida',0,630.00,13,'#660000','images/despesa.png'),(167,'','2017-01-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(168,'\0','2017-02-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(169,'\0','2017-03-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(170,'\0','2017-04-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(171,'\0','2017-05-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(172,'\0','2017-06-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(173,'\0','2017-07-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(174,'\0','2017-08-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(175,'\0','2017-09-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(176,'\0','2017-10-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(177,'\0','2017-11-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(178,'\0','2017-12-10 00:00:00','Previdencia - Seguro vida',1,630.00,14,'#006600','images/receita.png'),(179,'','2017-01-10 00:00:00','Gasolina',0,101.72,13,'#660000','images/despesa.png'),(180,'','2017-01-12 00:00:00','Aplicação BB',0,1000.00,13,'#660000','images/despesa.png'),(181,'','2017-01-12 00:00:00','Aplicação BB',1,1000.00,12,'#006600','images/receita.png'),(182,'','2017-01-12 00:00:00','Farmacia - desodorante',0,23.98,13,'#660000','images/despesa.png'),(183,'','2017-01-13 00:00:00','Saque carteira',0,50.00,13,'#660000','images/despesa.png'),(184,'','2017-01-16 00:00:00','Presente Valentina',0,99.90,13,'#660000','images/despesa.png'),(185,'','2017-01-16 00:00:00','Aplicação BB',0,600.00,13,'#660000','images/despesa.png'),(186,'','2017-01-16 00:00:00','Aplicação BB',1,600.00,12,'#006600','images/receita.png'),(187,'','2017-01-17 00:00:00','Saque carteira',0,40.00,13,'#660000','images/despesa.png'),(188,'','2017-01-18 00:00:00','Tarifa DOC Santander',0,9.20,13,'#660000','images/despesa.png'),(189,'','2017-01-18 00:00:00','Almoço',0,42.46,13,'#660000','images/despesa.png'),(190,'\0','2017-01-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(191,'\0','2017-02-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(192,'\0','2017-03-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(193,'\0','2017-04-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(194,'\0','2017-05-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(195,'\0','2017-06-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(196,'\0','2017-07-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(197,'\0','2017-08-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(198,'\0','2017-09-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(199,'\0','2017-10-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(200,'\0','2017-11-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(201,'\0','2017-12-26 00:00:00','Previdencia - Seguro vida',0,630.00,14,'#660000','images/despesa.png'),(202,'\0','2017-01-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(203,'\0','2017-02-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(204,'\0','2017-03-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(205,'\0','2017-04-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(206,'\0','2017-05-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(207,'\0','2017-06-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(208,'\0','2017-07-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(209,'\0','2017-08-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(210,'\0','2017-09-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(211,'\0','2017-10-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(212,'\0','2017-11-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(213,'\0','2017-12-26 00:00:00','Muro',0,150.00,13,'#660000','images/despesa.png'),(214,'','2017-01-01 00:00:00','Saldo Inicial',1,395.89,13,'#006600','images/receita.png');
/*!40000 ALTER TABLE `transacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `username` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('eric','','123'),('ericteles54','','123'),('joao','','123');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_roles`
--

DROP TABLE IF EXISTS `usuario_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcquqvtos8vtpas48hq29regjo` (`username`),
  CONSTRAINT `FKcquqvtos8vtpas48hq29regjo` FOREIGN KEY (`username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_roles`
--

LOCK TABLES `usuario_roles` WRITE;
/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
INSERT INTO `usuario_roles` VALUES (1,'ROLE_USER','eric'),(2,'ROLE_USER','joao'),(3,'ROLE_USER','ericteles54');
/*!40000 ALTER TABLE `usuario_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-23 12:41:15
