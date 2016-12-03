INSERT INTO SKILLS (ID_SKILLS, NAME, ID_DEVELOPER) VALUES
(1, 'Java', 1),
(2, 'Pl/SQL', 1),
(3, 'C#', 1),
(4, 'Java', 2),
(5, 'Pl/SQL', 2),
(6, 'Java', 3),
(7, 'Pl/SQL', 4),
(8, 'C#', 5),
(9, 'Java', 6),
(10, 'Pl/SQL', 7),
(11, 'C#', 6);

INSERT INTO DEVELOPERS(ID_DEVELOPER, NAME, AGE, ADDRESS, ID_PROJECT) VALUES
(1, 'Josh', 23, 'Kiev', 1),
(2, 'Den', 34, 'Lviv',1),
(3, 'Braun', 28, 'Kiev',3),
(4, 'Mykl', 28, 'Kiev',2),
(5, 'Ron', 42, 'Dnepr',3),
(6, 'Alen', 35, 'Lviv',1),
(7, 'Jon', 20, 'Kharkov',2);

INSERT INTO PROJECTS(ID_PROJECT, NAME, DAT_BEG, DAT_END, ID_COMPANY, ID_CUSTOMER)VALUES
(1, 'Bank Report', '2015-01-20', '2018-12-20', 1, 1),
(2, 'Navigat', '2016-01-01', '2020-04-01', 1, 2),
(3, 'Cars Shop', '2014-05-01', '2017-02-01', 2, 3),
(4, 'SentMass', '2016-09-01', '2019-04-25', 4, 2);

INSERT INTO COMPANIES(ID_COMPANY, NAME, ADDRESS, ID_PROJECT) VALUES
(1, 'ITWIN', 'Kiev', 2),
(2, 'Ficha', 'Lviv', 1),
(3, 'JustIT', 'Kiev', 3);

INSERT INTO CUSTOMERS(ID_CUSTOMER, NAME, ACCOUNT, ID_PROJECT) VALUES
(1, 'Alex', 2500000, 3),
(2, 'Max', 1800000, 1),
(3, 'Den', 3600000, 2);