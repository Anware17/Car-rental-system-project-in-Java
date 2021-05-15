package models;

import java.sql.Date;
import java.time.LocalDate;


public class Reservation {
	

public Reservation (String Matricule_fk)
{this.setMatricule_fk(Matricule_fk);
	}

public Reservation (Integer numRes, Integer cinclient_fk, String Matricule_fk, Date dateDebut ,Date dateFin , String TypePaiment, Double Montant) {
		super();
		this.numRes = numRes;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.setCinclient_fk(cinclient_fk);
		this.setMatricule_fk(Matricule_fk);
		this.TypePaiment=TypePaiment;
		this.Montant=Montant;
	    
			}
public Reservation (Integer cinclient_fk, String Matricule_fk, Date dateDebut ,Date dateFin , String TypePaiment, Double Montant) {
		super();
		
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.setCinclient_fk(cinclient_fk);
		this.setMatricule_fk(Matricule_fk);
		this.TypePaiment=TypePaiment;
		this.Montant=Montant;}

	
	public Reservation (Integer cinclient_fk, String Matricule_fk, String TypePaiment, Double Montant) {
		super();
		this.setCinclient_fk(cinclient_fk);
		this.setMatricule_fk(Matricule_fk);
		this.TypePaiment=TypePaiment;
		this.Montant=Montant;}
	
			
			private Integer numRes;
			private Date dateDebut;
			private Date dateFin;
			private String TypePaiment;
			private Double Montant;
			private Integer cinclient_fk;
			private String Matricule_fk;
			
			public int getNumRes() {
				return numRes;
			}
			public void setNumRes(int numRes) {
				this.numRes = numRes;
			}
			public Date getDateDebut() {
				return dateDebut;
			}
			public void setDateDebut(Date dateDebut) {
				this.dateDebut = dateDebut;
			}
			public Date getDateFin() {
				return dateFin;
			}
			public void setDateFin(Date dateFin) {
				this.dateFin = dateFin;
			}
			public String getTypePaiment() {
				return TypePaiment;
			}
			public void setTypePaiment(String typePaiment) {
				TypePaiment = typePaiment;
			}
			public Double getMontant() {
				return Montant;
			}
			public void setMontant(Double montant) {
				Montant = montant;
			}
			public Integer getCinclient_fk() {
				return cinclient_fk;
			}
			public void setCinclient_fk(Integer cinclient_fk) {
				this.cinclient_fk = cinclient_fk;
			}
			public String getMatricule_fk() {
				return Matricule_fk;
			}
			public void setMatricule_fk(String matricule_fk) {
				Matricule_fk = matricule_fk;
			}	
			
		
			public LocalDate getDateDeb() {
				LocalDate value = null;
				// TODO Auto-generated method stub
				return value;
			}
			public LocalDate getDateF() {
				LocalDate value=null;
				// TODO Auto-generated method stub
				return value;
			}
		
					
}
