package models;

public class Client {

	private int idc;
	private String nom;
	private String prenom;
	private String numpermis;
	private String adresse;
	private Integer cinclient;
	private String numphone;
	private String mail;
	public Client (int idc, String nom , String prenom, Integer cinclient, String numpermis, String adresse, String numphone, String mail) {
		super();
		this.idc=idc;
		this.nom=nom;
		this.prenom=prenom;
		this.cinclient=cinclient;
		this.numpermis=numpermis;
		this.adresse=adresse;
		this.numphone=numphone;
		this.mail=mail;
		
			}
	public Client  ()		{
	super();	
				}
	public int getIdc() {
		return idc;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNumpermis() {
		return numpermis;
	}
	public void setNumpermis(String numpermis) {
		this.numpermis = numpermis;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Integer getCinclient() {
		return cinclient;
	}
	public void setCinclient(Integer cinclient) {
		this.cinclient = cinclient;
	}
	public String getNumphone() {
		return numphone;
	}
	public void setNumphone(String numphone) {
		this.numphone = numphone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
		
			}	
	
