CREATE TABLE test_schema.Patient (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255),
    UUID text NOT NULL,
    DOCTOR_UUID text NOT NULL
);

ALTER SEQUENCE test_schema.patient_id_seq RESTART WITH 1;