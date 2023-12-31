package fr.eni.siteEncheres.bo;

import java.sql.Date;



public class ArticleVendu {
	
	private Integer idArticle;
	

	private String nomArticle;
	

	private String description;
	
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	

	private Integer miseAPrix;
	
	private Integer prixVente;
	
	private boolean etatVente;
	
	private Utilisateur utilisateur;
	
	private Categorie categorie;
	
	private Retrait retrait;
	
	private Enchere enchere;
	
	public ArticleVendu() {
	}

	// Constructeur sans étatVente
	public ArticleVendu(Integer idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, Integer miseAPrix, Integer prixVente, Utilisateur utilisateur,
			Categorie categorie) {
		super();
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}
	
	public ArticleVendu(Integer idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, Integer miseAPrix, Integer prixVente, boolean etatVente, Utilisateur utilisateur,
			Categorie categorie) {
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}
	
	public ArticleVendu(Integer idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, Integer miseAPrix, Integer prixVente, boolean etatVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait, Enchere enchere) {
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.enchere = enchere;
	}
	
	public ArticleVendu(Integer idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, Integer miseAPrix, Integer prixVente, boolean etatVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait) {
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(Date date) {
		this.dateDebutEncheres = date;
	}

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public Integer getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public boolean isEtatVente() {
		return etatVente;
	}

	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	@Override
	public String toString() {
		return "ArticleVendu [idArticle=" + idArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", categorie=" + categorie + ", retrait =" + retrait +"]";
	}
	

}
