create schema hackathon;
CREATE USER 'hackathon'@'localhost' IDENTIFIED BY 'hackathon';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON reward_store.* TO 'hackathon'@'localhost';
grant all on hackathon.* to 'hackathon'@'localhost';


INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `latitude`, `longitude`, `city`, `state`, `zipCode`) VALUES ('Food', 'Prison Bars', 'http://prison-bars.com/pre-order-bars/', 'https://zip.kiva.org/loans/16709', 'Seth', '37.802187', '-122.411361', 'San Francisco', 'CA', '94133');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `latitude`, `longitude`, `city`, `state`, `zipCode`) VALUES ('Flowers', 'Tony Rossi & Sons Florist', 'http://www.tonyrossiflorist.com/', 'https://zip.kiva.org/loans/10582', 'Carmen', '37.779214', '-122.224741', 'Oakland', 'CA', '94601');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `latitude`, `longitude`, `city`, `state`) VALUES ('Flowers', 'Chase Flowers & Gifts', 'http://chaseflowersandgifts.com/home.html', 'https://zip.kiva.org/loans/1182', 'Christine', '44.046044', '-122.993971', 'Springfield', 'OR');


INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Chandra ', 'csivakolundu@paypalcorp.com', '2');
INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Chandra ', 'csivakolundu@paypalcorp.com', '4');
INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Chandra ', 'csivakolundu@paypalcorp.com', '1');
INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Pooja', 'poshroff@paypal.com', '2');
