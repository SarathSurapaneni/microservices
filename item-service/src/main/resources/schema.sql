DROP TABLE IF EXISTS ITEM;
 
CREATE TABLE ITEM (
  id long(20) NOT NULL auto_increment,
  name varchar(400) NOT NULL,
  description varchar(2000) NOT NULL,
  price long(20) NOT NULL,
  PRIMARY KEY  (id)
);