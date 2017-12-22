/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16-log : Database - db_examsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_examsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_examsystem`;

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auto_start` bit(1) NOT NULL,
  `create_user` varchar(255) NOT NULL,
  `exam_state` varchar(255) NOT NULL,
  `has_clean` bit(1) NOT NULL,
  `has_paper` bit(1) NOT NULL,
  `has_store` bit(1) NOT NULL,
  `is_download` bit(1) NOT NULL,
  `paper_path` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `subject` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `exam` */

insert  into `exam`(`id`,`auto_start`,`create_user`,`exam_state`,`has_clean`,`has_paper`,`has_store`,`is_download`,`paper_path`,`path`,`start_date`,`subject`) values (3,'\0','tea','end','','','','\0','./exams/Java考试/','./exams/Java考试/答案/','2017-12-28 03:50:00','Java考试'),(4,'','tea','end','','','','\0','./exams/已完成的Java考试/','./exams/已完成的Java考试/答案/','2017-12-19 08:03:00','已完成的Java考试');

/*Table structure for table `exam_user` */

DROP TABLE IF EXISTS `exam_user`;

CREATE TABLE `exam_user` (
  `exam_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_iq7vei1cu397v0goqjuk3g72e` (`user_id`),
  KEY `FKpvet665enmcbtnoecju2epqmp` (`exam_id`),
  CONSTRAINT `FKjod0xt6ip59g0fw3pir8linne` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpvet665enmcbtnoecju2epqmp` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exam_user` */

insert  into `exam_user`(`exam_id`,`user_id`) values (3,5),(3,6),(3,7),(3,8),(3,9),(3,10);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` datetime DEFAULT NULL,
  `ismager` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `teaid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idnumber` varchar(255) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tgkwrgg3qouq6087dy4schx61` (`idnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`idnumber`,`ip`,`name`,`password`,`role`,`sex`) values (1,'admin',NULL,'admin','ISMvKXpXpadDiUoOSoAfww==','admin','男'),(2,'tea',NULL,'tea','cjnqK13JQ/YfPAoCdsIJdA==','teacher','男'),(3,'ad1',NULL,'ad1','xR6dRKZZ7PlRLhooREFtmw==','admin','男'),(4,'t3',NULL,'t3','C4hUrTjwpsZYB5KNKBlWCQ==','teacher','男'),(5,'1510121030',NULL,'Jack','5RXfDSAq5S/OuxQpV0MGOw==','student','男'),(6,'1234567890',NULL,'Kile','za6xKC1hR3K+sedMGSvr2g==','student','男'),(7,'1510121032',NULL,'Hello','mV4f2korX1XvDfUIaL8qjw==','student','男'),(8,'1510121036',NULL,'IIII','g/paQyrlXCU9DmDb+nFnIw==','student','男'),(9,'1510121039',NULL,'KJJJ','J+0PuVC4VrBuEnOYlCLn0w==','student','男'),(10,'1510121589',NULL,'HHH','3KVnL/NETH6ZeqmixOsglA==','student','男');

/*Table structure for table `user_exam` */

DROP TABLE IF EXISTS `user_exam`;

CREATE TABLE `user_exam` (
  `user_id` bigint(20) NOT NULL,
  `exam_id` bigint(20) NOT NULL,
  KEY `FKl44lsl55re2s7jhjlh527y85l` (`exam_id`),
  KEY `FK30xxyjqs5y3dnq0qtb2o159ds` (`user_id`),
  CONSTRAINT `FK30xxyjqs5y3dnq0qtb2o159ds` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKl44lsl55re2s7jhjlh527y85l` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_exam` */

insert  into `user_exam`(`user_id`,`exam_id`) values (5,3),(7,3),(8,3),(9,3),(10,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
