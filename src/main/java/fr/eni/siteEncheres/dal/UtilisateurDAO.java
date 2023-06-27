package fr.eni.siteEncheres.dal;

import java.util.List;

import fr.eni.siteEncheres.bo.Utilisateur;

public interface UtilisateurDAO {

	List<Utilisateur> findAll();

	Utilisateur read(Integer idUtilisateur);

}