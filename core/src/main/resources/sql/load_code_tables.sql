--
-- The Archetype is configured with "hibernate.hbm2ddl.auto" value="create-drop" in "persistence.xml".
--
-- This will cause hibernate to populate the database when the application is started by processing the files that
-- were configured in the hibernate.hbm2ddl.import_files property.
--
-- This file is responsible for loading  out-of-box miscellaneous tables
--

-- ???? to do / document me (believe this is for securing pages through the site web.app
INSERT INTO BLC_ROLE (ROLE_ID, ROLE_NAME) VALUES (1,'ROLE_USER');

-- ???? to do / document me
INSERT INTO BLC_ID_GENERATION (ID_TYPE, BATCH_SIZE, BATCH_START, VERSION) VALUES ('org.broadleafcommerce.profile.core.domain.Customer',100,100,1);

-- Sample challenge questions
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (1,'What is your favorite sports team?');
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (2,'What was your high school name?');
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (3,'What was your childhood nickname?'); 
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (4,'What street did you live on in third grade?');
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (5,'What is your oldest sibling''s middle name?');
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (6,'What school did you attend for sixth grade?');
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (7,'Where does your nearest sibling live?');
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (8,'What is your youngest brother''s birthday?');
INSERT INTO BLC_CHALLENGE_QUESTION (QUESTION_ID, QUESTION) VALUES (9,'In what city or town was your first job?');

-- Sample countries.  To do, where is this used?
INSERT INTO BLC_COUNTRY VALUES ('US','United States');
INSERT INTO BLC_COUNTRY VALUES ('CA','Canada');
INSERT INTO BLC_COUNTRY VALUES ('MX','Mexico');
INSERT INTO BLC_COUNTRY VALUES ('CO','Colombia');

-- Sample state list.  To do, where is this used
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Amazonas','Amazonas','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Antioquia','Antioquia','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Arauca','Arauca','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Atl&aacute;ntico','Atlantico','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Bol&iacute;var','Bolivar','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Boyac&aacute;','Boyaca','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Caldas','Caldas','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Caquet&aacute;','Caqueta','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Casanare','Casanare','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Cauca','Cauca','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Cesar','Cesar','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Choc&oacute;','Choco','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('C&oacute;rdoba','Cordoba','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Cundinamarca','Cundinamarca','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('G&uuml;ainia','Guainia','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Guaviare','Guaviare','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Huila','Huila','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('La Guajira','La Guajira','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Magdalena','Magdalena','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Meta','Meta','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Nari&ntilde;o','Narino','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Norte de Santander','Norte de Santander','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Putumayo','Putumayo','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Quind&iacute;o','Quindio','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Risaralda','Risaralda','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('San Andr&eacute;s y Providencia','San Andres y Providencia','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Santander','Santander','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Sucre','Sucre','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Tolima','Tolima','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Valle del Cauca','Valle del Cauca','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Vaup&eacute;s','Vaupes','CO');
INSERT INTO BLC_STATE (NAME, ABBREVIATION, COUNTRY) VALUES ('Vichada','Vichada','CO');
