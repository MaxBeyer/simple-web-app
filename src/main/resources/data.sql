drop table if exists food_item;

create table food_item (
    id UUID primary key not null,
    fridge_name varchar not null,
    food_type varchar not null
)

--insert into food_item (id, fridge_Name, food_type) values
--  ('fc29daa0-0b16-4155-a6aa-b3cc4803aef5', 'Fridge', 'FOOD'),
--  ('f6fb42cf-0db7-4d11-8153-92f4df2c1b28', 'Fridge', 'BEVERAGE'),
--  ('e217216c-9b89-4da2-b3d6-dc0e3ee7217f', 'Beer Fridge', 'FOOD');