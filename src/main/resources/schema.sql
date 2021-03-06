DROP TABLE POST IF EXISTS;
CREATE TABLE POST (
  ID BIGINT NOT NULL auto_increment,
  TITLE VARCHAR(100) NOT NULL,
  CONTENT TEXT NOT NULL,
  AUTHOR CHAR(30) NOT NULL,
  CREATED_AT TIMESTAMP NOT NULL,
  UPDATED_AT TIMESTAMP NOT NULL,
  HIT BIGINT NOT NULL,
  PRIMARY KEY (ID)
);
