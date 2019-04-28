# APIREST_USER

Créer 2 services REST: 
un permettant d’enregistrer un utilisateur et 
un autre affichant les détails d'un utilisateur enregistré.

Ces 2 services sont réalisés. Les requêtes correspondants peuvent être testés via POSTMAN par exemple.

Exigences:
- définir un utilisateur (quels sont les champs nécessaires). Nous devrions avoir des champs obligatoires et optionnels!
Réalisé avec une classe user.

- valider l'entrée et renvoyer les messages d'erreur / l'état http correct
Les vérifications des des réponses HTTP sont gérés dans userController

- enregistre l'entrée et la sortie de chaque appel et le temps de traitement.
Un système de log a été mis en place. Le fichier est disponible dans le PATH suivant : APIREST_USER/TestOffer/UserController.log

- avoir un paramètre de requête qui n'est pas obligatoire et qui fournit une valeur par défaut au cas où il n’ai pas mis.
Gérer dans le POST avec le champ phoneNumber. Valeur par défaut géré.

- avoir une variable PATH
Gérer

- code clair et javadoc
Commentaire du code avec les bonnes conventions pour pouvoir générer la Javadoc mis en place. Disponible dans le dossier doc du répertoire du workspace.

- tests unitaires
Non effectué

- seuls les adultes (âgés de plus de 18 ans) et résidant en France peuvent créer un compte!
Conditions pris en compte lors du traitement de la requête POST.

- utiliser une base de données non relationnelle afin de sauver les utilisateurs
IL n'existe pas de persistance des données dans cette solution. Evolution possible vers MongoDB

- documentation 
Swagger a été mis en place pour pouvoir avoir une documentation de l'API.
Disponible à l'adresse suivante : http://127.0.0.1:8080/swagger-ui.html#/
Attention effectivement au serveur lancé et au port utilisé.
