/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `user` (
	`id` bigint (20),
	`idcard` varchar (765),
	`idnumber` varchar (765),
	`name` varchar (765),
	`password` varchar (765),
	`role` varchar (765),
	`sex` varchar (765)
); 

INSERT INTO `user` ( `idnumber`, `name`, `password`, `role`, `sex`) VALUES('admin','admin','ISMvKXpXpadDiUoOSoAfww==','admin','男');
INSERT INTO `user` (   `idnumber`, `name`, `password`, `role`, `sex`) VALUES('tea','tea','cjnqK13JQ/YfPAoCdsIJdA==','teacher','男');
INSERT INTO `user` (   `idnumber`, `name`, `password`, `role`, `sex`) VALUES('ad1','ad1','xR6dRKZZ7PlRLhooREFtmw==','admin','男');
INSERT INTO `user` ( `idnumber`, `name`, `password`, `role`, `sex`) VALUES('t3','t3','C4hUrTjwpsZYB5KNKBlWCQ==','teacher','男');
