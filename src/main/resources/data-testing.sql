INSERT INTO role(designation)
VALUES ("client"),
       ("ouvrier"),
       ("administrateur");

INSERT INTO utilisateur(pseudo, password, role_id)
VALUES ("admin", "$2a$12$Nvc681i40b3DdF1zV/h9vuN/RAbmponV9cRj5cfkP2Pn0RkBgaDw2", 3),
       ("utilisateur", "$2a$12$m5lBD3iO.rCh0ET6H9z3eOxNkn34FbfIQgNz5/PqwKYBK6hUv7CTq", 1);


