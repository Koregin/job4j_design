create table bodies(
    id serial primary key,
    name varchar(255)
);

create table engines(
    id serial primary key,
    name varchar(255)
);

create table transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references bodies(id),
    engine_id int references engines(id),
    transmission_id int references transmissions(id)
);

insert into bodies(name) values ('седан');
insert into bodies(name) values ('хэтчбек');
insert into bodies(name) values ('минивен');
insert into engines(name) values ('бензиновый');
insert into engines(name) values ('дизельный');
insert into transmissions(name) values ('ручная');
insert into transmissions(name) values ('автомат');
insert into transmissions(name) values ('вариатор');
insert into cars(name, body_id, engine_id, transmission_id) values ('Mercedes Benz', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Volvo', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Ford', 2, 1, 1);

-- Вывести список всех машин и все привязанные к ним детали. --
select c.name, b.name, e.name, t.name
from cars c
left join bodies b on b.id = c.body_id
left join engines e on c.engine_id = e.id
left join transmissions t on c.transmission_id = t.id;

-- Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач.--
select b.name
from bodies b
full join cars c on b.id = c.body_id
where c.id is null;

select e.name
from engines e
full join cars c on e.id = c.engine_id
where c.id is null;

select t.name
from transmissions t
full join cars c on t.id = c.transmission_id
where c.id is null;