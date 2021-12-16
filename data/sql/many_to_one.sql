create table manufacturers (
	id serial primary key,
	name varchar(255)
);

create table products (
	id serial primary key,
	name varchar(255),
	manufacturer_id int references manufacturers(id)
);

insert into manufacturers(name) values ('volvo');
insert into manufacturers(name) values ('audi');
insert into products(name, manufacturer_id) values ('XC70', 1);
insert into products(name, manufacturer_id) values ('A6', 2);

select * from products;
select * from manufacturers where id in (select id from products);