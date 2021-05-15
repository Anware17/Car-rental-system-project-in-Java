package models;

import javafx.scene.control.CheckBox;


public class Voiture {
					
public Voiture () {
	super();}
public Voiture (int idv, String Matricule, String Marque, String Couleur, String Type, String Etat, Double Price ) {
	super();
	this.idv = idv;
	this.Matricule = Matricule;
	this.Marque = Marque;
	this.Couleur = Couleur;
	this.Type = Type;
	this.Etat=Etat;
	this.Price=Price;}

			
public Voiture (String Matricule, String Marque, String Couleur, String Type,Double Price )		
{super();
this.Matricule = Matricule;
this.Marque = Marque;
this.Couleur = Couleur;
this.Type = Type;
this.Price=Price;
}

		private int idv;
		private String Matricule;
		private String Marque;
		private String Couleur;
		private String Type;
		private CheckBox reservation;
		private String Etat;
		private Double Price;
		public int getIdv() {
			return idv;
		}
		public void setIdv(int idv) {
			this.idv = idv;
		}
		public String getMatricule() {
			return Matricule;
		}
		public void setMatricule(String matricule) {
			Matricule = matricule;
		}
		public String getMarque() {
			return Marque;
		}
		public void setMarque(String marque) {
			Marque = marque;
		}
		public String getCouleur() {
			return Couleur;
		}
		public void setCouleur(String champCouleur) {
			Couleur = champCouleur;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public CheckBox getReservation() {
			return reservation;
		}
		public void setReservation(CheckBox reservation) {
			this.reservation = reservation;
		}
		public String getEtat() {
			return Etat;
		}
		public void setEtat(String etat) {
			Etat = etat;
		}

		public Double getPrice() {
			return Price;
		}

		public void setPrice(Double price) {
			Price = price;
		}











}	
