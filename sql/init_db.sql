CREATE TABLE worker (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2),
    BIRTHDAY DATE NOT NULL CHECK (EXTRACT(YEAR FROM BIRTHDAY) > 1900),
    LEVEL VARCHAR(10) NOT NULL CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    SALARY INT NOT NULL CHECK (SALARY >= 100 AND SALARY <= 100000)
);

CREATE TABLE client (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2)
);

CREATE TABLE project (
    ID SERIAL PRIMARY KEY,
    CLIENT_ID INT NOT NULL,
    START_DATE DATE NOT NULL,
    FINISH_DATE DATE,
    CONSTRAINT fk_client FOREIGN KEY (CLIENT_ID) REFERENCES client(ID)
);

CREATE TABLE project_worker (
    PROJECT_ID INT NOT NULL,
    WORKER_ID INT NOT NULL,
    PRIMARY KEY (PROJECT_ID, WORKER_ID),
    CONSTRAINT fk_project FOREIGN KEY (PROJECT_ID) REFERENCES project(ID) ON DELETE CASCADE,
    CONSTRAINT fk_worker FOREIGN KEY (WORKER_ID) REFERENCES worker(ID) ON DELETE CASCADE
);
