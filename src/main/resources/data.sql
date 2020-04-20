drop table if exists food_item;
drop table if exists fridge;

create table fridge (
  id UUID  primary key not null,
  fridge_name varchar(250) not null
);

insert into fridge (id, fridge_name) values
  ('f7af576b-84e4-49b0-ab00-842d9db903d1', 'Beer Fridge'),
  ('92f19b62-946d-4ed6-a260-fe78ef854bcc', 'Chest Freezer'),
  ('d685f5ca-875a-4d06-8262-81d1b0a04fea', 'Mini Fridge');

create table food_item (
    id UUID primary key not null,
    fridge_id UUID references fridge(id),
    food_type varchar not null
)