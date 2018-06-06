/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.0.27-community-nt : Database - rice_inventory
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rice_inventory` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `rice_inventory`;

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `bank_id` int(11) NOT NULL auto_increment,
  `bank_name` varchar(255) default NULL,
  `branch_name` varchar(255) default NULL,
  `acc_title` varchar(255) default NULL,
  `acc_no` varchar(255) default NULL,
  `balance` decimal(20,6) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`bank_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `bank_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `bank_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

/*Table structure for table `broker` */

DROP TABLE IF EXISTS `broker`;

CREATE TABLE `broker` (
  `broker_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `contact` varchar(255) default NULL,
  `broker_type` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`broker_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `broker_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `broker_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `broker` */

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `company_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `contact_person` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`company_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `company_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `company_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `company` */

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `emp_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `contact` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `role_id` int(11) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`emp_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `employees` */

insert  into `employees`(`emp_id`,`name`,`contact`,`address`,`username`,`password`,`role_id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`active`) values (1,'admin','03341994199','Sanghar','admin','admin',1,NULL,'2018-03-31 00:48:52',NULL,'0000-00-00 00:00:00',1),(2,'Kashif','03341994199','Karachi','khoso12','12345',1,1,'2018-03-31 19:06:34',1,'2018-04-01 02:16:27',1),(3,'Rehan','03341994199','Sanghar','admin2','123456789',1,1,'2018-03-31 19:09:13',1,'2018-04-01 02:17:15',1),(4,'Mubeen','03341994199','Sanghar','mubeen123','123',1,1,'2018-03-31 19:55:35',1,'2018-03-31 19:55:35',1);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`product_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`created_by`,`created_date`,`modified_by`,`modified_date`,`active`) values (1,'rice-5kg',1,'2018-04-02 16:28:07',NULL,NULL,1),(2,'rice-10kg',1,'2018-04-02 16:28:16',NULL,NULL,1),(3,'daal-mash-5kg',1,'2018-04-02 16:28:43',NULL,NULL,1),(4,'daal-moong-5kg',1,'2018-04-02 16:29:15',1,'2018-04-02 16:30:32',1),(5,'daal-chana-5kg',1,'2018-04-02 16:30:05',NULL,NULL,1),(6,'daal-mash-10kg',1,'2018-04-02 16:30:46',NULL,NULL,1);

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL auto_increment,
  `date` date default NULL,
  `builty_no` varchar(255) default NULL,
  `total_amount` int(20) default NULL,
  `payment_condition_date` date default NULL,
  `note` varchar(255) default NULL,
  `company_id` int(11) default NULL,
  `broker_id` int(11) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`purchase_id`),
  KEY `purchase_fk0` (`company_id`),
  KEY `purchase_fk1` (`broker_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `purchase_fk0` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  CONSTRAINT `purchase_fk1` FOREIGN KEY (`broker_id`) REFERENCES `broker` (`broker_id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase` */

/*Table structure for table `purchase_credit_debit` */

DROP TABLE IF EXISTS `purchase_credit_debit`;

CREATE TABLE `purchase_credit_debit` (
  `purchase_credit_debit_id` int(11) NOT NULL auto_increment,
  `purchase_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `amount_paid` decimal(20,6) default NULL,
  `date` date default NULL,
  `comments` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`purchase_credit_debit_id`),
  KEY `purchase_credit_debit_fk0` (`purchase_id`),
  KEY `purchase_credit_debit_fk1` (`bank_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `purchase_credit_debit_fk0` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`purchase_id`),
  CONSTRAINT `purchase_credit_debit_fk1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`),
  CONSTRAINT `purchase_credit_debit_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `purchase_credit_debit_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase_credit_debit` */

/*Table structure for table `purchase_detail` */

DROP TABLE IF EXISTS `purchase_detail`;

CREATE TABLE `purchase_detail` (
  `purchase_detail_id` int(11) NOT NULL auto_increment,
  `purchase_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `quantity` decimal(20,6) default NULL,
  `price` decimal(20,6) default NULL,
  `payment_cond_date` timestamp NULL default NULL,
  `payment_cond_note` varchar(255) default NULL,
  `comments` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`purchase_detail_id`),
  KEY `purchase_detail_fk0` (`purchase_id`),
  KEY `purchase_detail_fk1` (`product_id`),
  KEY `purchase_detail_fk2` (`store_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `purchase_detail_fk0` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`purchase_id`),
  CONSTRAINT `purchase_detail_fk1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `purchase_detail_fk2` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `purchase_detail_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `purchase_detail_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase_detail` */

/*Table structure for table `purchase_sell_expense` */

DROP TABLE IF EXISTS `purchase_sell_expense`;

CREATE TABLE `purchase_sell_expense` (
  `expense_id` int(11) NOT NULL auto_increment,
  `purchase_id` int(11) NOT NULL,
  `sell_id` int(11) default NULL,
  `transport_expense` decimal(20,6) default NULL,
  `labour_expense` decimal(20,6) default NULL,
  `other` decimal(20,6) default NULL,
  `broker_comission` decimal(20,6) default NULL,
  `broker_paid_amount` decimal(20,6) default NULL,
  `comments` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`expense_id`),
  KEY `purchase_expense_fk0` (`purchase_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  KEY `sell_id` (`sell_id`),
  CONSTRAINT `purchase_expense_fk0` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`purchase_id`),
  CONSTRAINT `purchase_sell_expense_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `purchase_sell_expense_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `purchase_sell_expense_ibfk_3` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`sell_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase_sell_expense` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL auto_increment,
  `role` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NOT NULL default '0000-00-00 00:00:00',
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`role_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `role_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `role_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`role_id`,`role`,`created_by`,`created_date`,`modified_by`,`modified_date`,`active`) values (1,'admin',1,'2018-04-02 01:45:42',1,'0000-00-00 00:00:00',1),(2,'system administrator',1,'2018-04-02 00:42:54',1,'2018-04-01 23:53:57',1);

/*Table structure for table `sell` */

DROP TABLE IF EXISTS `sell`;

CREATE TABLE `sell` (
  `sell_id` int(11) NOT NULL auto_increment,
  `broker_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `builty_no` varchar(255) default NULL,
  `total_amount` decimal(20,6) default NULL,
  `date` date default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`sell_id`),
  KEY `sell_fk0` (`broker_id`),
  KEY `sell_fk1` (`company_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `sell_fk0` FOREIGN KEY (`broker_id`) REFERENCES `broker` (`broker_id`),
  CONSTRAINT `sell_fk1` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  CONSTRAINT `sell_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `sell_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sell` */

/*Table structure for table `sell_credit_debit` */

DROP TABLE IF EXISTS `sell_credit_debit`;

CREATE TABLE `sell_credit_debit` (
  `sell_credit_debit_id` int(11) NOT NULL auto_increment,
  `bank_id` int(11) NOT NULL,
  `sell_id` int(11) NOT NULL,
  `amount_paid` decimal(20,6) default NULL,
  `date` date default NULL,
  `comments` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`sell_credit_debit_id`),
  KEY `sell_credit_debit_fk0` (`bank_id`),
  KEY `sell_credit_debit_fk1` (`sell_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `sell_credit_debit_fk0` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`),
  CONSTRAINT `sell_credit_debit_fk1` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`sell_id`),
  CONSTRAINT `sell_credit_debit_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `sell_credit_debit_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sell_credit_debit` */

/*Table structure for table `sell_detail` */

DROP TABLE IF EXISTS `sell_detail`;

CREATE TABLE `sell_detail` (
  `sell_detail_id` int(11) NOT NULL auto_increment,
  `sell_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) default NULL,
  `price` decimal(20,6) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  `store_id` int(11) default NULL,
  PRIMARY KEY  (`sell_detail_id`),
  KEY `sell_detail_fk0` (`sell_id`),
  KEY `sell_detail_fk1` (`product_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `sell_detail_ibfk_3` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `sell_detail_fk0` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`sell_id`),
  CONSTRAINT `sell_detail_fk1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `sell_detail_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `sell_detail_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sell_detail` */

/*Table structure for table `shop_store_detail` */

DROP TABLE IF EXISTS `shop_store_detail`;

CREATE TABLE `shop_store_detail` (
  `shop_store_detail_id` int(11) NOT NULL auto_increment,
  `date` date NOT NULL,
  `quantity` int(11) NOT NULL,
  `comments` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `active` tinyint(1) default '1',
  `product_id` int(11) default NULL,
  `store_id` int(11) default NULL,
  PRIMARY KEY  (`shop_store_detail_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  KEY `store_id` (`store_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `shop_store_detail_ibfk_4` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `shop_store_detail_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `shop_store_detail_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `shop_store_detail_ibfk_3` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `shop_store_detail` */

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `store_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`store_id`),
  KEY `modified_by` (`modified_by`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `store_ibfk_1` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `store_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `store` */

insert  into `store`(`store_id`,`name`,`address`,`created_by`,`created_date`,`modified_by`,`modified_date`,`active`) values (1,'My Shop','market',1,'2018-04-04 21:06:03',1,'2018-04-04 21:06:03',1);

/*Table structure for table `store_detail` */

DROP TABLE IF EXISTS `store_detail`;

CREATE TABLE `store_detail` (
  `store_detail_id` int(11) NOT NULL auto_increment,
  `product_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `avail_qty` int(11) default NULL,
  `created_by` int(11) default NULL,
  `created_date` timestamp NULL default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` timestamp NULL default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`store_detail_id`),
  KEY `store_detail_fk0` (`product_id`),
  KEY `store_detail_fk1` (`store_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `store_detail_fk0` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `store_detail_fk1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `store_detail_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `employees` (`emp_id`),
  CONSTRAINT `store_detail_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `employees` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `store_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
