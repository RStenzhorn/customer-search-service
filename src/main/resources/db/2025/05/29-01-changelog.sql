-- liquibase formatted sql

-- changeset robert:1748513216910-1
CREATE TABLE customer
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    email       VARCHAR(255),
    phone       VARCHAR(255),
    customer_id VARCHAR(255)                            NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO customer (first_name, last_name, email, phone, customer_id) VALUES
('Max', 'Mustermann', 'max.mustermann@email.de', '+49 30 12345678', 'CUST-001'),
('Anna', 'Schmidt', 'anna.schmidt@gmail.com', '+49 40 87654321', 'CUST-002'),
('Peter', 'Weber', 'peter.weber@web.de', '+49 89 11223344', 'CUST-003'),
('Lisa', 'Müller', 'lisa.mueller@outlook.de', '+49 69 55667788', 'CUST-004'),
('Thomas', 'Fischer', 'thomas.fischer@gmx.de', '+49 221 99887766', 'CUST-005'),
('Sarah', 'Wagner', 'sarah.wagner@yahoo.de', '+49 711 44556677', 'CUST-006'),
('Michael', 'Becker', 'michael.becker@t-online.de', '+49 511 22334455', 'CUST-007'),
('Julia', 'Schulz', 'julia.schulz@freenet.de', '+49 351 66778899', 'CUST-008'),
('Daniel', 'Hoffmann', 'daniel.hoffmann@posteo.de', '+49 201 33445566', 'CUST-009'),
('Jennifer', 'Richter', 'jennifer.richter@mail.de', '+49 431 77889900', 'CUST-010'),
('Stefan', 'Klein', 'stefan.klein@arcor.de', '+49 621 11224433', 'CUST-011'),
('Christina', 'Wolf', 'christina.wolf@1und1.de', '+49 381 55443322', 'CUST-012'),
('Martin', 'Schröder', 'martin.schroeder@email.com', '+49 341 99776655', 'CUST-013'),
('Nicole', 'Neumann', 'nicole.neumann@hotmail.de', '+49 561 44332211', 'CUST-014'),
('Andreas', 'Schwarz', 'andreas.schwarz@aol.de', '+49 761 88775544', 'CUST-015'),
('Melanie', 'Zimmermann', 'melanie.zimmermann@live.de', '+49 241 22113300', 'CUST-016'),
('Frank', 'Braun', 'frank.braun@googlemail.com', '+49 395 66554433', 'CUST-017'),
('Sabrina', 'Krüger', 'sabrina.krueger@web.com', '+49 451 33221100', 'CUST-018'),
('Jürgen', 'Hartmann', 'juergen.hartmann@email.net', '+49 371 77665544', 'CUST-019'),
('Katrin', 'Lange', 'katrin.lange@provider.de', '+49 385 99887722', 'CUST-020');
