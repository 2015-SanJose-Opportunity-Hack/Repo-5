create schema hackathon;
CREATE USER 'hackathon'@'localhost' IDENTIFIED BY 'hackathon';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON reward_store.* TO 'hackathon'@'localhost';
grant all on hackathon.* to 'hackathon'@'localhost';