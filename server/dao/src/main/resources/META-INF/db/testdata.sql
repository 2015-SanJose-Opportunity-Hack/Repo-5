create schema hackathon;
CREATE USER 'hackathon'@'localhost' IDENTIFIED BY 'hackathon';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON reward_store.* TO 'hackathon'@'localhost';
grant all on hackathon.* to 'hackathon'@'localhost';


INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `latitude`, `longitude`, `city`, `state`, `zipCode`) VALUES ('Food', 'Prison Bars', 'http://prison-bars.com/pre-order-bars/', 'https://zip.kiva.org/loans/16709', 'Seth', '37.802187', '-122.411361', 'San Francisco', 'CA', '94133');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `latitude`, `longitude`, `city`, `state`, `zipCode`) VALUES ('Flowers', 'Tony Rossi & Sons Florist', 'http://www.tonyrossiflorist.com/', 'https://zip.kiva.org/loans/10582', 'Carmen', '37.779214', '-122.224741', 'Oakland', 'CA', '94601');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `latitude`, `longitude`, `city`, `state`) VALUES ('Flowers', 'Chase Flowers & Gifts', 'http://chaseflowersandgifts.com/home.html', 'https://zip.kiva.org/loans/1182', 'Christine', '44.046044', '-122.993971', 'Springfield', 'OR');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `yelpURL`, `latitude`, `longitude`, `city`, `state`, `zipCode`) VALUES ('Food', 'Chile Lindo', 'http://www.chilelindo.com/', 'https://zip.kiva.org/loans/15747', 'Paula', 'http://www.yelp.com/biz/chile-lindo-empanadas-san-francisco?osq=chilendo', '37.765566', '-122.418468', 'San Francisco', 'CA', '94103');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `likes`, `city`, `state`) VALUES ('Flowers', 'Ana Mia', 'http://www.anamiaflowerstudio.com/shop/', 'https://zip.kiva.org/loans/15397', 'Ana', '0', 'San Francisco', 'CA');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `city`, `state`) VALUES ('Food', 'The Candy Drawer Confectionary, LLC', 'http://www.candydrawerconfectionary.com/ecommerce/', 'https://zip.kiva.org/loans/10935', 'Maura', 'Reston', 'VA');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `city`, `state`) VALUES ('Food', 'Bread SRSLY', 'http://www.breadsrsly.com/shop/', 'https://zip.kiva.org/loans/5141', 'Sadie', 'San Francisco', 'CA');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `city`, `state`) VALUES ('Home Decoration', 'Bottle Gardens', 'https://www.etsy.com/shop/BottleGardens', 'https://zip.kiva.org/loans/1828', 'Chase', 'San Francisco', 'CA');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `city`, `state`) VALUES ('Food', 'VegThisWay', 'https://www.vegthisway.com/shop/', 'https://zip.kiva.org/loans/16747', 'Kaitlin', 'San Francisco', 'CA');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `city`, `state`) VALUES ('Jewelry', 'Reif', 'http://www.reif-haus.com/collections/reifhaus-aw15', 'https://zip.kiva.org/loans/1405', 'Lindsey', 'Portland', 'OR');
INSERT INTO `hackathon`.`business` (`category`, `businessName`, `storeLink`, `ownerLink`, `ownerName`, `city`, `state`) VALUES ('Clothing', 'Mushmina', 'http://www.mushmina.com/', 'https://zip.kiva.org/loans/13503', 'Katie', 'Wayne', 'PA');


INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Chandra ', 'csivakolundu@paypalcorp.com', '2');
INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Chandra ', 'csivakolundu@paypalcorp.com', '4');
INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Chandra ', 'csivakolundu@paypalcorp.com', '1');
INSERT INTO `hackathon`.`user` (`name`, `email`, `business_lent_id`) VALUES ('Pooja', 'poshroff@paypal.com', '2');

