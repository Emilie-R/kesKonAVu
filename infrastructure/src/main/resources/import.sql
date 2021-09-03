-- Create Member
INSERT INTO UTILISATEUR (id_member, creation_date, email, password, pseudo)   VALUES ('ID-1', current_date, 'email@gmail.fr', '123456', 'emilie');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES ('ID-1', 'USER');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES ('ID-1', 'ADMIN');
INSERT INTO UTILISATEUR (id_member, creation_date, email, password, pseudo)   VALUES ('ID-2', current_date, 'email@gmail.fr', '654321', 'klebert');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES ('ID-2', 'USER');
-- Create MOVIE
INSERT INTO RESOURCE (title, resource_type, category, dtype, duration, datasource, imdb_id) VALUES ('Le Grand Bleu','MOVIE', 'Drame', 'Resource', '240 minutes' , 'OMDBAPI', 'tt0095250');

-- Create SERIE
INSERT INTO RESOURCE (title, resource_type, category, dtype, duration, datasource, imdb_id, number_of_episodes, number_of_seasons) VALUES ('Game of Thrones','SERIE', 'Action, Adventure', 'Serie', '50 minutes', 'OMDBAPI', 'tt0944947', 0, 7);

-- Create EPISODES
INSERT INTO EPISODE (datasource, imdb_id, number, season_number, title) VALUES ('OMDBAPI', '123456', 1,1, 'S1E1');
INSERT INTO EPISODE (datasource, imdb_id, number, season_number, title) VALUES ('OMDBAPI', '123456', 2,1, 'S1E2');
INSERT INTO EPISODE (datasource, imdb_id, number, season_number, title) VALUES ('OMDBAPI', '123456', 3,1, 'S1E3');
INSERT INTO EPISODE (datasource, imdb_id, number, season_number, title) VALUES ('OMDBAPI', '123456', 1,2, 'S2E1');
INSERT INTO EPISODE (datasource, imdb_id, number, season_number, title) VALUES ('OMDBAPI', '123456', 2,3, 'S2E2');
INSERT INTO EPISODE (datasource, imdb_id, number, season_number, title) VALUES ('OMDBAPI', '123456', 3,4, 'S2E3');

-- Create MOVIE FollowUp
INSERT INTO SUIVI_RESOURCE (creation_date, last_modification_date, member_id_member, resource_id_resource, status, note,progression) VALUES (current_date, current_date, 'ID-1', 1, 'VU', 10,100);
INSERT INTO SUIVI_RESOURCE (creation_date, last_modification_date, member_id_member, resource_id_resource, status,note,progression) VALUES (current_date, current_date, 'ID-2', 1, 'AVOIR',20,0);
-- Create SERIE FollowUp
INSERT INTO SUIVI_RESOURCE (creation_date, last_modification_date, member_id_member, resource_id_resource, status, note,progression) VALUES (current_date, current_date, 'ID-1', 2, 'VU', 10,50);

INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'VU',1);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'VU',2);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'VU',3);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'AVOIR',4);
INSERT INTO SUIVI_EPISODE(last_modification_date, status, episode_id_episode) VALUES (current_date, 'AVOIR',5);
INSERT INTO SUIVI_RESOURCE_SUIVI_EPISODE (follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,1);
INSERT INTO SUIVI_RESOURCE_SUIVI_EPISODE (follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,2);
INSERT INTO SUIVI_RESOURCE_SUIVI_EPISODE (follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,3);
INSERT INTO SUIVI_RESOURCE_SUIVI_EPISODE (follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,4);
INSERT INTO SUIVI_RESOURCE_SUIVI_EPISODE (follow_up_id_follow_up, episode_follow_ups_id) VALUES (3,5);