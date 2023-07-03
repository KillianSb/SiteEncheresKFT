package fr.eni.siteEncheres.ihm;

import java.util.List;

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
import fr.eni.siteEncheres.bll.UtilisateurService;
import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Categorie;
import fr.eni.siteEncheres.bo.Utilisateur;
import fr.eni.siteEncheres.dal.UtilisateurDAO;
import jakarta.validation.Valid;

@Controller
public class EncheresController {
	
	private UtilisateurService utilisateurService;
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	
	private UtilisateurDAO utilisateurDAO;
	
	
	public EncheresController(UtilisateurService utilisateurService, ArticleVenduService articleVenduService,
			CategorieService categorieService) {
		this.utilisateurService = utilisateurService;
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
	}
	
	
	@GetMapping({"/", "/accueil"})
	public String afficherAccueil(Model modele) {
		
		List<ArticleVendu> listeArticle = articleVenduService.getArticleVendu();
		modele.addAttribute("articleVendu", listeArticle);
		
		return "PageAccueilNonConnecte";
	}
	
	
	@GetMapping("/accueil/articleParCategorie")
	public String afficherAccueilParCategorie(@RequestParam Integer idCategorie, String filtre, Model modele) {
		
	    List<ArticleVendu> listeArticle;
	    
	    if (filtre != null && !filtre.isEmpty()) {
	        listeArticle = articleVenduService.getArticleVenduParCategorieEtFiltre(idCategorie, filtre);
	    } else {
	        listeArticle = articleVenduService.getArticleVenduParCategorie(idCategorie);
	    }
	  
	    modele.addAttribute("articleVendu", listeArticle);
	    
	    return "PageAccueilNonConnecte";
	}
	
	
	@GetMapping("/connexion")
	public String afficherPageConnexion() {
		return "PageConnexion";
	}
	
	@GetMapping("/inscription")
	public String afficherPageInscription(@Valid @ModelAttribute Utilisateur utilisateur,
			BindingResult validationResultat) {
		
		if(validationResultat.hasErrors()) {
			return "PageCreerCompte";
		}
		return "redirect:/inscription";
	}
	
	@GetMapping("/encheres")
	public String afficherPageEncheres(Integer idUtilisateur, Model modele) {
		
		Utilisateur utilisateur = utilisateurService.findById(2);
		modele.addAttribute("utilisateur", utilisateur);
		
		Categorie categorie = categorieService.findById(1);
		  modele.addAttribute("categorie", categorie);
		List<ArticleVendu> listeArticle = articleVenduService.getArticleVendu();	
		modele.addAttribute("articleVendu", listeArticle);  
		  
		return "PagesListeEncheresConnecte";
	}
	
	@GetMapping("/liste-encheres/mes-ventes")
	public String afficherPageMesVentes(@RequestParam Integer idCategorie, String filtre, Model modele) {
		
	    List<ArticleVendu> listeArticle;
	    
	    if (filtre != null && !filtre.isEmpty()) {
	        listeArticle = articleVenduService.getArticleVenduParCategorieEtFiltre(idCategorie, filtre);
	    } else {
	        listeArticle = articleVenduService.getArticleVenduParCategorie(idCategorie);
	    }
	  
	    modele.addAttribute("articleVendu", listeArticle);
		
		return "PagesListeEncheresConnecte";
	}
	
	@GetMapping("/profil")
	public String afficherPageProfil(Integer idUtilisateur, Model modele) {
		
		Utilisateur utilisateur = utilisateurService.findById(1);
		 modele.addAttribute("utilisateur", utilisateur);
		
		return "PageMonProfil";
	}
	
	@GetMapping("/modifierProfil")
	public String afficherPagesModifierMonProfil(Integer idUtilisateur, Model modele) {
		
		Utilisateur utilisateur = utilisateurService.findById(1);
		modele.addAttribute("utilisateur", utilisateur);
		return "PageModifierMonProfil";
	}
	
	
	@PostMapping("/supprimerProfil")
	public String supprimerProfilUtilisateur(@RequestParam Integer idUtilisateur, Model modele) {
		Utilisateur utilisateur = utilisateurService.findById(4);
		utilisateurService.supprimerUtilisateur(utilisateur);		
		return "redirect:/";
	}
	
	@GetMapping("/vendre")
	public String afficherPageVendre(Model modele) {
		
		ArticleVendu articleVendu  = new ArticleVendu();
		modele.addAttribute("articleVendu", articleVendu);
	    
		return "PageVendreUnArticle";
	}
	
	@PostMapping("/vendre/valider")
	public String afficherVendreArticle( ArticleVendu articleVendu, Utilisateur utilisateur) {
		utilisateur = utilisateurService.findById(2); // temporaire en attente de connexion

		articleVenduService.enregistrerArticleVendu(articleVendu, utilisateur);
		
		return "redirect:/encheres";
	}
	
	
	@PostMapping("/profil/modifier")
	public String afficherPageProfilModifier(Utilisateur utilisateur) {
		
		utilisateurService.enregistrerUtilisateur(utilisateur);
		System.out.println(utilisateur);
		
		return "redirect:/accueil";
	}
	
	@GetMapping("/vendre/modif")
	public String afficherPageEnchereNonCommencee() {
		return "PageEnchereNonCommencee";
	}
	
	@RequestMapping("/encherir")
	public String afficherPageEncherir(@RequestParam("idArticle") Integer idArticle, Model model) {
		
		ArticleVendu articleVendu = articleVenduService.findById(idArticle);
		model.addAttribute("articleVendu", articleVendu);
		
		return "PageEncherir";
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
