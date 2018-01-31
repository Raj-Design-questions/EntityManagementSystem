CREATE TABLE test_schema.Patient (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255)
);

ALTER SEQUENCE test_schema.patient_id_seq RESTART WITH 1;