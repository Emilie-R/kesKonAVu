INSERT INTO UTILISATEUR (id_member, creation_date, email, password, pseudo)	VALUES (1, current_date, 'email@gmail.fr', '123456', 'emilie');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'USER');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (1, 'ADMIN');

INSERT INTO UTILISATEUR (id_member, creation_date, email, password, pseudo)	VALUES (2, current_date, 'email@gmail.fr', '654321', 'klebert');
INSERT INTO UTILISATEUR_ROLE (member_id_member, roles) VALUES (2, 'USER');