/*7. В подключенном MySQL репозитории создать базу данных “Друзья
человека”*/

DROP DATABASE IF EXISTS human_friends;	
CREATE DATABASE human_friends; 		
USE human_friends;

/*8. Создать таблицы с иерархией из диаграммы в БД
9. Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения*/


CREATE TABLE AnimalsGroups (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(70) NOT NULL UNIQUE
);
INSERT INTO AnimalsGroups VALUES (1, 'Pets');
INSERT INTO AnimalsGroups VALUES (2, 'Pack Animals');
SELECT * FROM AnimalsGroups;

CREATE TABLE AnimalsTypes (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	AnimalsGroups_name VARCHAR(70) NOT NULL,
	name VARCHAR(50) NOT NULL UNIQUE,
	FOREIGN KEY (AnimalsGroups_name) REFERENCES AnimalsGroups(name)
);
INSERT INTO AnimalsTypes 
VALUES 
	('1', 'Pets', 'dog'),
	('2', 'Pets', 'cat'),
	('3', 'Pets', 'hamster'),
	('4', 'Pack Animals', 'camel'),
	('5', 'Pack Animals', 'horse'),
	('6', 'Pack Animals', 'donkey');
SELECT * FROM AnimalsTypes;

CREATE TABLE Commands (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(70) NOT NULL UNIQUE
);
INSERT INTO Commands
VALUES 
	(1, 'sit'),
	(2, 'stay'),
	(3, 'pounce'),
	(4, 'roll'),
	(5, 'hide'),
	(6, 'paw'),
	(7, 'voice'),
	(8, 'spin'),
	(9, 'jump'),
	(10, 'trot'),
	(11, 'canter'),
	(12, 'kick'),
	(13, 'walk'),
	(14, 'gallop'),
	(15, 'run'),
	(16, 'scratch'),
	(17, 'carry load'),
	(18, " ");
SELECT * FROM Commands;

CREATE TABLE Animals (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	AnimalsTypes_name VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL,
	birthdate DATE,
	commands1 VARCHAR(70),
	commands2 VARCHAR(70),
	commands3 VARCHAR(70),
	FOREIGN KEY (AnimalsTypes_name) REFERENCES AnimalsTypes(name),
	FOREIGN KEY (commands1) REFERENCES Commands(name),
	FOREIGN KEY (commands2) REFERENCES Commands(name),
	FOREIGN KEY (commands3) REFERENCES Commands(name)
);
INSERT INTO Animals VALUES 
	(1, 'horse', 'thunder', '2015-07-21', 'trot', 'canter', 'gallop'),
	(2, 'camel', 'sandy', '2016-11-03', 'walk', 'carry load', " "),
	(3, 'donkey', 'eeyore', '2017-09-18', 'walk', 'carry load', 'voice'),
	(4, 'horse', 'storm', '2014-05-05', 'trot', 'canter', " "),
	(5, 'camel', 'dune', '2018-12-12', 'walk', 'sit', " "),
	(6, 'donkey', 'burro', '2019-01-23', 'walk', 'voice', 'kick'),
	(7, 'horse', 'blaze', '2016-02-29', 'trot', 'jump', 'gallop'),
	(8, 'camel', 'sahara', '2015-08-14', 'walk', 'run', " "),
	(9, 'dog', 'fido', '2020-01-01', 'sit', 'stay', " "),
	(10, 'cat', 'whiskers', '2019-05-15', 'sit', 'pounce', " "),
	(11, 'hamster', 'hammy', '2021-03-10', 'roll', 'hide', " "),
	(12, 'dog', 'buddy', '2018-12-10', 'sit', 'paw', 'voice'),
	(13, 'cat', 'smudge', '2020-02-20', 'sit', 'pounce', 'scratch'),
	(14, 'hamster', 'peanut', '2021-08-01', 'roll', 'spin', " "),
	(15, 'dog', 'bella', '2019-11-11', 'sit', 'stay', 'roll'),
	(16, 'cat', 'oliver', '2020-06-30', 'voice', 'scratch', 'jump');

CREATE TABLE AnimalsJ AS SELECT 
    id, 
    AnimalsTypes_name,
    name,
    birthdate,
    CONCAT_WS(", ",commands1, commands2, commands3) AS commands 
    FROM Animals;
SELECT * FROM AnimalsJ;

--10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
--питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

DELETE FROM Animals WHERE AnimalsTypes_name = 'camel';
CREATE TABLE PackAnimals AS SELECT * FROM Animals WHERE AnimalsTypes_name = 'horse' OR AnimalsTypes_name = 'donkey';
SELECT * FROM Animals;

--11.Создать новую таблицу “молодые животные” в которую попадут все
--животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
--до месяца подсчитать возраст животных в новой таблице

CREATE TABLE YoungAnimals AS SELECT 
    Animals.id,
    AnimalsTypes.name AS type,
    Animals.name,    
    PERIOD_DIFF(DATE_FORMAT(now(), '%Y%m'), DATE_FORMAT(Animals.birthdate, '%Y%m')) AS age_month
    FROM Animals JOIN AnimalsTypes ON AnimalsTypes.name = Animals.AnimalsTypes_name
    WHERE year(now())-year(Animals.birthdate) >= 1 AND year(now())-year(Animals.birthdate) <= 3;
SELECT * FROM YoungAnimals;


--12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
--прошлую принадлежность к старым таблицам.

CREATE TABLE HumanFriends AS SELECT
    DISTINCT Animals.id AS 'Animals.id',
    AnimalsGroups.id AS 'AnimalsGroups.id',
    AnimalsGroups.name AS 'AnimalsGroups.name',
    AnimalsTypes.id AS 'AnimalsTypes.id',
    Animals.AnimalsTypes_name AS 'Animals.AnimalsTypes_name',
    Animals.name AS 'Animals.name',
    Animals.birthdate AS 'Animals.birthdate',
    IF(YoungAnimals.id = Animals.id, YoungAnimals.age_month, " ") AS 'YoungAnimals.age_month',
    AnimalsJ.commands AS 'AnimalsJ.commands'
FROM Animals, AnimalsTypes, AnimalsGroups, YoungAnimals, AnimalsJ
WHERE AnimalsJ.id = Animals.id AND AnimalsGroups.name = AnimalsTypes.AnimalsGroups_name AND Animals.AnimalsTypes_name = AnimalsTypes.name;



SELECT * FROM HumanFriends;
