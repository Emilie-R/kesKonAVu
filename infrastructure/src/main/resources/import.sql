INSERT INTO UTILISATEUR (creation_date, email, password, pseudo)	VALUES (current_date, 'email@gmail.fr', '123456', 'emilie');
INSERT INTO UTILISATEUR (creation_date, email, password, pseudo)	VALUES (current_date, 'email@gmail.fr', '654321', 'klebert');

INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'USER');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'ADMIN');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (2, 'USER');

INSERT INTO CATALOGUE_RESOURCE (title, resource_type, category, dtype, duration, external_catalog_name, external_key) VALUES ('Le Grand Bleu','MOVIE', 'Drame', 'Resource', 240, 'OMDBAPI', 'tt0095250');
INSERT INTO CATALOGUE_RESOURCE (title, resource_type, category, dtype, duration, external_catalog_name, external_key, number_of_episodes, number_of_seasons) VALUES ('Game of Thrones','SERIE', 'Action, Adventure', 'Serie', 50, 'OMDBAPI', 'tt0944947', 0, 7);

INSERT INTO SUIVI_RESOURCE (creation_date, last_modification_date, member_id_member, resource_id_resource, status, note,progression) VALUES (current_date, current_date, 1, 2, 'VU', 10,0);
INSERT INTO SUIVI_RESOURCE (creation_date, last_modification_date, member_id_member, resource_id_resource, status,note,progression) VALUES (current_date, current_date, 2, 1, 'AVOIR',20,22);