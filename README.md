    Compte rendu du travail effectué

À la suite de la réunion que nous avons eu l’interface faite a été amélioré (certaines fonctionnalités ont été rajoutées).
Celle-ci se présente comme suit :



L’utilisateur entre son gmail et son mot de passe pour se connecter. 
* Infos
En cliquant sur le bouton infos l’utilisateur obtient des infos de base sur sa boite mail.
A titre illustratif :


* Histogramme
Ce bouton permet d’afficher une nouvelle fenêtre qui nous présentera l’histogramme des âges des mails par mois. Pour cette boite mail on obtient :
* Options

En cliquant sur ce bouton on a la fenêtre suivante :



	Il offre deux fonctionnalités (on peut en rajouter en cas de besoin). La première c’est supprimer les messages contenus dans le dossier spams, la deuxième on choisit l’âge maximum des mails qu’on souhaite avoir dans sa boite. Par exemple en choisissant 6 puis en cliquant sur le bouton enregistrer, on supprimera les messages âgés de plus de 6 mois.
* Entropie

Pour le calcul de l’entropie on a décidé non pas de partir sur la fréquence d’utilisation d’un mail (ce qui est impossible à déterminer) mais plutôt sur l’âge des mails c’est-à-dire le temps passé par le mail dans la boite de réception. On utilise donc les résultats fournis par l’histogramme et on calcule la probabilité de se retrouver dans chaque classe. Après ça on applique juste la formule. Pour la boite email gautrobn@gmail.com on a donc :




