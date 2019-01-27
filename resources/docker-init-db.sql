CREATE DATABASE IF NOT EXISTS invillia;
CREATE DATABASE IF NOT EXISTS invillia_test;

CREATE USER 'invillia'@'%' IDENTIFIED BY 'invilli@';
GRANT ALL ON *.* TO 'invillia'@'%';
