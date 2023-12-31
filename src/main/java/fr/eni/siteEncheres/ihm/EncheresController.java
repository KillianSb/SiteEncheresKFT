package fr.eni.siteEncheres.ihm;

import java.security.Principal;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.siteEncheres.bll.ArticleVenduService;
import fr.eni.siteEncheres.bll.CategorieService;
import fr.eni.siteEncheres.bll.EnchereService;
import fr.eni.siteEncheres.bll.UtilisateurService;
import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Enchere;
import fr.eni.siteEncheres.bo.Retrait;
import fr.eni.siteEncheres.bo.Utilisateur;
import fr.eni.siteEncheres.dal.UtilisateurDAO;
import jakarta.validation.Valid;

@Controller

public class EncheresController {

	private UtilisateurService utilisateurService;
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	private EnchereService enchereService;	
	private UtilisateurDAO utilisateurDAO;

	public EncheresController(UtilisateurService utilisateurService, ArticleVenduService articleVenduService,
			CategorieService categorieService, EnchereService enchereService) {
		this.utilisateurService = utilisateurService;
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
		this.enchereService = enchereService;
	}

	@GetMapping({"/", "/accueil"})
	public String afficherAccueil(Model modele) {

		List<ArticleVendu> listeArticle = articleVenduService.getArticleVendu();
		modele.addAttribute("articleVendu", listeArticle);

		return "PageAccueilNonConnecte";
	}

	@GetMapping("/accueil/articleParCategorie")
	public String afficherAccueilParCategorie(@RequestParam Integer idCategorie, String filtre, Model modele, Principal principal) {

		List<ArticleVendu> listeArticle;

		if (filtre != null && !filtre.isEmpty()) {
			listeArticle = articleVenduService.getArticleVenduParCategorieEtFiltre(idCategorie, filtre);
		} else {
			listeArticle = articleVenduService.getArticleVenduParCategorie(idCategorie);
		}

		modele.addAttribute("articleVendu", listeArticle);

		// Pour l'affichage des points dans le header
		String username = principal.getName();
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);

		return "PageAccueilNonConnecte";
	}

	@GetMapping("/connexion")
	public String afficherPageConnexion() {
		return "PageConnexion";
	}

	@GetMapping("/inscription")
	public String afficherPageInscription(@ModelAttribute Utilisateur utilisateur, BindingResult validationResultat) {
		return "PageCreerCompte";
	}


	@PostMapping("/profil/modifier")
	public String afficherPageInscriptionErreur(@Valid @ModelAttribute Utilisateur utilisateur,
			BindingResult validationResultat, Model modele ,Principal principal) {

		if(validationResultat.hasErrors()) {
			return "PageCreerCompte";
		}

		utilisateurService.enregistrerUtilisateur(utilisateur);

		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);	
		return "redirect:/connexion";
	}

	@RequestMapping("/encheres")
	public String afficherPageEncheres(Utilisateur utilisateur, Model modele, Principal principal) {

		List<ArticleVendu> listeArticle = articleVenduService.getArticleVendu();	
		modele.addAttribute("articleVendu", listeArticle);  

		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);	  
		return "PagesListeEncheresConnecte";
	}

	@GetMapping("/liste-encheres/mes-ventes")
	public String afficherPageMesVentes(@RequestParam Integer idCategorie, String filtre, Model modele, Principal principal) {

		List<ArticleVendu> listeArticle;

		if (filtre != null && !filtre.isEmpty()) {
			listeArticle = articleVenduService.getArticleVenduParCategorieEtFiltre(idCategorie, filtre);
		} else {
			listeArticle = articleVenduService.getArticleVenduParCategorie(idCategorie);
		}

		modele.addAttribute("articleVendu", listeArticle);

		// Pour l'affichage des points dans le header
		String username = principal.getName();
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);	
		return "PagesListeEncheresConnecte";
	}

	@GetMapping("/profil")
	public String afficherPageProfil(Model model, Principal principal) {

		String username = principal.getName();

		Utilisateur utilisateur = utilisateurService.findByUserName(username);

		model.addAttribute("utilisateur", utilisateur);	
		return "PageMonProfil";
	}


	@GetMapping("/modifierProfil")
	public String afficherPagesModifierMonProfil(Model model, Principal principal) {
		String username = principal.getName();	
		Utilisateur utilisateur = utilisateurService.findByUserName(username);

		model.addAttribute("utilisateur", utilisateur);
		return "PageModifierMonProfil";
	}


	@PostMapping("/profilModifier")
	public String afficherPageProfilModifier(@Valid @ModelAttribute Utilisateur utilisateur , BindingResult validationResultat ) {

		if(validationResultat.hasErrors()) {				
			return "redirect:/modifierProfil";
		}
		utilisateurService.enregistrerUtilisateur(utilisateur);
		return "redirect:/encheres";
	}

	@PostMapping("/supprimerProfil")
	public String supprimerProfilUtilisateur(Model modele, Principal principal) {
		String username = principal.getName();	
		Utilisateur utilisateur = utilisateurService.findByUserName(username);

		utilisateurService.supprimerUtilisateur(utilisateur);		
		return "redirect:/logout";
	}

	@GetMapping("/vendre")
	public String afficherPageVendre(Model modele, Principal principal) {

		// Pour l'affichage des points dans le header
		// Récupération des infos utilisateur pour affichage de retrait 
		String username = principal.getName();
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);

		ArticleVendu articleVendu  = new ArticleVendu();
		modele.addAttribute("articleVendu", articleVendu);

		Retrait articleRetrait = new Retrait();
		articleRetrait.setRue(utilisateur.getRue());
		articleRetrait.setVille(utilisateur.getVille());
		articleRetrait.setCode_Postal(utilisateur.getCodePostal());

		modele.addAttribute("retrait", articleRetrait);

		return "PageVendreUnArticle";
	}

	@PostMapping("/vendre/valider")
	public String afficherVendreArticle(ArticleVendu articleVendu, Utilisateur utilisateur, Model modele ,Principal principal) {

		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);

		articleVenduService.enregistrerArticleVendu(articleVendu, utilisateur);

		return "redirect:/encheres";
	}

	@GetMapping("/vendre/modif")
	public String afficherPageEnchereNonCommencee(Utilisateur utilisateur, Model modele ,Principal principal) {

		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);

		return "PageEnchereNonCommencee";
	}

	@GetMapping("/encherir")
	public String afficherPageEncherir(@RequestParam("idArticle") Integer idArticle, Utilisateur utilisateur, Model model, Principal principal, Enchere enchere) {

		ArticleVendu articleVendu = articleVenduService.findById(idArticle);
		model.addAttribute("articleVendu", articleVendu);

		try {
			enchere = enchereService.findById(idArticle);
			model.addAttribute("enchere", enchere);
			Integer idMeilleurOffre = enchereService.readAncienEncherisseur(idArticle);
			Utilisateur utilMeilleurOffre = utilisateurService.findById(idMeilleurOffre);
			String utilisateurMeilleurOffre = utilMeilleurOffre.getPseudo();
			model.addAttribute("utilisateurMeilleurOffre", utilisateurMeilleurOffre);

		} catch (EmptyResultDataAccessException ex) {

			ex.printStackTrace();
		}

		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		model.addAttribute("utilisateur", utilisateur);

		return "PageEncherir";
	}

	@PostMapping("/encherir")
	public String validationPageEncherir(@RequestParam("prixEnchere") Integer prixEnchere, Principal principal, @RequestParam("idArticle") Integer idArticle, Model model, Enchere enchere,
			ArticleVendu articleVendu, Utilisateur utilisateur) {

		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		Integer idUtilisateur = utilisateur.getIdUtilisateur();	
		articleVendu = articleVenduService.findById(idArticle);
		model.addAttribute("articleVendu", articleVendu);

		Integer meilleurOffre = enchereService.readAncienEncherisseur(idArticle);

		if (meilleurOffre != null) {
			// Enchére déjà existant
			Integer sommeARecredite = enchereService.readAncienOffre(idArticle);
			Integer idAncienEncherisseur = enchereService.readAncienEncherisseur(idArticle);
			enchere.setIdArticle(idAncienEncherisseur);

			enchereService.enregistrerEnchere(enchere, prixEnchere, articleVendu, utilisateur);
			enchere.setMontantEnchere(prixEnchere);

			utilisateurService.ajouterPoint(sommeARecredite, idAncienEncherisseur);
			utilisateurService.retirerPoints(prixEnchere, idUtilisateur);
		} else {	
			// Création d'une nouvelle enchère
			Enchere creationEnchere = new Enchere();
			model.addAttribute("enchere", creationEnchere);

			enchereService.enregistrerEnchere(enchere, prixEnchere, articleVendu, utilisateur);
			enchere.setMontantEnchere(prixEnchere);
			utilisateurService.retirerPoints(prixEnchere, idUtilisateur);
		}
		return "redirect:/encheres";
	}

	@GetMapping("/acquisition")
	public String afficherPageAcquisition() {
		return "PageAcquisition";
	}

	@GetMapping("/ma-vente-fini")
	public String afficherPageMaFinVente() {
		return "PageDetailMaVenteFinEnchere";
	}
}
