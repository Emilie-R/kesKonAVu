INSERT INTO UTILISATEUR (creation_date, email, password, pseudo)	VALUES (current_date, 'email@gmail.fr', '123456', 'emilie');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'USER');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'ADMIN');

INSERT INTO UTILISATEUR (creation_date, email, password, pseudo)	VALUES (current_date, 'email@gmail.fr', '654321', 'klebert');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (2, 'USER');

INSERT INTO CATALOGUE_REFERENCE_EXTERNE (catalog_name, resource_id) VALUES ('OMDBAPI', 'tt0095250');
INSERT INTO CATALOGUE_RESOURCE (title, resource_type, category, external_key_id, dtype, duration) VALUES ('Le Grand Bleu','MOVIE', 'Drame', 1, 'Resource', 240);
INSERT INTO CATALOGUE_REFERENCE_EXTERNE (catalog_name, resource_id) VALUES ('OMDBAPI', 'tt0944947');
INSERT INTO CATALOGUE_RESOURCE (title, resource_type, category, external_key_id, dtype, duration) VALUES ('Game of Thrones','SERIE', 'Action, Adventure', 2, 'Serie', 50);