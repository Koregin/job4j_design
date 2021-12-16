create table parts(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255)
);

create table parts_cars(
	id serial primary key,
	part_id int references parts(id),
	car_id int references cars(id)
);

insert into parts(name) values ('engine');
insert into parts(name) values ('transmission');
insert into parts(name) values ('cruise control');

insert into cars(name) values ('Audi A6');
insert into cars(name) values ('BMW X5');
insert into cars(name) values ('Reno Logan');

insert into parts_cars(part_id, car_id) values (1, 1);
insert into parts_cars(part_id, car_id) values (2, 1);
insert into parts_cars(part_id, car_id) values (3, 1);
insert into parts_cars(part_id, car_id) values (1, 2);
insert into parts_cars(part_id, car_id) values (2, 2);
insert into parts_cars(part_id, car_id) values (3, 2);
insert into parts_cars(part_id, car_id) values (1, 3);
insert into parts_cars(part_id, car_id) values (2, 3);