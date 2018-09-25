CREATE TABLE IF NOT EXISTS env (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (100),
  description varchar(100),
  create_by varchar(100),
  update_by varchar(100),
  create_time timestamp not null,
  update_time timestamp not null
);


CREATE TABLE user (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100),
  gender INTEGER,
  name VARCHAR(100),
  name_en VARCHAR(100),
  nickname VARCHAR(100),
  cellphone VARCHAR(11),
  email VARCHAR(100),
  province VARCHAR(10),
  city VARCHAR(32),
  deleted TINYINT NOT NULL DEFAULT 0,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);