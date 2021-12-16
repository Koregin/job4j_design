create table accounts(
	id serial primary key,
	name varchar(255),
	address varchar(255),
	phone varchar(255),
	balance real,
	isOpen boolean
);

insert into accounts(name, address, phone, balance, isOpen) 
values ('Иванов Иван Иванович', 'Москва, ул. Ленина, д.125, кв. 5', '8(123)456-78-99', 1500, true); 

update accounts set balance = 0, isOpen = false;

delete from accounts;