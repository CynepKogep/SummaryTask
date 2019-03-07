DROP database IF exists hospitaldatabase;

CREATE DATABASE hospitaldatabase CHARACTER SET UTF8 COLLATE utf8_unicode_ci;

USE hospitaldatabase;

-- -------------------------------------------------------------------------------
--                     TABLE <<ROLE>>
-- -------------------------------------------------------------------------------

CREATE TABLE role (
    id        INTEGER NOT NULL PRIMARY KEY, 
    role_name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO role (id, role_name) VALUES (0, 'admin');
INSERT INTO role (id, role_name) VALUES (1, 'doctor');
INSERT INTO role (id, role_name) VALUES (2, 'nurse');

select * from role;

-- -------------------------------------------------------------------------------
--                     TABLE <<PROFESSION>>
-- -------------------------------------------------------------------------------

create table profession (
    id                 INTEGER NOT NULL PRIMARY KEY, 
    profession_name    VARCHAR(20) NOT NULL UNIQUE,
    profession_name_ru VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO profession (id, profession_name, profession_name_ru) 
                VALUES (0, 'not doctor',     'не врач' );
INSERT INTO profession (id, profession_name, profession_name_ru) 
                VALUES (1, 'therapeutist',   'терапевт'); 
INSERT INTO profession (id, profession_name, profession_name_ru) 
                VALUES (2, 'traumatologist', 'травматолог');
INSERT INTO profession (id, profession_name,     profession_name_ru) 
                VALUES (3, 'gastroenterologist', 'гастроэнтеролог');
INSERT INTO profession (id, profession_name, profession_name_ru) 
                VALUES (4, 'surgeon',        'хирург');

select * from profession;

-- -------------------------------------------------------------------------------
--                     TABLE <<MEDICAL_USER>>
-- -------------------------------------------------------------------------------

CREATE table medical_user (
    id            INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login         VARCHAR(20) UNIQUE NOT NULL, 
    password      VARCHAR(20), 
    first_name    VARCHAR(20) NOT NULL, 
    last_name     VARCHAR(20) NOT NULL,
    first_name_ru VARCHAR(20) NOT NULL, 
    last_name_ru  VARCHAR(20) NOT NULL,
    profession_id INTEGER,
    role_id       INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id),
    FOREIGN KEY (profession_id) REFERENCES profession(id)
);
					
INSERT INTO medical_user (login,    password, first_name, last_name,   first_name_ru, last_name_ru, profession_id, role_id)  
                  VALUES ('admin', 'admin',   'Andrey',   'Admiralov', 'Андрей',      'Адмиралов',  0,             0);
INSERT INTO medical_user (login,    password, first_name, last_name,   first_name_ru, last_name_ru, profession_id, role_id)  
                  VALUES ('doctor', 'doctor', 'Ivan',     'Ivanov',    'Иван',        'Иванов',     1,             1);
INSERT INTO medical_user (login,    password,   first_name, last_name, first_name_ru, last_name_ru, profession_id, role_id)  
                  VALUES ('doctor2', 'doctor2', 'Vasa',     'Vasilev', 'Вася',        'Васильев',   2,             1);
INSERT INTO medical_user (login,    password, first_name, last_name,   first_name_ru, last_name_ru, profession_id, role_id)  
                  VALUES ('nurse',  'nurse',  'Anna',     'Annova',    'Анна',        'Аннова',     0,             2);

select * from medical_user;

-- -------------------------------------------------------------------------------
--                     TABLE <<DIAGNOSIS>>
-- -------------------------------------------------------------------------------

CREATE TABLE diagnosis (
    id                INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    diagnosis_name    VARCHAR(20) NOT NULL UNIQUE,
    diagnosis_name_ru VARCHAR(20) NOT NULL UNIQUE
);
                        
INSERT INTO diagnosis(diagnosis_name, diagnosis_name_ru) 
              VALUES ('not define',   'не определен' );
INSERT INTO diagnosis(diagnosis_name, diagnosis_name_ru) 
              VALUES ('varicella',    'ветряная оспа');
INSERT INTO diagnosis(diagnosis_name, diagnosis_name_ru) 
              VALUES ('flu',          'грипп' );
INSERT INTO diagnosis(diagnosis_name, diagnosis_name_ru) 
              VALUES ('appendicitis', 'аппендицит');

SELECT * FROM diagnosis;

-- -------------------------------------------------------------------------------
--                     TABLE <<PATIENT>>
-- -------------------------------------------------------------------------------

CREATE table patient (
    id              INTEGER UNSIGNED NOT NULL AUTO_INCREMENT primary key,  
	first_name      VARCHAR(20) NOT NULL, 
	last_name       VARCHAR(20) NOT NULL,
	first_name_ru   VARCHAR(20) NOT NULL, 
	last_name_ru    VARCHAR(20) NOT NULL,
    doctor_id       INTEGER UNSIGNED NOT NULL,
    date_of_birth   DATE NOT NULL,
    telephon_number VARCHAR(15) NOT NULL, 
	email           VARCHAR(40) NOT NULL,
    discharged      boolean not null default 0,
    diagnosis_id    INTEGER DEFAULT 1,
	FOREIGN KEY (diagnosis_id) REFERENCES diagnosis(id),
    FOREIGN KEY (doctor_id) REFERENCES medical_user(id)
);
                        
INSERT INTO patient (id,      first_name, last_name,  first_name_ru, last_name_ru, doctor_id, date_of_birth, telephon_number, email,             discharged, diagnosis_id) 
             VALUES (DEFAULT, 'Bpatient1', 'Bpatient1', 'Бпациент1',    'Бпациент1',   2,         '2001-02-11',  '5507777777',    'mail7@.mail.com', DEFAULT,    1);
INSERT INTO patient (id,      first_name, last_name,  first_name_ru, last_name_ru, doctor_id, date_of_birth, telephon_number, email,             discharged, diagnosis_id)  
             VALUES (DEFAULT, 'Dpatient2', 'Dpatient2', 'Дпациент2',    'Дпациент2',   2,         '2002-01-12',  '45088888888',   'mail2@.mail.com', DEFAULT,    1);
INSERT INTO patient (id,      first_name, last_name,  first_name_ru, last_name_ru, doctor_id, date_of_birth, telephon_number, email,             discharged, diagnosis_id)  
             VALUES (DEFAULT, 'Apatient3', 'Apatient3', 'Апациент3',    'Апациент3',   3,         '2003-03-12',  '35088888883',   'mail8@.mail.com', DEFAULT,    1);
INSERT INTO patient (id,      first_name, last_name,  first_name_ru, last_name_ru, doctor_id, date_of_birth, telephon_number, email,             discharged, diagnosis_id)  
             VALUES (DEFAULT, 'Epatient4', 'Epatient4', 'Епациент4',    'Епациент4',   3,         '2004-05-14',  '25088888884',   'mail4@.mail.com', DEFAULT,    1);
INSERT INTO patient (id,      first_name, last_name,  first_name_ru, last_name_ru, doctor_id, date_of_birth, telephon_number, email,             discharged, diagnosis_id)  
             VALUES (DEFAULT, 'Cpatient5', 'Cpatient5', 'Спациент5',    'Спациент5',   3,         '2005-07-15',  '15088888885',   'mail1@.mail.com', DEFAULT,    1);

SELECT * FROM patient;

-- -------------------------------------------------------------------------------
--                     TABLE <<ASSIGNMENT_STATUS>>
-- -------------------------------------------------------------------------------

CREATE TABLE assignment_status (
    id             INTEGER NOT NULL PRIMARY KEY,
	status_name    VARCHAR(20) NOT NULL UNIQUE,
    status_name_ru VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO assignment_status (id, status_name,   status_name_ru) 
                       VALUES (0,  'in progress', 'в ходе выполнения');
INSERT INTO assignment_status (id, status_name,   status_name_ru)  
                       VALUES (1, 'complete', 'завершить');

SELECT * FROM assignment_status;

-- -------------------------------------------------------------------------------
--                     TABLE <<ASSIGNMENT_TYPE>>
-- -------------------------------------------------------------------------------

CREATE TABLE assignment_type (
    id           INTEGER NOT NULL PRIMARY KEY,
	type_name    VARCHAR(20) NOT NULL UNIQUE,
    type_name_ru VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO assignment_type (id, type_name,    type_name_ru) 
                     VALUES (0,  'procedures', 'процедуры');
INSERT INTO assignment_type (id, type_name, type_name_ru)
                     VALUES (1,  'pills',   'таблетки');
INSERT INTO assignment_type (id, type_name,  type_name_ru) 
                     VALUES (2, 'operation', 'операция');

-- -------------------------------------------------------------------------------
--                     TABLE <<PATIENT_ASSIGNMENT>>
-- -------------------------------------------------------------------------------
                        
CREATE TABLE patient_assignment (
    id                   INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	patient_id           INTEGER UNSIGNED NOT NULL, 
	assignment_id        INTEGER NOT NULL,
	assignment_status_id INTEGER NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (assignment_id) REFERENCES assignment_type(id),
    FOREIGN KEY (assignment_status_id) REFERENCES assignment_status(id));

INSERT INTO patient_assignment ( id,      patient_id, assignment_id, assignment_status_id ) 
                        VALUES ( default, 1,          0,             0); 
INSERT INTO patient_assignment ( id,      patient_id, assignment_id, assignment_status_id )
                        VALUES ( default, 2,          1,             0);  
INSERT INTO patient_assignment ( id,      patient_id, assignment_id, assignment_status_id ) 
                        VALUES ( default, 3,          0,             0); 
INSERT INTO patient_assignment ( id,      patient_id, assignment_id, assignment_status_id )
                        VALUES ( default, 4,          0,             0);  
INSERT INTO patient_assignment ( id,      patient_id, assignment_id, assignment_status_id )
                        VALUES ( default, 5,          0,             0);  
                        
SELECT * FROM patient_assignment;                        