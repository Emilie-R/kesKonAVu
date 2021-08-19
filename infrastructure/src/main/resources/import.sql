-- Create Member
INSERT INTO UTILISATEUR (creation_date, email, password, pseudo)	VALUES (current_date, 'email@gmail.fr', '123456', 'emilie');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'USER');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'ADMIN');

INSERT INTO UTILISATEUR (creation_date, email, password, pseudo)	VALUES (current_date, 'email@gmail.fr', '654321', 'klebert');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (2, 'USER');

-- Create MOVIE
INSERT INTO CATALOGUE_RESOURCE (title, resource_type, category, dtype, duration, external_catalog_name, external_key) VALUES ('Le Grand Bleu','MOVIE', 'Drame', 'Resource', 240, 'OMDBAPI', 'tt0095250');

-- Create SERIE
INSERT INTO CATALOGUE_RESOURCE (title, resource_type, category, dtype, duration, external_catalog_name, external_key, number_of_episodes, number_of_seasons) VALUES ('Game of Thrones','SERIE', 'Action, Adventure', 'Serie', 50, 'OMDBAPI', 'tt0944947', 0, 7);
INSERT INTO CATALOGUE_EPISODE (external_catalog_name, external_key, number, season_number, title) VALUES ('OMDBAPI', '123456', 1,1, 'S1E1');
INSERT INTO CATALOGUE_EPISODE (external_catalog_name, external_key, number, season_number, title) VALUES ('OMDBAPI', '123456', 2,1, 'S1E2');
INSERT INTO CATALOGUE_EPISODE (external_catalog_name, external_key, number, season_number, title) VALUES ('OMDBAPI', '123456', 3,1, 'S1E3');
INSERT INTO CATALOGUE_EPISODE (external_catalog_name, external_key, number, season_number, title) VALUES ('OMDBAPI', '123456', 1,2, 'S2E1');
INSERT INTO CATALOGUE_EPISODE (external_catalog_name, external_key, number, season_number, title) VALUES ('OMDBAPI', '123456', 2,3, 'S2E2');
INSERT INTO CATALOGUE_EPISODE (external_catalog_name, external_key, number, season_number, title) VALUES ('OMDBAPI', '123456', 3,4, 'S2E3');


-- Create MOVIE FollowUp
INSERT INTO SUIVI_RESOURCE (dtype, creation_date, last_modification_date, member_id_member, resource_id_resource, status, note) VALUES ('ResourceFollowUp', current_date, current_date, 1, 1, 'VU', 10);
INSERT INTO SUIVI_RESOURCE (dtype, creation_date, last_modification_date, member_id_member, resource_id_resource, status) VALUES ('ResourceFollowUp', current_date, current_date, 2, 1, 'AVOIR');

-- Create SERIE FollowUp
INSERT INTO SUIVI_RESOURCE (dtype, creation_date, last_modification_date, member_id_member, resource_id_resource, status, note, progression) VALUES ('SerieFollowUp', current_date, current_date, 1, 2, 'VU', 10, 45);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'VU',1);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'VU',2);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'VU',3);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'AVOIR',4);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'AVOIR',5);
INSERT INTO SUIVI_SERIE_PROGRESSION (serie_follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,1);
INSERT INTO SUIVI_SERIE_PROGRESSION (serie_follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,2);
INSERT INTO SUIVI_SERIE_PROGRESSION (serie_follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,3);
INSERT INTO SUIVI_SERIE_PROGRESSION (serie_follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,4);
INSERT INTO SUIVI_SERIE_PROGRESSION (serie_follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,5);