drop table if exists garage;
drop table if exists food_item;

create table garage (
  id int auto_increment  primary key,
  fridge_name varchar(250) not null
);

insert into garage (fridge_name) values
  ('Beer Fridge'),
  ('Chest Freezer'),
  ('Mini Fridge');

create table food_item (
    id int auto_increment primary key,
    fridge_id int references garage(id),
    food_type varchar
)