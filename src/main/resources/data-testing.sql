INSERT INTO role(designation)
VALUES ("client"),
       ("ouvrier"),
       ("administrateur");

INSERT INTO utilisateur(pseudo, password, role_id)
VALUES ("admin", "$2a$12$Nvc681i40b3DdF1zV/h9vuN/RAbmponV9cRj5cfkP2Pn0RkBgaDw2", 3),
       ("user", "$2a$12$m5lBD3iO.rCh0ET6H9z3eOxNkn34FbfIQgNz5/PqwKYBK6hUv7CTq", 2),
       ("Bertrand", "$2a$12$m5lBD3iO.rCh0ET6H9z3eOxNkn34FbfIQgNz5/PqwKYBK6hUv7CTq", 2),
       ("Jérome", "$2a$12$m5lBD3iO.rCh0ET6H9z3eOxNkn34FbfIQgNz5/PqwKYBK6hUv7CTq", 2),
       ("Sophie", "$2a$12$m5lBD3iO.rCh0ET6H9z3eOxNkn34FbfIQgNz5/PqwKYBK6hUv7CTq", 1);

INSERT INTO chantier(name, address, owner_id, leader_id)
VALUES ("Résidence des étoiles", "76 Rue de l'univers", 2, 1),
       ("Maison des roses", "76 Rue du jardin", 2, 1);

INSERT INTO tache(time, name)
VALUES (10, "balayer"),
       (120, "couler une dalle"),
       (60, "creuser un trou");

INSERT INTO operation(name, date, chantier_planned_id, utilisateur_given_id, task_to_do_id)
VALUES ("Piscine", current_date, 1, 3, 3),
       ("Piscine", current_date, 1, 4, 3);

