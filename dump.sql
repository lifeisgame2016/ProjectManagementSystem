--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: companies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE companies_id_seq
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE companies_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: companies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE companies (
    id_company integer DEFAULT nextval('companies_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    address text NOT NULL,
    id_project integer NOT NULL
);


ALTER TABLE companies OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE customer_id_seq
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customer_id_seq OWNER TO postgres;

--
-- Name: customers; Type: TABLE; Schema: public; Owner: userj
--

CREATE TABLE customers (
    id_customer integer DEFAULT nextval('customer_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    account real NOT NULL,
    id_project integer NOT NULL
);


ALTER TABLE customers OWNER TO userj;

--
-- Name: developer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE developer_id_seq
    START WITH 9
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE developer_id_seq OWNER TO postgres;

--
-- Name: developers; Type: TABLE; Schema: public; Owner: userj
--

CREATE TABLE developers (
    id_developer integer DEFAULT nextval('developer_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    age integer NOT NULL,
    address text,
    id_project integer NOT NULL,
    salary integer
);


ALTER TABLE developers OWNER TO userj;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE employee (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    age integer NOT NULL,
    address character varying(50),
    salary real,
    join_date date
);


ALTER TABLE employee OWNER TO postgres;

--
-- Name: project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE project_id_seq
    START WITH 5
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project_id_seq OWNER TO postgres;

--
-- Name: projects; Type: TABLE; Schema: public; Owner: userj
--

CREATE TABLE projects (
    id_project integer DEFAULT nextval('project_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    dat_beg date NOT NULL,
    dat_end date,
    id_company integer NOT NULL,
    id_customer integer NOT NULL,
    cost integer
);


ALTER TABLE projects OWNER TO userj;

--
-- Name: skill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE skill_id_seq
    START WITH 12
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE skill_id_seq OWNER TO postgres;

--
-- Name: skills; Type: TABLE; Schema: public; Owner: userj
--

CREATE TABLE skills (
    id_skills integer DEFAULT nextval('skill_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    id_developer integer NOT NULL
);


ALTER TABLE skills OWNER TO userj;

--
-- Data for Name: companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY companies (id_company, name, address, id_project) FROM stdin;
1	ITWIN	Kiev	2
2	Ficha	Lviv	1
3	JustIT	Kiev	3
4	Bubik	USA	1
\.


--
-- Name: companies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('companies_id_seq', 4, true);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('customer_id_seq', 4, false);


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: userj
--

COPY customers (id_customer, name, account, id_project) FROM stdin;
1	Alex	2500000	3
2	Max	1800000	1
3	Den	3600000	2
\.


--
-- Name: developer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('developer_id_seq', 20, true);


--
-- Data for Name: developers; Type: TABLE DATA; Schema: public; Owner: userj
--

COPY developers (id_developer, name, age, address, id_project, salary) FROM stdin;
2	Den	34	Lviv	1	4400
3	Braun	28	Kiev	3	5100
4	Mykl	28	Kiev	2	5800
5	Ron	42	Dnepr	3	6500
6	Alen	35	Lviv	1	7200
7	Jon	20	Kharkov	2	7900
10	Jon	34	Kiev	3	50000
11	Jon	34	Kiev	3	50000
12	Jon	34	Kiev	3	50000
13	Jon	34	Kiev	3	50000
14	Jon	34	Kiev	3	50000
15	Jon	34	Kiev	3	50000
16	Jon	34	Kiev	3	50000
17	Max	23	Kiev	2	8000
18	Jon	34	Kiev	3	50000
19	Jon	34	Kiev	3	50000
20	Jon	34	Kiev	3	50000
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employee (id, name, age, address, salary, join_date) FROM stdin;
1	Paul	23	California	20000	2001-07-13
2	Allen	25	Texas	\N	2007-12-13
3	Teddy	23	Norvay	20000	\N
4	Mark	25	Rich-Mond	65000	2007-12-13
5	David	27	Texas	85000	2007-12-13
\.


--
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('project_id_seq', 5, false);


--
-- Data for Name: projects; Type: TABLE DATA; Schema: public; Owner: userj
--

COPY projects (id_project, name, dat_beg, dat_end, id_company, id_customer, cost) FROM stdin;
1	Bank Report	2015-01-20	2018-12-20	1	1	1300000
2	Navigat	2016-01-01	2020-04-01	1	2	1600000
3	Cars Shop	2014-05-01	2017-02-01	2	3	1900000
4	SentMass	2016-09-01	2019-04-25	3	2	2200000
\.


--
-- Name: skill_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('skill_id_seq', 12, false);


--
-- Data for Name: skills; Type: TABLE DATA; Schema: public; Owner: userj
--

COPY skills (id_skills, name, id_developer) FROM stdin;
4	Java	2
5	Pl/SQL	2
6	Java	3
7	Pl/SQL	4
8	C#	5
9	Java	6
10	Pl/SQL	7
11	C#	6
\.


--
-- Name: companies companies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (id_company);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id_customer);


--
-- Name: developers developers_pkey; Type: CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY developers
    ADD CONSTRAINT developers_pkey PRIMARY KEY (id_developer);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: projects projects_pkey; Type: CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY projects
    ADD CONSTRAINT projects_pkey PRIMARY KEY (id_project);


--
-- Name: skills skills_pkey; Type: CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY skills
    ADD CONSTRAINT skills_pkey PRIMARY KEY (id_skills);


--
-- Name: fki_fk_companies; Type: INDEX; Schema: public; Owner: userj
--

CREATE INDEX fki_fk_companies ON projects USING btree (id_company);


--
-- Name: fki_fk_customers; Type: INDEX; Schema: public; Owner: userj
--

CREATE INDEX fki_fk_customers ON projects USING btree (id_customer);


--
-- Name: fki_fk_project; Type: INDEX; Schema: public; Owner: userj
--

CREATE INDEX fki_fk_project ON developers USING btree (id_project);


--
-- Name: fki_fk_skills; Type: INDEX; Schema: public; Owner: userj
--

CREATE INDEX fki_fk_skills ON skills USING btree (id_developer);


--
-- Name: projects fk_companies; Type: FK CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY projects
    ADD CONSTRAINT fk_companies FOREIGN KEY (id_company) REFERENCES companies(id_company);


--
-- Name: projects fk_customers; Type: FK CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY projects
    ADD CONSTRAINT fk_customers FOREIGN KEY (id_customer) REFERENCES customers(id_customer);


--
-- Name: developers fk_project; Type: FK CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY developers
    ADD CONSTRAINT fk_project FOREIGN KEY (id_project) REFERENCES projects(id_project);


--
-- Name: skills fk_skills; Type: FK CONSTRAINT; Schema: public; Owner: userj
--

ALTER TABLE ONLY skills
    ADD CONSTRAINT fk_skills FOREIGN KEY (id_developer) REFERENCES developers(id_developer);


--
-- PostgreSQL database dump complete
--

