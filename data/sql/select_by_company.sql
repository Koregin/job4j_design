CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company VALUES (1, 'IBM');
INSERT INTO company VALUES (2, 'Apple');
INSERT INTO company VALUES (3, 'Microsoft');
INSERT INTO company VALUES (4, 'Dell');
INSERT INTO company VALUES (5, 'HP');

INSERT INTO person VALUES (1, 'Ivanov', 1);
INSERT INTO person VALUES (2, 'Jobs', 2);
INSERT INTO person VALUES (3, 'Gates', 3);
INSERT INTO person VALUES (4, 'Smith', 4);
INSERT INTO person VALUES (5, 'Green', 5);
INSERT INTO person VALUES (6, 'Junior', 3);
INSERT INTO person VALUES (7, 'Salor', 3);
INSERT INTO person VALUES (8, 'Voznyak', 2);
INSERT INTO person VALUES (9, 'Lacokka', 2);

--1. - имена всех person, которые не состоят в компании с id = 5; - название компании для каждого человека.
SELECT p.name, c.name
FROM person as p
JOIN company c ON c.id = p.company_id
WHERE p.company_id != 5;

-- 2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
-- (нужно учесть, что таких компаний может быть несколько).
SELECT c.name, count(c.name) as count
FROM company AS c
JOIN person p ON c.id = p.company_id
GROUP BY c.name
HAVING count(c.name) = (
        SELECT count(company_id)
        FROM person
        GROUP BY company_id
        ORDER BY count(company_id) DESC
        LIMIT 1
    );