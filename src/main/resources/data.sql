drop table if exists food_item;

create table food_item (
    id UUID primary key not null,
    fridge_name varchar not null,
    food_type varchar not null
);

insert into food_item (id, fridge_Name, food_type) values
  ('fc29daa0-0b16-4155-a6aa-b3cc4803aef5', 'Fridge', 'FOOD'),
  ('f6fb42cf-0db7-4d11-8153-92f4df2c1b28', 'Fridge', 'BEVERAGE'),
  ('e217216c-9b89-4da2-b3d6-dc0e3ee7217f', 'Beer Fridge', 'FOOD'),
('5f510e56-b8d8-4dbf-a79d-be5a640e2554', 'Beer Fridge', 'SODA_CAN'),
('aff945ae-c823-4251-8ebc-f47e3147f040', 'Beer Fridge', 'SODA_CAN'),
('66aeb69a-040d-4508-9f02-c8efa14afb28', 'Beer Fridge', 'SODA_CAN'),
('b2a4dbbc-2097-4187-9952-7b98fa17d6ad', 'Beer Fridge', 'SODA_CAN'),
('cdd981eb-53ce-4f9b-a96b-b79151980763', 'Beer Fridge', 'SODA_CAN'),
('1d8d33f2-030c-4f3d-b40e-874e00e23203', 'Beer Fridge', 'SODA_CAN'),
('6f43c803-06c5-4c22-b440-ff3d3990b546', 'Beer Fridge', 'SODA_CAN'),
('96ef1820-3ca9-4d7c-8fce-875cc1443d6b', 'Beer Fridge', 'SODA_CAN'),
('1b14d279-550f-4892-a71d-a960b990a3d2', 'Beer Fridge', 'SODA_CAN'),
('34df1302-7c5f-4d7e-a1b5-255bccd44b27', 'Beer Fridge', 'SODA_CAN'),
('b38461e3-336a-4937-b825-daaaf174231b', 'Beer Fridge', 'SODA_CAN'),
('cdd0e2f9-94ac-480a-8fe2-c062b80e5d2f', 'Beer Fridge', 'SODA_CAN');

