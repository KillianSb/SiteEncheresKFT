  
 insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values 
  ('chaussure', 'chaussure', '2023-07-01', '2023-07-06', 10, 50, 1, 3);

INSERT INTO  ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere) 
VALUES (2,1,'2023-07-05',250);

Update UTILISATEURS set credit=1000 ;

