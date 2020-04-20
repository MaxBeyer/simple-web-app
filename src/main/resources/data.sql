DROP TABLE IF EXISTS garage;

CREATE TABLE garage (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  fridgeName VARCHAR(250) NOT NULL
);

INSERT INTO garage (fridgeName) VALUES
  ('Beer Fridge'),
  ('Chest Freezer'),
  ('Mini Fridge');