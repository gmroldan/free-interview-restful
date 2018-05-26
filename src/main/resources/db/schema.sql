DROP TABLE INTERVIEWER IF EXISTS;
DROP TABLE INTERVIEW IF EXISTS;

CREATE TABLE INTERVIEWER (
    ID INTEGER IDENTITY PRIMARY KEY,
    FIRST_NAME VARCHAR(30),
    LAST_NAME VARCHAR(30)
);
CREATE SEQUENCE INTERVIEWER_ID_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE INTERVIEW (
    ID INTEGER IDENTITY PRIMARY KEY,
    INTERVIEWER_ID NUMERIC NOT NULL,
    INTERVIEW_DATE TIMESTAMP
);
ALTER TABLE INTERVIEW ADD CONSTRAINT FK_INTERVIEWER FOREIGN KEY (INTERVIEWER_ID) REFERENCES INTERVIEWER (ID);