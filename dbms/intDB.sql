CREATE TABLE skills
(
    id_skills integer NOT NULL,
    name text NOT NULL,
    id_developer integer NOT NULL,
    PRIMARY KEY (id_skills)
);

CREATE TABLE developers
(
    id_developer integer NOT NULL,
    name text NOT NULL,
    age integer NOT NULL,
    address text,
    id_project integer NOT NULL,
    PRIMARY KEY (id_developer)
);

CREATE TABLE projects
(
    id_project integer NOT NULL,
    name text NOT NULL,
    dat_beg date NOT NULL,
    dat_end date,
    id_company integer NOT NULL,
    id_customer integer NOT NULL,
    PRIMARY KEY (id_project)
);

CREATE TABLE companies
(
    id_company integer NOT NULL,
    name text NOT NULL,
    address text NOT NULL,
    id_project integer NOT NULL,
    PRIMARY KEY (id_company)
);

CREATE TABLE customers
(
    id_customer integer NOT NULL,
    name text NOT NULL,
    account real NOT NULL,
    id_project integer NOT NULL,
    PRIMARY KEY (id_customer)
);

ALTER TABLE skills
    ADD CONSTRAINT fk_skills FOREIGN KEY (id_developer)
    REFERENCES developers (id_developer) MATCH SIMPLE;
ALTER TABLE developers
    ADD CONSTRAINT fk_project FOREIGN KEY (id_project)
    REFERENCES projects (id_project) MATCH SIMPLE;	
ALTER TABLE projects
    ADD CONSTRAINT fk_companies FOREIGN KEY (id_company)
    REFERENCES companies (id_company) MATCH SIMPLE;	
ALTER TABLE projects
    ADD CONSTRAINT fk_customers FOREIGN KEY (id_customer)
    REFERENCES customers (id_customer) MATCH SIMPLE;	
	

