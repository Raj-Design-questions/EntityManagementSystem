CREATE TABLE test_schema.Doctor (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255),
    UUID text NOT NULL
);

ALTER SEQUENCE test_schema.doctor_id_seq RESTART WITH 1;