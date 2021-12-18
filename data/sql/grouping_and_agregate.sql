create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iPhone 11', 50000);
insert into devices(name, price) values ('XBOX Series S', 30000);
insert into devices(name, price) values ('PS 5', 59990);
insert into devices(name, price) values ('TV SONY 48"', 120000);
insert into devices(name, price) values ('TV LG 32', 25000);
insert into devices(name, price) values ('Air Pods', 17000);

insert into people(name) values ('Vasya');
insert into people(name) values ('Petya');
insert into people(name) values ('Masha');

insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (5, 1);
insert into devices_people(device_id, people_id) values (6, 3);

select avg(price)
from devices;

select p.name, avg(d.price)
from people as p
join devices_people dp on p.id = dp.people_id
join devices d on d.id = dp.device_id
group by p.name;

select p.name, avg(d.price)
from people as p
join devices_people dp on p.id = dp.people_id
join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 5000;
