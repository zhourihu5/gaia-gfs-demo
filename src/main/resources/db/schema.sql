CREATE TABLE IF NOT EXISTS env (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (100),
  description varchar(100),
  create_by varchar(100),
  update_by varchar(100)
);
