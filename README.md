# MITBibli
### Pourquoi MIT ?
C'est un jeu de mot avec les syllabes de nos prénoms : Mathilde, Inès et Tatiana.
Notre logo est le mélange des blasons de nos villes natales à savoir Lisieux, Nancy et Alençon.

### Manuel de lancement

Normalement, le code est dans cette archive. Mais au cas où voici la commande git pour récupérer le projet depuis le Gitlab de l'école :
`git clone https://gitlab.ecole.ensicaen.fr/mleray/mitbibli.git`

###### `Gestion utilisateurs :`
Pour lancer notre service de **gestion des utilisateurs** (sous **Node.js** et **Express.js**), nous ouvrons le dossier frontend (en tant que projet) dans Webstorm. Normalement les configurations sont déjà définies. Il suffit de lancer celle nommée **"start"**. Lors du lancement, notre service va se lancer sur le port **8080** de notre machine et va se connecter à une base de données NoSQL (MongoDB) hébergée dans le cloud.

Si le fichier .env n'est pas présent dans le dossier frontend, il faut le créer et mettre dedans :
```
PORT=8080
DB_CONNECTION=mongodb://MITBeaver:1234AZERTY@cluster0-shard-00-00.setuu.mongodb.net:27017,cluster0-shard-00-01.setuu.mongodb.net:27017,cluster0-shard-00-02.setuu.mongodb.net:27017/MIT_DB?replicaSet=atlas-89tacq-shard-0&ssl=true&authSource=admin
```

Si la configuration n'est pas préalablement enregistrée dans Webstorm, il faut cliquer sur 'Add configuration' en haut à droite et ajouter une configuration npm. On choisit un nom pour cette configuration et on lui indique :
```
Command: run
Scripts: start
```
sans oublier le chemin d'accès au fichier package.json.
Il ne reste plus qu'a tapé la commande `npm install` dans le terminal pour installer tout les modules nécessaires au projet (nom et version des modules présents dans package.json).

###### `Gestion d’accès à la base de donnée et gestion de la bibliothèque :`
Pour lancer notre service **d’accès à la base de donnée et gestion de la bibliothèque** (avec **SpringBoot**, **Hibernate** et **H2 Database**), nous ouvrons le dossier MITProject dans le dossier backend (en tant que projet) dans IntelliJ. Normalement les configurations sont déjà définies. Il suffit de lancer celle nommée **"MitProjectApplication"**. Lors du lancement, notre service va se lancer sur le port **8081** de notre machine et va se connecter à une base de données SQL (H2 Database) hébergée localement.

Il faudra ensuite accéder à la base de données [http://localhost:8081/h2](http://localhost:8081/h2) avec comme configuration :
```
JDBC URL : jdbc:h2:file:~/bibliothequedb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE
User Name : user
Password : //laisser vide//
```
et lui ajouter un jeu de donnée. Pour cela nous devons copier le contenu du fichier **data.sql** se trouvant dans le répertoire resources et le jouer dans H2.

Si la configuration n'est pas préalablement enregistrée dans IntelliJ, il faut cliquer sur 'Add configuration' en haut à droite et ajouter une configuration Springboot. On choisit un nom pour cette configuration et on lui indique :
```
Main Class: fr.ensicaen.ecole.mitproject.MitProjectApplication
```

###### `Interface Homme Machine (IHM) :`
Pour lancer notre **IHM** (avec **Vue.js**), nous ouvrons le dossier frontend (en tant que projet) dans Webstorm (ce dernier est déjà ouvert étant donné que le serveur express est lancé). Il suffit ensuite de lancer la configuration nommée **"serve"**. Lors du lancement, notre application va se lancer sur le port **1024** de notre machine. Pour aller voir le résultat, il suffit d'ouvrir un navigateur et accéder à [http://localhost:1024](http://localhost:1024).

Si la configuration n'est pas préalablement enregistrée dans Webstorm, il faut cliquer sur 'Add configuration' en haut à droite et ajouter une configuration npm. On choisit un nom pour cette configuration et on lui indique :
```
Command: run
Scripts: serve
```
sans oublier le chemin d'accès au fichier package.json.

### Architecture

###### `Serveur Node.js et Express.js :`
C'est lui qui gère la création, l'authentification, la modification et la déconnexion d'utilisateur. Il gère également la gestion des rôles. Toutes ces données sont stockées dans une base de données MongoDB dans le cloud (deux collections : User et Role).
- models :
Nous avons un premier dossier qui contient le modèle de nos données selon la collection. Nous avons ainsi un modèle pour les utilisateurs et un autre pour les rôles.
- routes :
Ce dossier contient les définition des différentes routes et leur implémentation. Nous avons le manager.js qui gère la modification (changement de pseudo, de mot de passe) et la récupération d'un utilisateur (selon un login ou un ID). Puis session.js qui gère toute l'authentification, la déconnexion d'un utilisateur. Enfin userRoles.js nous permet de récupérer des rôles selon leur ID ou leur description.
- utils :
Ce dossier contient le fichier passport.js qui gère toute l'authentification d'un utilisateur via le midddleware du même nom.
- swagger.json (pour la documentation : [http://localhost:8080/docs](http://localhost:8080/docs))
- configure.js (fichier de configuration)
- server.js (fichier principal, appelé lors du lancement du serveur)


###### `API (SpringBoot, Hibernate et H2)`
Il s'agit de notre service d'accès à la base de données de gestion de bibliothèque. Notre API permet d'interroger la base et d'éviter l'accès direct.

Nous avons utilisé SpringBoot, Hibernate et H2 en local.

Notre projet est divisé en différents packages qui regroupent les contrôleurs, les entités, les exceptions, les répertoires et les services.

- controller : ce dossier contient les définitions des routes pour chaque entité
- entity : ce dossier contient tous les objets que nous utilisons
- service : ce dossier contient les classes qui permettent de faire des liens entre les contrôleurs et la base de données.

### Captures d'écran
Les captures d'écran sont dans le dossier images à la racine de notre projet.

### Choix techniques
Sur l'IHM, lors de la connexion d'un utilisateur, un cookie est créé et stocké. De ce fait, la session d'un utilisateur reste valide pendant une heure. Après ce laps de temps, l'utilisateur est automatiquement déconnecté.
De plus, si un utilisateur tente d'aller sur une page dont il n'a pas les droits d'accès, il est automatiquement redirigé vers la page d'accueil. De ce fait, quelqu'un de non authentifié est redirigé sur la page de connexion quelque soit la page existante qu'il essaye d'ouvrir (sinon il est dirigé sur la page Not Found). Pareil pour un utilisateur lambda authentifié qui essaye d'accéder à la page de création d'un livre (réservée aux bibliothécaire et aux administrateurs) par exemple, il est automatiquement redirigé vers la page principale de sa session.

### Utilisateurs enregistrés
- **Admin :**
    - mleray (login)
    - 1234 (password)
- **Contrib :**
    - librarian
    - flynn
- **Borrower :**
    - thot
    - egypt
- **Consult :**
    - giant
    - joséphine

### Couverture de code
Pour accéder à notre workspace Postman :
[lien d'invitation](https://app.getpostman.com/join-team?invite_code=21384a13406c9ebd5fd2c651b2493fde&ws=49dee39b-8748-4c27-80e4-0012da393f9f)