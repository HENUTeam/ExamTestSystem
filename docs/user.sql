/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `user` (
	`id` bigint (20),
	`idcard` varchar (765),
	`createdate` datetime ,
	`idnumber` varchar (765),
	`name` varchar (765),
	`password` varchar (765),
	`role` varchar (765),
	`sex` varchar (765)
); 
insert into `user` (`id`, `idcard`, `createdate`, `idnumber`, `name`, `password`, `role`, `sex`) values('6','111111111111111','2017-11-30 21:24:42','admin','admin','ISMvKXpXpadDiUoOSoAfww==','admin','男');
insert into `user` (`id`, `idcard`, `createdate`, `idnumber`, `name`, `password`, `role`, `sex`) values('8',NULL,NULL,'tea','tea','cjnqK13JQ/YfPAoCdsIJdA==','teacher','男');
insert into `user` (`id`, `idcard`, `createdate`, `idnumber`, `name`, `password`, `role`, `sex`) values('11',NULL,'2017-12-01 15:48:30','ad1','ad1','xR6dRKZZ7PlRLhooREFtmw==','admin','男');
insert into `user` (`id`, `idcard`, `createdate`, `idnumber`, `name`, `password`, `role`, `sex`) values('12',NULL,NULL,'t3','t3','C4hUrTjwpsZYB5KNKBlWCQ==','teacher','男');
