create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('ХЛЕБ');
insert into type(name) values ('МЯСО');
insert into type(name) values ('РЫБА');

insert into product(name, type_id, expired_date, price) values ('Сыр голландский', 1, '2021-11-21', 800);
insert into product(name, type_id, expired_date, price) values ('Молоко Ополье', 2, '2021-12-21', 70);
insert into product(name, type_id, expired_date, price) values ('мороженое Лакомка', 2, '2021-12-31', 80);
insert into product(name, type_id, expired_date, price) values ('мороженое пломбир', 2, '2022-06-10', 60);
insert into product(name, type_id, expired_date, price) values ('Хлеб дворянский', 3, '2021-12-18', 50);
insert into product(name, type_id, expired_date, price) values ('Свининна свежая', 4, '2022-01-05', 500);
insert into product(name, type_id, expired_date, price) values ('Сыр пошехонский', 1, '2022-02-01', 400);
insert into product(name, type_id, expired_date, price) values ('Молоко Домик в деревне', 2, '2021-12-28', 80);
insert into product(name, type_id, expired_date, price) values ('Багет московский', 3, '2021-12-15', 45);
insert into product(name, type_id, expired_date, price) values ('Говядина копченая', 4, '2021-11-30', 800);
insert into product(name, type_id, expired_date, price) values ('Семга', 5, '2022-01-15', 1200);
insert into product(name, type_id, expired_date, price) values ('Форель', 5, '2022-02-13', 1055);
insert into product(name, type_id, expired_date, price) values ('Акула холодного копчения', 5, '2022-03-20', 3000);
insert into product(name, type_id, expired_date, price) values ('Сельдь атлантическая', 5, '2021-12-28', 550);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name
from product as p
join type t on p.type_id = t.id
where t.name = 'СЫР';
-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from product
where name like '%мороженое%';
-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product
where expired_date < current_date;
-- 4. Написать запрос, который выводит самый дорогой продукт.
select name, price
from product
where price = (select max(price) from product);
-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name as Тип, count(p.name) as Количество
from type as t
join product p on t.id = p.type_id
group by t.name;
-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name, p.price, p.expired_date
from product as p
join type t on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';
-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук
select t.name as Тип
from type as t
join product p on t.id = p.type_id
group by t.name
having count(p.name) < 10;
-- 8. Вывести все продукты и их тип
select p.name, t.name
from product as p
join type t on t.id = p.type_id;
