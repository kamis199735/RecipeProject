CREATE DATABASE kamis199735_dev;
CREATE DATABASE kamis199735_prod;


CREATE USER 'kamis199735_dev_user'@'localhost' IDENTIFIED BY 'kamis199735';
CREATE USER 'kamis199735_prod_user'@'localhost' IDENTIFIED BY 'kamis199735';
CREATE USER 'kamis199735_dev_user'@'%' IDENTIFIED BY 'kamis199735';
CREATE USER 'kamis199735_prod_user'@'%' IDENTIFIED BY 'kamis199735';


GRANT SELECT ON kamis199735_dev.* to 'kamis199735_dev_user'@'localhost';
GRANT INSERT ON kamis199735_dev.* to 'kamis199735_dev_user'@'localhost';
GRANT UPDATE ON kamis199735_dev.* to 'kamis199735_dev_user'@'localhost';
GRANT DELETE ON kamis199735_dev.* to 'kamis199735_dev_user'@'localhost';
GRANT SELECT ON kamis199735_prod.* to 'kamis199735_prod_user'@'localhost';
GRANT INSERT ON kamis199735_prod.* to 'kamis199735_prod_user'@'localhost';
GRANT UPDATE ON kamis199735_prod.* to 'kamis199735_prod_user'@'localhost';
GRANT DELETE ON kamis199735_prod.* to 'kamis199735_prod_user'@'localhost';
GRANT SELECT ON kamis199735_dev.* to 'kamis199735_dev_user'@'%';
GRANT INSERT ON kamis199735_dev.* to 'kamis199735_dev_user'@'%';
GRANT UPDATE ON kamis199735_dev.* to 'kamis199735_dev_user'@'%';
GRANT DELETE ON kamis199735_dev.* to 'kamis199735_dev_user'@'%';
GRANT SELECT ON kamis199735_prod.* to 'kamis199735_prod_user'@'%';
GRANT INSERT ON kamis199735_prod.* to 'kamis199735_prod_user'@'%';
GRANT UPDATE ON kamis199735_prod.* to 'kamis199735_prod_user'@'%';
GRANT DELETE ON kamis199735_prod.* to 'kamis199735_prod_user'@'%';