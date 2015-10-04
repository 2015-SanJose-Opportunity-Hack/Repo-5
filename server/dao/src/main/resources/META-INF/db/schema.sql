CREATE DATABASE  IF NOT EXISTS `hackathon` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hackathon`;

DROP TABLE IF EXISTS `business`;

CREATE TABLE business (
  id int(11) NOT NULL AUTO_INCREMENT,
  category varchar(20),
  businessName varchar(255),
  storeLink varchar(255),
  ownerLink varchar(255),
  ownerName varchar(20),
  likes int default 0,
  yelpURL varchar(255),
  latitude double,
  longitude double,
  city varchar(20),
  state varchar (20),
  zipCode int,
  PRIMARY KEY (id)
  );
  
  CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45),
  `email` varchar(255),
  `business_lent_id` int(11),
  PRIMARY KEY (`id`)
);

  