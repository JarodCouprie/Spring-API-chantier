# Evaluation API Java Spring

### Connexion utilisateur

- **Création d'un nouvel utilisateur** : http://localhost:8080/sign-in
    - Méthode POST
    - Champs à renseigner
        - pseudo : String
        - password : String
        - role : { id : Integer }

- **Connexion de l'utilisateur** : http://localhost:8080/log-in
    - Méthode POST
    - Champs à renseigner
        - pseudo : String
        - password: String

### Permettre de lister les chantiers (réservé au chef de chantier)

- **Lister les chantiers lorsqu'on en est le chef** : http://localhost:8080/chantier/list
    - Méthode GET

### Permettre de connaitre le temps total pour un chantier (réservé au chef de chantier)

- **Connaître le temps total d'un chantier lorsqu'on en est le chef** : http://localhost:8080/chantier/time/{id}
    - Méthode GET
    - id : est l'identifiant du chantier dont on veut récupérer le temps total

### Permettre a un employé de connaitre la liste des opération qu'il doit réaliser (réservé au chef de chantier et aux ouvriers)

- **Connaître uniquement les opérations que l'on doit réaliser** : http://localhost:8080/operation/list
    - Méthode GET