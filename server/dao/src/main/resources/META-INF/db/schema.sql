
CREATE TABLE `app_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) NOT NULL,
  `value` varchar(4096) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE business (
  id INT ,
  category varchar(20),
  latitude double,
  longitude double,
  addressId int,
  businessName varchar(20),
  businessDescription varchar(20),
  likes int,
  PRIMARY KEY (userId)
  );
  
  

  CREATE TABLE address (
  addressId int,
  street varchar(50),
  city varchar(20),
  state varchar (20),
  zipCode smallint,
  PRIMARY KEY (addressId)
  );  