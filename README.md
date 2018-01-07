Application Backend Sirh Gestion Personnel
==========================================



Ressource Départements
----------------------


URI path                         |  méthode HTTP  |  Description     
-------------------------------- | -------------- | --------------------------------------------------------------------------
**_/api/departements_**          |  GET           |  retourne la liste des départements au format JSON



Ressource Collaborateurs
------------------------


URI path                                               |  méthode HTTP  |  Description     
------------------------------------------------------ | -------------- | --------------------------------------------------------------------------
**_/api/collaborateurs_**                              |  GET           |  retourne la liste des collaborateurs au format JSON
**_api/collaborateurs?departement=[ID_DEPARTEMENT]_**  |  GET           |  retourne la liste des collaborateurs au format JSON dont le département a l’identifiant ID_DEPARTEMENT
**_/api/collaborateurs/[MATRICULE]_**                  |  GET           |  retourne les informations d’un collaborateur
**_/api/collaborateurs/[MATRICULE]_**                  |  PUT           |  modifie un collaborateur (données envoyées au format JSON)
**_/api/collaborateurs/[MATRICULE]/banque**            |  GET           |  récupère les coordonnées bancaires d’un collaborateur.




