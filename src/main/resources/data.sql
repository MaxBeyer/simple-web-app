DROP TABLE IF EXISTS garage;
DROP TABLE IF EXISTS food_item;

CREATE TABLE garage (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  fridge_name VARCHAR(250) NOT NULL
);

INSERT INTO garage (fridge_name) VALUES
  ('Beer Fridge'),
  ('Chest Freezer'),
  ('Mini Fridge');

create table food_item (
    id int auto_increment primary key,
    fridge_id int references garage(id),
    food_type varchar,
    quantity int not null
)